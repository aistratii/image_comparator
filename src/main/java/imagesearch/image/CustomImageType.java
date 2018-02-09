package imagesearch.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class CustomImageType extends BufferedImage{

    private int rgb = 0;
    private boolean isRgbInited = false;
    private String location;

    public CustomImageType(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public int getAverageRgb() {
        if (isRgbInited)
            return rgb;
        else {
            isRgbInited = true;
            int red = (getRGB(0, 0) >> 16) & 0xFF,
                green = (getRGB(0, 0) >> 8) & 0xFF,
                blue = (getRGB(0, 0) >> 0) & 0xFF;

            for (int i = 0; i < getWidth(); i++)
                for (int j = 0; j < getHeight(); j++){
                    red += (getRGB(i, j) >> 16) & 0xFF;
                    red /= 2;

                    green += (getRGB(0, 0) >> 8) & 0xFF;
                    green /= 2;

                    blue = (getRGB(0, 0) >> 0) & 0xFF;
                    blue /= 2;
                }

            return (red << 16) | (green << 8) | blue;
        }
    }

    public String getImageLocation() {
        return location;
    }

    public void setImageLocation(String location){
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomImageType that = (CustomImageType) o;

        if (rgb != that.rgb) return false;
        if (isRgbInited != that.isRgbInited) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rgb;
        result = 31 * result + (isRgbInited ? 1 : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomImageType{" +
                "rgb=" + rgb +
                ", isRgbInited=" + isRgbInited +
                ", location='" + location + '\'' +
                '}';
    }
}
