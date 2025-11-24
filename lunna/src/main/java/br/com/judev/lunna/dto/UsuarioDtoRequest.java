package br.com.judev.lunna.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoRequest {

    private String email;
    private String nome;
    private String phoneNumber;
    private String senha;
    private String role;

    private EnderecoDtoRequest endereco;
}

