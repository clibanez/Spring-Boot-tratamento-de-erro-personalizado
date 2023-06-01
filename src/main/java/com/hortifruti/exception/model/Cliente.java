package com.hortifruti.exception.model;

import com.hortifruti.exception.dto.ClienteDTO;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;

    public Cliente(ClienteDTO clienteDTO){
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
        this.email = clienteDTO.getEmail();
    }
}
