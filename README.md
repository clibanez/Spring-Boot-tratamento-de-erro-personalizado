# Tratamento de Erros no Spring

Tratar erros relacionados a exceções geradas ao enviar uma requisição para um endpoint em uma aplicação Spring.Temos que lidar 
com esses erros de forma adequada, fornecendo respostas claras e úteis para os clientes.Isso garante que o cliente receba
resposta apropriada em caso de erros, 

# Para começar

Exemplo 1:
```json
{
    "timestamp": "2023-05-31T00:56:31.021+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "trace": "org.springframework.dao.InvalidDataAccessApiUsageException: The given id must not be null\n\tat \
    org.springframework.orm.jpa.EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(EntityManagerFactoryUtils.java:371)\n\tat 
    org.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:234)\n\tat 
    org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.translateExceptionIfPossible(AbstractEntityManagerFactoryBean.java:550)\n\tat 
    org.springframework.dao.support.ChainedPersistenceExceptionTranslator.translateExceptionIfPossible(ChainedPersistenceExceptionTranslator.
    java:61)\n\tat ..."
}
```



Ao enviar o trace (rastreamento) completo de exceções não é uma boa prática, pois pode expor nomes de classes, métodos e outras 
informações sensíveis. Isso pode representar uma brecha de segurança e expor informações desnecessárias.
Para desativar o trace,
Adicione a seguinte linha ao seu arquivo application.properties:
* server.error.include-stacktrace=never

# Fazendo o tratamento de erro na nossa api

exemplo 2: 
Vamos alterar o código de erro de 500 para 404. Para fazer essa correção, você pode seguir os passos do método:

(```
@ExceptionHandler(NoSuchElementException.class) //Passamos o parametro que vai ser tratado
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build(); //Aqui retornamos o erro 404 notFound() que e mais especifico para esse erro.
    }
    ```):
   
# Fazendo o tratamento de erro personalizado
    
Exemplo 3
Criamos uma classe dto para receber os campos que queremos, e uma função que retonar esses erros personalizados.

classe DTO
```package com.hortifruti.exception.dto;

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
````
Método de tratamento
```
 @ExceptionHandler(MethodArgumentNotValidException.class) //Passamos o parametro que vai ser tratado
    public ResponseEntity tratarErro400(MethodArgumentNotValidException exception){  //Aqui estamos capturando a exception
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ErrosException::new).toList());
    }
    
