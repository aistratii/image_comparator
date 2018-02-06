package imagesearch.source;

import imagesearch.image.CustomImageType;


public interface SourceImageService {
    CustomImageType getNextImage();
}
