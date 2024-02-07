package otyom.anketSpring.Exception.userexceptions;

public class PasswordLoginException extends RuntimeException{

    public PasswordLoginException(String message){
        super(message);
    }


    public PasswordLoginException(){
        super();
    }
}
