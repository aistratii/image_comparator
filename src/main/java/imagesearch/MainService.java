package imagesearch;

import imagesearch.comparator.ComparatorService;
import imagesearch.persistance.DataBaseService;
import imagesearch.source.SourceImageFactory;
import imagesearch.source.SourceImageService;
import imagesearch.source.TargetImageSourceService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

@Component
public class MainService{

    @Autowired
    private SourceImageFactory sourceImageFactory;

    @Autowired
    private ComparatorService comparatorService;

    @Autowired
    private TargetImageSourceService targetImageSourceService;

    @Autowired
    private DataBaseService dataBaseService;

    public void work(final String args[]){

        List<Pair<String, Integer>> paramsAndPositions = parse(args);


        List<SourceImageService> sourceServices =
                stream(args)
                .map(source -> sourceImageFactory.getSource(source))
                .collect(Collectors.toList());


        sourceServices
                .parallelStream()
                .forEach(srcSvc -> comparatorService.compare(srcSvc, targetImageSourceService));

    }

    public List<Pair<String, Integer>> parse(String[] args) {
        List<Pair<String, Integer>> paramMetaData = new ArrayList<>();

        //String longInputStream = stream(args).reduce((s, o) -> s + 0);

        asList("-load", "-compare");

        return paramMetaData;
    }
}
