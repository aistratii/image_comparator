package imagesearch;


import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.List;

@Component
public class ReportService {
    private final String reportDirectory;
    private int counter = 0;

    public ReportService(String reportDirectory){
        this.reportDirectory = reportDirectory;
    }

    synchronized public void persistReport(Report report) {
        try {
            FileOutputStream fout = new FileOutputStream(reportDirectory + File.separator + "report_" + counter++ + ".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(report);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
