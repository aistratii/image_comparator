package imagesearch.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class CustomImageType extends BufferedImage{

    public CustomImageType(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public int getAverageRgb() {
        throw new UnsupportedOperationException();
    }

    public String getImageLocation() {
        throw new UnsupportedOperationException();
    }
}
