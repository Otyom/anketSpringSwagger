package otyom.anketSpring.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorType {
    //not found
    ADMIN_NOT_FOUND_EXCEPTION(1001,"Admin Not Found"),
    TOKEN_NOT_FOUND(1002,"Token not found"),


    //WRONG/HATALI
    WRONG_TOKEN(1003,"Yanlış token"),


    //exist
    EMAIL_ALREADY_EXSIST_EXCEPTION(2001,"Admin mevcut"),



    //NOT MATCH
    PASSWORD_NOT_MATCH(2001,"Password not match");











    int statusCode;
    String message;


}
