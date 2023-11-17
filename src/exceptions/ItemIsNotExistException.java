package exceptions;

public class ItemIsNotExistException extends Exception{
    public ItemIsNotExistException() {
    }

    public ItemIsNotExistException(String message) {
        super(message);
    }
}
