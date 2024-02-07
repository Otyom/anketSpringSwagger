package otyom.anketSpring.exception.userexceptions;
public class WrongTokenException extends RuntimeException {

        public WrongTokenException(String message){
        super(message);
    }

    public WrongTokenException(){
            super();
    }


}
