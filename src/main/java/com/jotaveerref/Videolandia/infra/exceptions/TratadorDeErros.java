package com.jotaveerref.Videolandia.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(ValidadorException.class)
    public ResponseEntity tratarErro400(ValidadorException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
