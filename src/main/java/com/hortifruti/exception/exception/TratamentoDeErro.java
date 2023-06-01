package com.hortifruti.exception.exception;

import com.hortifruti.exception.dto.ErrosException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice //Servi para falar que essa classe, vai tratar os erros de exception.
public class TratamentoDeErro {

    @ExceptionHandler(NoSuchElementException.class) //Passamos o parametro que vai ser tratado
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build(); //Aqui retornamos o erro 404 notFound() que e mais especifico para esse erro.
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //Passamos o parametro que vai ser tratado
    public ResponseEntity tratarErro400(MethodArgumentNotValidException exception){  //Aqui estamos capturando a exception
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ErrosException::new).toList());

    }
}
