package exceptions;

public class ObjectIsEmptyException extends Exception{
    public ObjectIsEmptyException() {
    }

    public ObjectIsEmptyException(String message) {
        super(message);
    }
}
