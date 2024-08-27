package net.ensah.ensahterrain.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import net.ensah.ensahterrain.dto.ErrorMessageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MatchException.class)
    public ResponseEntity<Object> handleMatchException(final MatchException ex, final HttpServletRequest request) {
        ErrorMessageResponse errorMessageResponse= new ErrorMessageResponse( new Date(),ex.getMessage());
        return new ResponseEntity<>(errorMessageResponse,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

   @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleRegisterException(final MethodArgumentNotValidException ex, final HttpServletRequest request) {
       Map<String,String> errors = new HashMap<>();
       for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
           errors.put(fieldError.getField(),fieldError.getDefaultMessage());
       }
        return new ResponseEntity<>(errors,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleUserException(final UserException ex, final HttpServletRequest request) {
        ErrorMessageResponse errorMessageResponse= new ErrorMessageResponse( new Date(),ex.getMessage());
        return new ResponseEntity<>(errorMessageResponse,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(final Exception ex, final HttpServletRequest request) {
        return new ResponseEntity<>(ex.getMessage(),new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
