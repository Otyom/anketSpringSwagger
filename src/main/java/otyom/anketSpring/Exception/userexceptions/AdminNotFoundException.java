package otyom.anketSpring.Exception.userexceptions;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(String message){
        super(message);
    }

    public AdminNotFoundException(){
        super();
    }

}
