package imagesearch.persistance;

import imagesearch.persistance.model.RgbDbModel;

import java.util.List;

public interface Dao {
    List<RgbDbModel> getForRgb(int averageRgb);
}
