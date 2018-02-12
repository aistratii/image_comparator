package imagesearch.source;

import imagesearch.persistance.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TargetImageSourceService {

    @Autowired
    private DataBaseService dbService;


    //TODO: Make this API call more universal, to also include calls from some remote DB's
    public ImageStream getStreamForRgb(int averageRgb) {
        return new WindowsFileLocation(dbService.getImagesWithRgb(averageRgb));
    }
}
