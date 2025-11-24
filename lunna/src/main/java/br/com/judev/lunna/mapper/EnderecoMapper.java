package br.com.judev.lunna.mapper;

import br.com.judev.lunna.dto.UsuarioDtoRequest;
import br.com.judev.lunna.dto.UsuarioDtoResponse;
import br.com.judev.lunna.entity.Usuario;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EnderecoMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Usuario toEntity(UsuarioDtoRequest dto) {
        return mapper.map(dto, Usuario.class);
    }

    public static UsuarioDtoResponse toDto(User entity) {
        return mapper.map(entity, UsuarioDtoResponse.class);
    }

    public static List<UsuarioDtoResponse> toDtoList(List<User> users) {
        return users.stream()
                .map(UsuarioMapper::toDto)
                .collect(Collectors.toList());
    }
}
