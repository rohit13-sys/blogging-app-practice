package com.example.blogging.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFound ex){
        String msg=ex.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<Object> userAlreadyExistsException(UserAlreadyExists ex){
        String msg=ex.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> resp=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName=((FieldError)error).getField();
            String errorMsg=error.getDefaultMessage();
            resp.put(fieldName,errorMsg);
        });

        return new ResponseEntity<>(resp, HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<Object> categoryNotFound(CategoryNotFound ex){
        String msg=ex.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CaregoryAlreadyExists.class)
    public ResponseEntity<Object> caregoryAlreadyExists(CaregoryAlreadyExists ex){
        String msg=ex.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Object> postNotFoundException(PostNotFoundException ex){
        String msg=ex.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
    }
}
