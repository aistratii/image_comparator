package imagesearch.comparator;

import imagesearch.Report;
import imagesearch.ReportService;
import imagesearch.TargetImageSourceService;
import imagesearch.image.CustomImageType;
import imagesearch.image.LocalImageStream;
import imagesearch.source.SourceImageService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ComparatorService {
    private final int RGB_DIFF_TOLERANCE = 20;

    @Autowired
    private ImageComparator imageComparator;

    @Autowired
    private ReportService reportService;

    public void compare(SourceImageService source, TargetImageSourceService target) {
        CustomImageType sourceImage = source.getNextImage();

        LocalImageStream localImageStream = target.getStreamForRgb(sourceImage.getAverageRgb(), RGB_DIFF_TOLERANCE);

        while(localImageStream.hasNext()){
            CustomImageType targetImage = localImageStream.getNext();

            if(imageComparator.compare(sourceImage, targetImage)) {
                reportService.persistReport(new Report(sourceImage, targetImage));
                break;
            }
        }

    }

}
