package com.hortifruti.exception.dto;

import com.hortifruti.exception.model.Cliente;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {


    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String email;

    public ClienteDTO(Cliente cliente){
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
    }
}
