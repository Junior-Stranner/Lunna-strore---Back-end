package br.com.judev.lunna.service;

import br.com.judev.lunna.dto.UsuarioDtoRequest;
import br.com.judev.lunna.dto.UsuarioDtoResponse;
import br.com.judev.lunna.entity.Usuario;
import br.com.judev.lunna.enums.UserRole;
import br.com.judev.lunna.mapper.UsuarioMapper;
import br.com.judev.lunna.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDtoResponse cadastrarUsuario(UsuarioDtoRequest usuarioDtoRequest) {
        UserRole role = UserRole.USER;

        if (usuarioDtoRequest.getRole() != null &&
                usuarioDtoRequest.getRole().equalsIgnoreCase("admin")) {
            role = UserRole.ADMIN;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDtoRequest.getNome());
        usuario.setEmail(usuarioDtoRequest.getEmail());
        usuario.setSenha(usuarioDtoRequest.getSenha());
        usuario.setRole(role);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return UsuarioMapper.toDto(usuarioSalvo);
    }

}
