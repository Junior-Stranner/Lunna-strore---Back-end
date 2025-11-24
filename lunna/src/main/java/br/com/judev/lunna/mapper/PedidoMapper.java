package br.com.judev.lunna.mapper;

import br.com.judev.lunna.dto.PedidoDtoRequest;
import br.com.judev.lunna.dto.PedidoDtoResponse;
import br.com.judev.lunna.entity.Pedido;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Pedido toEntity(PedidoDtoRequest dto) {
        return mapper.map(dto, Pedido.class);
    }

    public static PedidoDtoResponse toDto(Pedido entity) {
        return mapper.map(entity, PedidoDtoResponse.class);
    }

    public static List<PedidoDtoResponse> toDtoList(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(PedidoMapper::toDto)
                .collect(Collectors.toList());
    }
}
