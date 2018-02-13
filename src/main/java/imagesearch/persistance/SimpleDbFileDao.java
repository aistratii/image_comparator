package imagesearch.persistance;

import imagesearch.image.CustomImageType;
import imagesearch.persistance.model.AllDbModel;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void update(CustomImageType customImageType){
        if (allDbModel.getRgbDbModel().get(customImageType.getAverageRgb()) == null)
            allDbModel.getRgbDbModel().put(customImageType.getAverageRgb(), new ArrayList<>());

        allDbModel.getRgbDbModel().get(customImageType.getAverageRgb()).add(customImageType.getImageLocation());
    }

    protected AllDbModel getAllDbModel(){
        return allDbModel;
    }

    @Override
    public List<String> getForRgb(int averageRgb) {
        List<String> resultFileNames = allDbModel.getRgbDbModel().get(averageRgb);

        return resultFileNames == null ? new ArrayList<>() : resultFileNames;
    }
}
