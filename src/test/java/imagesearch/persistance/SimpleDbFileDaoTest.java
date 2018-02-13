package imagesearch.persistance;

import imagesearch.persistance.model.AllDbModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

        FileOutputStream fileOutputStream = new FileOutputStream(SimpleDbFileDao.DB_MODEL_FILE_NAME);
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);

        oos.writeObject(allDbModel);
        oos.close();
        fileOutputStream.close();

        SimpleDbFileDao dao = new SimpleDbFileDao();
        assertEquals(allDbModel, dao.getAllDbModel());

        assertTrue(new File(SimpleDbFileDao.DB_MODEL_FILE_NAME).delete());
    }

    @Test
    public void saveDbFileTest(){
        SimpleDbFileDao simpleDbFileDao = new SimpleDbFileDao();
        assertNotNull(simpleDbFileDao.getAllDbModel());
        assertTrue(simpleDbFileDao.getAllDbModel().getRgbDbModel().isEmpty());

        simpleDbFileDao.saveDbFile();

        File file = new File(SimpleDbFileDao.DB_MODEL_FILE_NAME);
        assertTrue(file.exists());

        assertTrue(new File(SimpleDbFileDao.DB_MODEL_FILE_NAME).delete());
    }

}
