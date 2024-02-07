package otyom.anketSpring.exception.userexceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String message){
        super(message);
    }

    public EmailAlreadyExistsException(){super();}

}
