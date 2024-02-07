package otyom.anketSpring.Exception;

import lombok.Builder;

@Builder
public class ErrorMessage {
    public int statusCode;
    public String message;


}
