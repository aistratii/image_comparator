package imagesearch.comparator;

import imagesearch.image.CustomImageType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.ExecutorService;

public class ImageComparator {
    private final ExecutorService executorService;
    private final int THREADPOOL_SIZE = 4;

    public ImageComparator(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public boolean compare(CustomImageType sourceImage, CustomImageType targetImage) {

        Pair<CustomImageType, CustomImageType> pairOfResized = matchSize(sourceImage, targetImage);

        CustomImageType sourceImageSized = pairOfResized.getLeft();
        CustomImageType targetImageSized = pairOfResized.getRight();

        //executorService.execute();
        throw new UnsupportedOperationException();
    }


    public Pair<CustomImageType, CustomImageType> matchSize(CustomImageType sourceImage, CustomImageType targetImage) {
        throw new UnsupportedOperationException();
    }
}
