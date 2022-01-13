package exceptions;

public class ReciepeNotFoundException extends RuntimeException{
    public ReciepeNotFoundException(String message){
        super(message);
    }
}
