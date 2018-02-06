package imagesearch.source;

import imagesearch.image.CustomImageType;
import org.springframework.stereotype.Component;

public class WindowsFileLocation implements SourceImageService {
    private final String fileSourcePath;

    public WindowsFileLocation(String source) {
        this.fileSourcePath = source;
    }

    @Override
    public CustomImageType getNextImage() {
        throw new UnsupportedOperationException();
    }
}
