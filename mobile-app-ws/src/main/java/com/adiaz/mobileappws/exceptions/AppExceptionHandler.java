package com.adiaz.mobileappws.exceptions;

import com.adiaz.mobileappws.model.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyTypeException(Exception ex, WebRequest request) {
        String errorMsgDesc = ex.getLocalizedMessage()!=null ? ex.getLocalizedMessage() : ex.toString();
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMsgDesc);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
    public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request) {
        String errorMsgDesc = "SPECIFIC_EXCEPTION: " +  (ex.getLocalizedMessage()!=null ? ex.getLocalizedMessage() : ex.toString());
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMsgDesc);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
