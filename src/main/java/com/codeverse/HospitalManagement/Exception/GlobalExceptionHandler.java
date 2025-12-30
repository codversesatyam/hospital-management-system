package com.codeverse.HospitalManagement.Exception;

import com.codeverse.HospitalManagement.error.ApiError;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex){
        ApiError apiError = new ApiError("Username not find with username :" + ex.getMessage() , HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(apiError , apiError.getHttpStatus());
    }


}
