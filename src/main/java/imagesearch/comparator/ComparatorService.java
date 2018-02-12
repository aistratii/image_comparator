package imagesearch.comparator;

import imagesearch.Report;
import imagesearch.ReportService;
import imagesearch.source.TargetImageSourceService;
import imagesearch.image.CustomImageType;
import imagesearch.image.LocalImageStream;
import imagesearch.source.SourceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComparatorService {
    private final int RGB_DIFF_TOLERANCE = 20;

    @Autowired
    private ImageComparator imageComparator;

    @Autowired
    private ReportService reportService;

    public void compare(SourceImageService source, TargetImageSourceService target) {
        while(source.hasNextImage()) {
            CustomImageType sourceImage = source.getNextImage();

            LocalImageStream localImageStream = target.getStreamForRgb(sourceImage.getAverageRgb());

            while (localImageStream.hasNext()) {
                CustomImageType targetImage = localImageStream.getNext();

                if (imageComparator.compare(sourceImage, targetImage)) {
                    reportService.persistReport(new Report(sourceImage, targetImage));
                    break;
                }
            }
        }
    }

}
