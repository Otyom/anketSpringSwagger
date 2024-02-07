package otyom.anketSpring.Exception;

import lombok.Builder;

@Builder
public class ErrorMessage {
    int statusCode;
    String message;


}
