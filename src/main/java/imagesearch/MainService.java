package imagesearch;

import imagesearch.source.SourceImageFactory;
import imagesearch.source.SourceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component
public class MainService{

    @Autowired
    private SourceImageFactory sourceImageFactory;

    @Autowired
    private ComparatorService comparatorService;

    @Autowired
    private TargetImageSourceService targetImageSourceService;

    public void work(final String args[]){

        List<SourceImageService> sourceServices =
                stream(args)
                .map(source -> sourceImageFactory.getSource(source))
                .collect(Collectors.toList());


        sourceServices
                .parallelStream()
                .forEach(srcSvc -> comparatorService.compare(srcSvc, targetImageSourceService));

    }
}
