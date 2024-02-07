package otyom.anketSpring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorType {
    //not found
    ADMIN_NOT_FOUND_EXCEPTION(1001,"Admin Not Found"),
    TOKEN_NOT_FOUND(4001,"Token not found"),


    //WRONG/HATALI
    WRONG_TOKEN(4002,"Yanlış token"),


    //exist
    EMAIL_ALREADY_EXSIST_EXCEPTION(2001,"Admin mevcut"),




    //NOT MATCH
    EMAIL_LOGIN_EXCEPTION(3001,"Email hatalı"),
    PASSWORD_NOT_MATCH(3002,"Password not match");












    int statusCode;
    String message;


}
