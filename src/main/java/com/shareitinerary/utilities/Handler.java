package com.shareitinerary.utilities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shareitinerary.dto.Response;
import com.shareitinerary.exceptions.DatabaseError;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Response res = new Response();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        res.setMessage(errors.toString());
        return new ResponseEntity<Response>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DatabaseError.class)
    public ResponseEntity<?> handleDataAccessExceptions(
            DatabaseError e) {
        Response res = new Response();
        res.setMessage(e.getMessage());
        return new ResponseEntity<Response>(res, HttpStatus.BAD_REQUEST);
    }

}

