package imagesearch;

import imagesearch.image.CustomImageType;
import imagesearch.image.LocalImageStream;
import imagesearch.source.SourceImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ComparatorServiceTest {

    @Spy
    @InjectMocks
    private ComparatorService comparatorService;

    @Mock
    private SourceImageService sourceService;

    @Mock
    private TargetImageSourceService targetService;

    @Mock
    private ReportService reportService;

    @Test
    public void compareTestMatchFound(){
        final Report report = new Report("[possibly match] source: source location, target: target location");
        final CustomImageType sourceImage = mock(CustomImageType.class);
        final CustomImageType targetImage = mock(CustomImageType.class);
        final LocalImageStream localImageStream = mock(LocalImageStream.class);

        when(sourceImage.getAverageRgb()).thenReturn(100);

        when(sourceService.getNextImage()).thenReturn(sourceImage);
        when(targetService.getStreamForRgb(eq(100), anyInt())).thenReturn(localImageStream);

        when(localImageStream.hasNext()).thenReturn(true).thenReturn(false);
        when(localImageStream.getNext()).thenReturn(targetImage);

        when(sourceImage.getImageLocation()).thenReturn("source location");
        when(targetImage.getImageLocation()).thenReturn("target location");

        doReturn(true).when(comparatorService).compare(sourceImage, targetImage);

        comparatorService.compare(sourceService, targetService);

        verify(reportService).persistReport(report);
    }

    @Test
    public void compareTestMatchNotFound(){
        final CustomImageType sourceImage = mock(CustomImageType.class);
        final CustomImageType targetImage = mock(CustomImageType.class);
        final LocalImageStream localImageStream = mock(LocalImageStream.class);

        when(sourceImage.getAverageRgb()).thenReturn(100);

        when(sourceService.getNextImage()).thenReturn(sourceImage);
        when(targetService.getStreamForRgb(eq(100), anyInt())).thenReturn(localImageStream);

        when(localImageStream.hasNext()).thenReturn(true).thenReturn(false);
        when(localImageStream.getNext()).thenReturn(targetImage);

        doReturn(false).when(comparatorService).compare(sourceImage, targetImage);

        comparatorService.compare(sourceService, targetService);

        verify(reportService, never()).persistReport(any(Report.class));
    }

}
