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

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex){
        ApiError apiError = new ApiError("Unauthenticated User  :" + ex.getMessage() , HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(apiError , HttpStatus.UNAUTHORIZED);

    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException ex){
        ApiError apiError = new ApiError("Invalid JWT Token :" + ex.getMessage() , HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(apiError , HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex){
        ApiError apiError = new ApiError("Invalid JWT Token :" + ex.getMessage() , HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(apiError , HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex){
        ApiError apiError = new ApiError("Invalid JWT Token :" + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(apiError , HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
