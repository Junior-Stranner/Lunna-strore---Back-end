package br.com.judev.lunna.mapper;

import br.com.judev.lunna.dto.CategoriaDtoRequest;
import br.com.judev.lunna.dto.CategoriaDtoResponse;
import br.com.judev.lunna.entity.Categoria;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Categoria toEntity(CategoriaDtoRequest dto) {
        return mapper.map(dto, Categoria.class);
    }

    public static CategoriaDtoResponse toDto(Categoria entity) {
        return mapper.map(entity, CategoriaDtoResponse.class);
    }

    public static List<CategoriaDtoResponse> toDtoList(List<Categoria> categorias) {
        return categorias.stream()
                .map(CategoriaMapper::toDto)
                .collect(Collectors.toList());
    }
}
