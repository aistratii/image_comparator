package imagesearch;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportService {
    private static List<Report> reports;

    public List<Report> getReports() {
        return reports;
    }

    public void persistReport(Report any) {

    }
}
