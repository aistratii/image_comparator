package imagesearch;

import imagesearch.source.SourceImageFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageSearchConfig {

    @Bean
    public MainService mainService(){
        return new MainService();
    }

    @Bean
    public ReportService reportService(){
        return new ReportService();
    }

    @Bean
    public SourceImageFactory sourceImageFactory(){
        return new SourceImageFactory();
    }

    @Bean
    public TargetImageSourceService targetImageSourceService (){
        return new TargetImageSourceService();
    }

    @Bean
    public ComparatorService comparatorService(){
        return new ComparatorService();
    }
}
