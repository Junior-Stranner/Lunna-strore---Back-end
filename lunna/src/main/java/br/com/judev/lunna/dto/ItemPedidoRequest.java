package br.com.judev.lunna.dto;

import lombok.Data;

@Data
public class ItemPedidoRequest {

    private Long produtoId;
    private int quantidade;
}
