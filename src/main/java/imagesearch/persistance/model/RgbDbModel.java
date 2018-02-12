package imagesearch.persistance.model;

import java.util.List;

public class RgbDbModel {
    private int rgbId;
    private List<String> fileLocations;

    public int getRgbId() {
        return rgbId;
    }

    public List<String> getFileLocations() {
        return fileLocations;
    }
}
