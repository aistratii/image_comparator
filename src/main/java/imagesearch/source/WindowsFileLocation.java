package imagesearch.source;

import imagesearch.image.CustomImageType;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static java.util.Arrays.stream;

public class WindowsFileLocation implements SourceImageService {
    private final String fileSourcePath;
    private Stack<String> fileList;

    public WindowsFileLocation(String source) {
        fileList = new Stack<>();

        this.fileSourcePath = source;

        File file = new File(source);
        if (file.isDirectory())
            stream(file.list())
                    .forEach(fileName -> fileList.push(source + File.separator + fileName));
        else
            fileList.push(source);
    }

    @Override
    public CustomImageType getNextImage() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(fileList.pop()));
            CustomImageType customImageType = new CustomImageType(bufferedImage.getWidth(), bufferedImage.getHeight(), 1);
            customImageType.getGraphics().drawImage(customImageType, 0, 0, null);

            return customImageType;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean hasNextImage() {
        return !fileList.empty();
    }
}
