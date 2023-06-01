package com.hortifruti.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrosException {

    private String campo;
    private String mensagem;

    public ErrosException(FieldError erro){
        this.campo = erro.getField();
        this.mensagem = erro.getDefaultMessage();
    }
}
