package br.com.judev.lunna.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDtoResponse {

    private Long id;
    private int quantidade;
    private BigDecimal preco;
    private String status;

    private UsuarioDtoResponse usuario;
    private ProdutoDtoResponse produto;

    private LocalDateTime criadoEm;
}
