package com.rp.interview_rp.controller.exceptions;

import com.rp.interview_rp.model.exceptions.NotFoundException;
import com.rp.interview_rp.model.exceptions.NullEntityException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerHandlerException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundIdError> handlerEmployeeNotFound(NotFoundException e,
                                                                   HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                new NotFoundIdError(
                        Instant.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "Object not found",
                        e.getMessage(),
                        request.getRequestURI()));
    }

    @ExceptionHandler({NullEntityException.class, NullPointerException.class})
    public ResponseEntity<InvalidDtoBody> handlerEmployeeInvalidBody(NullEntityException e,
                                                                     HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(
                new InvalidDtoBody(
                        Instant.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Invalid body request",
                        e.getMessage(),
                        request.getRequestURI()));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        return buildResponse("Invalid param request", "The parameter passed by the request contains errors",
                status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        return buildResponse("Invalid param request", "The parameter passed by the request contains errors",
                status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponse("Error in body request", "Request body contains errors", status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponse("Error in body request", "Request body contains errors", status);
    }

    private ResponseEntity<Object> buildResponse(String erro, String message, HttpStatus status) {
        return ResponseEntity.status(status.value()).body(NotFoundIdError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(erro)
                .message(message)
                .path("")
                .build());
    }
}