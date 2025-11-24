package br.com.judev.lunna.mapper;

import br.com.judev.lunna.dto.ItemPedidoDtoRequest;
import br.com.judev.lunna.dto.ItemPedidoDtoResponse;
import br.com.judev.lunna.entity.ItemPedido;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ItemPedidoMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static ItemPedido toEntity(ItemPedidoDtoRequest dto) {
        return mapper.map(dto, ItemPedido.class);
    }

    public static ItemPedidoDtoResponse toDto(ItemPedido entity) {
        return mapper.map(entity, ItemPedidoDtoResponse.class);
    }

    public static List<ItemPedidoDtoResponse> toDtoList(List<ItemPedido> itens) {
        return itens.stream()
                .map(ItemPedidoMapper::toDto)
                .collect(Collectors.toList());
    }
}
