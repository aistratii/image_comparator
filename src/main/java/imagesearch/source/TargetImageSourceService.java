package imagesearch.source;

import imagesearch.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TargetImageSourceService {

    @Autowired
    private DataBaseService dbService;

    public ImageStream getStreamForRgb(int averageRgb) {
        return new WindowsFileLocation(dbService.getImagesWithRgb(averageRgb));
    }
}
