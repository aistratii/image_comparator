package imagesearch;

import imagesearch.comparator.ComparatorCache;
import imagesearch.comparator.ComparatorService;
import imagesearch.comparator.ImageComparator;
import imagesearch.source.SourceImageFactory;
import imagesearch.source.TargetImageSourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ImageSearchConfig {

    @Bean
    public MainService mainService(){
        return new MainService();
    }

    @Bean
    public ReportService reportService(){
        return new ReportService("D:\\tmp\\reports");
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

    @Scope("prototype")
    @Bean
    public ImageComparator imageComparator(ComparatorCache comparatorCache){
        return new ImageComparator(comparatorCache);
    }

    @Scope("prototype")
    @Bean
    public ComparatorCache comparatorCache(){
        return new ComparatorCache();
    }
}
