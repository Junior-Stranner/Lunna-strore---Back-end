package br.com.judev.lunna.mapper;

import br.com.judev.lunna.dto.EnderecoDtoRequest;
import br.com.judev.lunna.dto.EnderecoDtoResponse;
import br.com.judev.lunna.entity.Endereco;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EnderecoMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Endereco toEntity(EnderecoDtoRequest dto) {
        return mapper.map(dto, Endereco.class);
    }

    public static EnderecoDtoResponse toDto(Endereco entity) {
        return mapper.map(entity, EnderecoDtoResponse.class);
    }

    public static List<EnderecoDtoResponse> toDtoList(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(EnderecoMapper::toDto)
                .collect(Collectors.toList());
    }
}
