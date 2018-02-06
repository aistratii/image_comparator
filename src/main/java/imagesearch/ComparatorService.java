package imagesearch;

import imagesearch.image.CustomImageType;
import imagesearch.image.LocalImageStream;
import imagesearch.source.SourceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComparatorService {
    private final int RGB_DIFF_TOLERANCE = 20;

    @Autowired
    private ReportService reportService;

    public void compare(SourceImageService source, TargetImageSourceService target) {
        CustomImageType sourceImage = source.getNextImage();

        LocalImageStream localImageStream =  target.getStreamForRgb(sourceImage.getAverageRgb(), RGB_DIFF_TOLERANCE);

        while(localImageStream.hasNext()){
            CustomImageType targetImage = localImageStream.getNext();

            if(this.compare(sourceImage, targetImage)) {
                reportService.persistReport(new Report(sourceImage, targetImage));
                break;
            }
        }

    }

    public boolean compare(CustomImageType sourceImage, CustomImageType targetImage) {
        throw new UnsupportedOperationException();
    }

}
