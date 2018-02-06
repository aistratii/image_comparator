package imagesearch.image;

public class LocalImageStream {
    public boolean hasNext() {
        return false;
    }

    public CustomImageType getNext() {
        throw new UnsupportedOperationException();
    }
}
