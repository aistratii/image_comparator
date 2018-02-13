package imagesearch.persistance;

import com.fasterxml.jackson.databind.ObjectMapper;
import imagesearch.image.CustomImageType;
import imagesearch.persistance.model.AllDbModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.longThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SimpleDbFileDaoTest {

    @Test
    public void readFileTestCreateOnEmpty(){
        SimpleDbFileDao simpleDbFileDao = new SimpleDbFileDao();
        assertNotNull(simpleDbFileDao.getAllDbModel());
        assertTrue(simpleDbFileDao.getAllDbModel().getRgbDbModel().isEmpty());
    }

    @Test
    public void readFileTestReadExistent() throws IOException {
        AllDbModel allDbModel = new AllDbModel();
        allDbModel.getRgbDbModel().put(101, asList("file location1", "file location2"));


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(SimpleDbFileDao.DB_MODEL_FILE_NAME), allDbModel);

        SimpleDbFileDao dao = new SimpleDbFileDao();
        assertEquals(allDbModel, dao.getAllDbModel());

        assertTrue(new File(SimpleDbFileDao.DB_MODEL_FILE_NAME).delete());
    }

    @Test
    public void commitFileTest(){
        SimpleDbFileDao simpleDbFileDao = new SimpleDbFileDao();
        assertNotNull(simpleDbFileDao.getAllDbModel());
        assertTrue(simpleDbFileDao.getAllDbModel().getRgbDbModel().isEmpty());

        simpleDbFileDao.commit();

        File file = new File(SimpleDbFileDao.DB_MODEL_FILE_NAME);
        assertTrue(file.exists());

        assertTrue(new File(SimpleDbFileDao.DB_MODEL_FILE_NAME).delete());
    }

    @Test
    public void updateTest(){
        SimpleDbFileDao simpleDbFileDao = new SimpleDbFileDao();
        CustomImageType image = mock(CustomImageType.class);

        when(image.getAverageRgb()).thenReturn(201);
        when(image.getImageLocation()).thenReturn("img location");

        simpleDbFileDao.update(image);

        assertEquals(asList("img location"), simpleDbFileDao.getAllDbModel().getRgbDbModel().get(201));
    }

    @Test
    public void getForRgbTestNotEmpty(){
        SimpleDbFileDao simpleDaoFile = new SimpleDbFileDao();
        simpleDaoFile.getAllDbModel().getRgbDbModel().put(101, asList("file loc 1"));
        simpleDaoFile.getAllDbModel().getRgbDbModel().put(102, asList("file loc 2"));

        assertEquals(asList("file loc 1"), simpleDaoFile.getForRgb(101));
    }


    @Test
    public void getForRgbTestEmpty(){
        SimpleDbFileDao simpleDaoFile = new SimpleDbFileDao();
        assertEquals(asList(), simpleDaoFile.getForRgb(101));
    }

}
