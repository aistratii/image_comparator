package imagesearch.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class DataBaseService {

    @Autowired
    private Dao dao;

    public List<String> getImagesWithRgb(int averageRgb) {
        return dao.getForRgb(averageRgb);
    }
}
