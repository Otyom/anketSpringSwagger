package otyom.anketSpring.Exception.userexceptions;

public class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException(){

    }
    public TokenNotFoundException(String message){
        super(message);
    }
}
