package imagesearch.image;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Stack;

public class LocalImageStream {
    private Stack<String> fileLocations;

    public LocalImageStream(List<String> fileLocations) {
        this.fileLocations = new Stack<>();

        fileLocations.forEach(lctn -> this.fileLocations.push(lctn));
    }

    public boolean hasNext() {
        return !fileLocations.isEmpty();
    }

    public CustomImageType getNext() {
        throw new NotImplementedException();
    }
}
