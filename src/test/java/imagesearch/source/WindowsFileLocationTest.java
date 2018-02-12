package imagesearch.source;

import imagesearch.comparator.ImageComparatorTest;
import imagesearch.image.CustomImageType;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WindowsFileLocationTest {

    @Test
    public void getHasImageTest(){
        WindowsFileLocation windowsFileLocation = new WindowsFileLocation(/*WindowsFileLocationTest.class.getClassLoader().getResource("").toString()*/"C:\\Users\\aistratii\\Desktop\\image-clone-finder\\src\\test\\resources");
        assertTrue(windowsFileLocation.hasNextImage());
    }

    @Test
    public void getNextImageTestSuccess() throws IOException {
        WindowsFileLocation windowsFileLocation = new WindowsFileLocation(/*WindowsFileLocationTest.class.getClassLoader().getResource("").toString()*/"C:\\Users\\aistratii\\Desktop\\image-clone-finder\\src\\test\\resources");
        final String imagePath1 = ImageComparatorTest.class.getClassLoader().getResource("img1.jpg").getFile().toString();
        final String imagePath2 = ImageComparatorTest.class.getClassLoader().getResource("img2.jpg").getFile().toString();

        final BufferedImage image1 = ImageIO.read(new File(imagePath1));
        final BufferedImage image2 = ImageIO.read(new File(imagePath2));
        final CustomImageType customImage1 = new CustomImageType(image1.getWidth(), image1.getHeight(), 1);
        final CustomImageType customImage2 = new CustomImageType(image2.getWidth(), image2.getHeight(), 1);

        customImage1.createGraphics().drawImage(image1, 0, 0, null);
        customImage2.createGraphics().drawImage(image2, 0, 0, null);

        assertEquals(customImage1, windowsFileLocation.getNextImage());
        assertEquals(customImage2, windowsFileLocation.getNextImage());
    }

    @Test
    public void getNextImageTestSingleFile() throws IOException {
        WindowsFileLocation windowsFileLocation = new WindowsFileLocation("C:\\Users\\aistratii\\Desktop\\image-clone-finder\\src\\test\\resources\\img1.jpg");
        final String imagePath1 = ImageComparatorTest.class.getClassLoader().getResource("img1.jpg").getFile().toString();

        final BufferedImage image1 = ImageIO.read(new File(imagePath1));
        final CustomImageType customImage1 = new CustomImageType(image1.getWidth(), image1.getHeight(), 1);

        customImage1.createGraphics().drawImage(image1, 0, 0, null);

        assertEquals(customImage1, windowsFileLocation.getNextImage());
    }

    @Test
    public void getNextImageFail() throws IOException {
        WindowsFileLocation windowsFileLocation = new WindowsFileLocation("sada");

        assertEquals(null, windowsFileLocation.getNextImage());
    }
}
