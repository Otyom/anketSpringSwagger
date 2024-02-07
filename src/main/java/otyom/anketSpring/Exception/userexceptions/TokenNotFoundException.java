package otyom.anketSpring.Exception.userexceptions;

public class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException(){
        super();
    }
    public TokenNotFoundException(String message){
        super(message);
    }
}
