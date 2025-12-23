package com.codeverse.HospitalManagement.Exception;

public class UserNotFoundException extends RuntimeException{
     public UserNotFoundException(String message){
         super(message);
     }
}
