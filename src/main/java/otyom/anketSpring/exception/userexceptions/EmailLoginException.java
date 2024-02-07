package otyom.anketSpring.exception.userexceptions;

public class EmailLoginException extends RuntimeException{
    public EmailLoginException(String message){
        super(message);
    }

    public EmailLoginException(){
        super();
    }
}
