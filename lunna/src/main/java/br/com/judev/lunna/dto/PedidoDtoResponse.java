package br.com.judev.lunna.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDtoResponse {

    private Long id;
    private BigDecimal total;
    private String status;
    private Long usuarioId;

    private LocalDateTime createdAt;

    private List<ItemPedidoDtoResponse> itens;
}
