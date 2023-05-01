package com.willi.twitter.exceptions;

import com.willi.twitter.controller.dto.error.ErrorDetailsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNameAlreadyExistExeption.class)
    public ResponseEntity<?> handleUserNameAlreadyExistException(UserNameAlreadyExistExeption ex) {
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO("Conflict: " + ex.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetailsDTO);
    }

    @ExceptionHandler(EmailAlreadyExistExeption.class)
    public ResponseEntity<?> handleEmailAlreadyExistException(EmailAlreadyExistExeption ex) {
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO("Conflict: " + ex.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetailsDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex){
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO(ex.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetailsDTO);
    }

    @ExceptionHandler(UserNameAndEmailAlreadyExistException.class)
    public ResponseEntity<?> hanldeUserNameAndEmailAlreadyExistException(UserNameAndEmailAlreadyExistException ex){
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO("Conflict: " + ex.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetailsDTO);
    }

}
