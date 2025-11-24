package br.com.judev.lunna.dto;

import lombok.Data;

import java.util.List;

@Data
public class PedidoDtoRequest {

    private Long usuarioId;
    private List<ItemPedidoDtoRequest> itens;
}
