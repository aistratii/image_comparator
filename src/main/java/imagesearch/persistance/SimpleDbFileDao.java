package imagesearch.persistance;

import imagesearch.image.CustomImageType;
import imagesearch.persistance.model.AllDbModel;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class SimpleDbFileDao implements Dao {
    private AllDbModel allDbModel;
    public final static String DB_MODEL_FILE_NAME = "db.db";

    public SimpleDbFileDao() {
        readFile();
    }

    private void readFile() {
        try {
            File file = new File(DB_MODEL_FILE_NAME);

            if(file.exists()){
                FileInputStream fis = new FileInputStream(DB_MODEL_FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);

                allDbModel = (AllDbModel) ois.readObject();

                ois.close();
                fis.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            if (allDbModel == null)
                allDbModel = new AllDbModel();
        }
    }

    public void saveDbFile(){
        try {
            FileOutputStream fos = new FileOutputStream(DB_MODEL_FILE_NAME);
            ObjectOutputStream ous = new ObjectOutputStream(fos);
            ous.writeObject(allDbModel);
            fos.close();
            ous.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(CustomImageType customImageType){
        throw new UnsupportedOperationException();
    }

    protected AllDbModel getAllDbModel(){
        return allDbModel;
    }

    @Override
    public List<String> getForRgb(int averageRgb) {
        throw new UnsupportedOperationException();
    }
}
