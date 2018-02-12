package imagesearch.persistance;

import imagesearch.persistance.model.RgbDbModel;

import java.util.List;

public class MongoDbDao implements Dao {
    @Override
    public List<RgbDbModel> getForRgb(int averageRgb) {
        throw new UnsupportedOperationException();
    }
}
