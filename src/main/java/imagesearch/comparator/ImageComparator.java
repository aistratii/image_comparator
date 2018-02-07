package imagesearch.comparator;

import imagesearch.image.CustomImageType;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Component
public class ImageComparator {
    private final ExecutorService executorService;
    private final int THREADPOOL_SIZE = 4;
    private final int TOLERANCE_THRESHOLD_RGB = 10;
    private final int TOLERANCE_THRESHOLD_PERCENT = 5;

    @Autowired
    final ComparatorCache comparatorCache;

    public ImageComparator(ComparatorCache comparatorCache) {
        this.executorService = Executors.newFixedThreadPool(THREADPOOL_SIZE);
        this.comparatorCache = comparatorCache;
    }

    public boolean compare(CustomImageType sourceImage, CustomImageType targetImage) {
        Pair<CustomImageType, CustomImageType> pairOfResized = matchSize(sourceImage, targetImage);

        comparatorCache.setBuffer(pairOfResized.getLeft(), pairOfResized.getRight());

        IntStream.of(THREADPOOL_SIZE).forEach(part -> {
            executorService.execute(() ->{
                for (int index = part * min(comparatorCache.getFirstSize(), comparatorCache.getSecondSize()) / THREADPOOL_SIZE;
                        index < min(comparatorCache.getFirstSize(), comparatorCache.getSecondSize());
                        index++){

                    int redDiff = ((comparatorCache.getFirstVector()[part] >> 16) & 0xFF) - ((comparatorCache.getSecondVector()[part] >> 16) & 0xFF);
                    int greenDiff = ((comparatorCache.getFirstVector()[part] >> 8) & 0xFF) - ((comparatorCache.getSecondVector()[part] >> 8) & 0xFF);
                    int blueDiff = ((comparatorCache.getFirstVector()[part] >> 0) & 0xFF) - ((comparatorCache.getSecondVector()[part] >> 0) & 0xFF);

                    boolean diffIsAcceptable = (redDiff >= 0 && redDiff <= TOLERANCE_THRESHOLD_RGB)
                            && (greenDiff >= 0 && greenDiff <= TOLERANCE_THRESHOLD_RGB)
                            && (blueDiff >= 0 && blueDiff <= TOLERANCE_THRESHOLD_RGB);

                    if (diffIsAcceptable)
                        comparatorCache.incrementSuccesses();
                    else
                        comparatorCache.incrementFails();
                }
            });
        });

        try {
            executorService.awaitTermination(20l, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new ComparationException("Something went wrong while waiting for the executor to finish");
        }

        int nrOfIgnoredElements = max(comparatorCache.getFirstSize(), comparatorCache.getSecondSize())
                - min(comparatorCache.getFirstSize(), comparatorCache.getSecondSize());

        comparatorCache.incrementFails(nrOfIgnoredElements);



        try {
            return comparatorCache.getFails() * 100
                    / (comparatorCache.getFails() + comparatorCache.getSuccesses())
                    <= TOLERANCE_THRESHOLD_PERCENT;
        } catch (ArithmeticException ex){
            //should be division by 0
            return true;
        }
    }


    //perhaps will be better to reuse some array instead of new object delcaration => less strain on jvm
    public Pair<CustomImageType, CustomImageType> matchSize(CustomImageType sourceImage, CustomImageType targetImage) {
        throw new UnsupportedOperationException();
    }
}
