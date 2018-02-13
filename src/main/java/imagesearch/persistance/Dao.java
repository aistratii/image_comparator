package imagesearch.persistance;


import java.util.List;

public interface Dao {
    List<String> getForRgb(int averageRgb);
}
