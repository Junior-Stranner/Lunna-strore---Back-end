package br.com.judev.lunna.mapper;

import br.com.judev.lunna.dto.ProdutoDtoRequest;
import br.com.judev.lunna.dto.ProdutoDtoResponse;
import br.com.judev.lunna.entity.Produto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Produto toEntity(ProdutoDtoRequest dto) {
        return mapper.map(dto, Produto.class);
    }

    public static ProdutoDtoResponse toDto(Produto entity) {
        return mapper.map(entity, ProdutoDtoResponse.class);
    }

    public static List<ProdutoDtoResponse> toDtoList(List<Produto> produtos) {
        return produtos.stream()
                .map(ProdutoMapper::toDto)
                .collect(Collectors.toList());
    }
}
