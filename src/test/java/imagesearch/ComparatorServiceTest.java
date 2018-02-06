package imagesearch;

import imagesearch.comparator.ComparatorService;
import imagesearch.comparator.ImageComparator;
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

    @InjectMocks
    private ComparatorService comparatorService;

    @Mock
    private SourceImageService sourceService;

    @Mock
    private TargetImageSourceService targetService;

    @Mock
    private ReportService reportService;

    @Mock
    private ImageComparator imageComparator;

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

        when(imageComparator.compare(sourceImage, targetImage)).thenReturn(true);

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

        when(imageComparator.compare(sourceImage, targetImage)).thenReturn(false);

        comparatorService.compare(sourceService, targetService);

        verify(reportService, never()).persistReport(any(Report.class));
    }

}
