package imagesearch.source;

import imagesearch.DataBaseService;
import imagesearch.image.LocalImageStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TargetImageSourceService {

    @Autowired
    private DataBaseService dbService;

    public LocalImageStream getStreamForRgb(int averageRgb) {
        return new LocalImageStream(dbService.getImagesWithRgb(averageRgb));
    }
}
