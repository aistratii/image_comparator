package imagesearch.persistance;

import imagesearch.persistance.model.AllDbModel;
import imagesearch.persistance.model.RgbDbModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataBaseServiceTest {

    @Mock
    private Dao dao;

    @Spy
    @InjectMocks
    private DataBaseService dataBaseService;

    @Test
    public void getByRgbTest(){
        RgbDbModel rgbDbModel = mock(RgbDbModel.class);
        List<String> expectedFilenames = asList("file1.jpg", "file2.jpg");
        int rgb = 101;

        when(rgbDbModel.getFileLocations()).thenReturn(expectedFilenames);
        when(dao.getForRgb(rgb)).thenReturn(asList(rgbDbModel));

        List<String> actualFileNames = dataBaseService.getImagesWithRgb(rgb);

        assertEquals(expectedFilenames, actualFileNames);
    }

}
