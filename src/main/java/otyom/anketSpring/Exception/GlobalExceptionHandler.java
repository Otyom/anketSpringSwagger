package otyom.anketSpring.Exception;

import jakarta.persistence.Table;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import otyom.anketSpring.Exception.userexceptions.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> emailAlreadyExsis(EmailLoginException emailLoginException){
        return new ResponseEntity<>(
                createMessage(ErrorType.EMAIL_ALREADY_EXSIST_EXCEPTION),
                HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(EmailLoginException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> emailLoginException(EmailAlreadyExistsException emailAlreadyExistsException){
        return new ResponseEntity<>(
                createMessage( ErrorType.EMAIL_ALREADY_EXSIST_EXCEPTION),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordLoginException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> passwordLoginException(PasswordLoginException passwordLoginException){
        return new ResponseEntity<>(
                createMessage(ErrorType.PASSWORD_NOT_MATCH)
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdminNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> adminNotFoundException(AdminNotFoundException adminNotFoundException){
        return new ResponseEntity<>(
                createMessage(ErrorType.ADMIN_NOT_FOUND_EXCEPTION)
                , HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(TokenNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> TokenNotFoundException(TokenNotFoundException notFoundException){
        return new ResponseEntity<>(
                createMessage(ErrorType.TOKEN_NOT_FOUND),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> WrongTokenException(WrongTokenException wrongTokenException){
        return new ResponseEntity<>(
                createMessage(ErrorType.WRONG_TOKEN),
                HttpStatus.BAD_REQUEST);
    }

































    public ErrorMessage createMessage (ErrorType errorType){
        return  ErrorMessage.builder()
                .message(errorType.message)
                .statusCode(errorType.statusCode)
                .build();
    }
}
