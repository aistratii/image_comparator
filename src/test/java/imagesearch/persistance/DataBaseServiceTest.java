package imagesearch.persistance;

import imagesearch.persistance.model.AllDbModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
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
        List<String> expectedFilenames = asList("file1.jpg", "file2.jpg");
        int rgb = 101;

        when(dao.getForRgb(rgb)).thenReturn(expectedFilenames);

        List<String> actualFileNames = dataBaseService.getImagesWithRgb(rgb);

        assertEquals(expectedFilenames, actualFileNames);
    }

}
