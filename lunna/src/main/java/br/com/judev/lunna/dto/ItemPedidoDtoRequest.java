package br.com.judev.lunna.dto;

import lombok.Data;

@Data
public class ItemPedidoDtoRequest {

    private Long produtoId;
    private int quantidade;
}
