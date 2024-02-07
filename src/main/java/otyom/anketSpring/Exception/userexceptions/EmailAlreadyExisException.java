package otyom.anketSpring.Exception.userexceptions;

public class EmailAlreadyExisException extends RuntimeException{
    public EmailAlreadyExisException(String message){
        super("Email zaten kayıtlı");
    }


    public EmailAlreadyExisException() {
        super();
    }

}
