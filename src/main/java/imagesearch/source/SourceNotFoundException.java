package imagesearch.source;

public class SourceNotFoundException extends RuntimeException {
    private String errorMessage;

    public SourceNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "SourceNotFoundException{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
