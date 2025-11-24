package br.com.judev.lunna.mapper;

import br.com.judev.lunna.dto.UsuarioDtoRequest;
import br.com.judev.lunna.dto.UsuarioDtoResponse;
import br.com.judev.lunna.entity.Usuario;
import org.apache.catalina.User;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

public class UsuarioMapper {


    private static final ModelMapper mapper = new ModelMapper();

    public static Usuario toEntity(UsuarioDtoRequest dto) {
        return mapper.map(dto, Usuario.class);
    }

    public static UsuarioDtoResponse toDto(Usuario entity) {
        return mapper.map(entity, UsuarioDtoResponse.class);
    }

    public static List<UsuarioDtoResponse> toDtoList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::toDto)
                .collect(Collectors.toList());
    }
}
