package otyom.anketSpring.Exception.userexceptions;
public class WrongTokenException extends RuntimeException {

        public WrongTokenException(String message){
        super(message);
    }

    public WrongTokenException(){
        super();
    }


}