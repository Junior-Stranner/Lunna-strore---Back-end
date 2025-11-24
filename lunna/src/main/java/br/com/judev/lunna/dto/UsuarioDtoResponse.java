package br.com.judev.lunna.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDtoResponse {

    private Long id;
    private String email;
    private String nome;
    private String phoneNumber;
    private String role;

    private EnderecoDtoResponse endereco;

    private List<ItemPedidoDtoResponse> itens;
}
