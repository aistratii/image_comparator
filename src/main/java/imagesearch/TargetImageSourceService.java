package imagesearch;

import imagesearch.image.LocalImageStream;
import org.springframework.stereotype.Component;

@Component
public class TargetImageSourceService {
    public LocalImageStream getStreamForRgb(int averageRgb, int rgbDiffTolerance) {
        return null;
    }
}
