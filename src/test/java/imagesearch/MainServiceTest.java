package imagesearch;

import imagesearch.comparator.ComparatorService;
import imagesearch.source.SourceImageFactory;
import imagesearch.source.SourceImageService;
import imagesearch.source.TargetImageSourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainServiceTest {

    @Mock
    private SourceImageFactory sourceImageFactory;

    @Mock
    private TargetImageSourceService targetImageSourceService;

    @Mock
    private ComparatorService comparatorService;

    @InjectMocks
    private MainService mainService;

    @Test
    public void workTest(){
        final String sources[] = {"source1", "source2"};
        final SourceImageService source1Service = mock(SourceImageService.class);
        final SourceImageService source2Service = mock(SourceImageService.class);

        when(sourceImageFactory.getSource("source1")).thenReturn(source1Service);
        when(sourceImageFactory.getSource("source2")).thenReturn(source2Service);

        doNothing().when(comparatorService).compare(source1Service, targetImageSourceService);
        doNothing().when(comparatorService).compare(source2Service, targetImageSourceService);

        mainService.work(sources);

        verify(comparatorService).compare(source1Service, targetImageSourceService);
        verify(comparatorService).compare(source2Service, targetImageSourceService);
    }


}
