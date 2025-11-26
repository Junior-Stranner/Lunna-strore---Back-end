package br.com.judev.lunna.service;

import br.com.judev.lunna.dto.LoginRequest;
import br.com.judev.lunna.dto.UsuarioDtoRequest;
import br.com.judev.lunna.dto.UsuarioDtoResponse;
import br.com.judev.lunna.entity.Usuario;
import br.com.judev.lunna.enums.UserRole;
import br.com.judev.lunna.exception.InvalidCredentialsException;
import br.com.judev.lunna.mapper.UsuarioMapper;
import br.com.judev.lunna.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

        usuario.setEmail(usuarioDtoRequest.getEmail());
        usuario.setNome(usuarioDtoRequest.getNome());
        usuario.setPhoneNumber(usuarioDtoRequest.getPhoneNumber());
        usuario.setSenha(usuarioDtoRequest.getSenha());
        usuario.setRole(UserRole.valueOf(usuarioDtoRequest.getRole()));

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return UsuarioMapper.toDto(usuarioSalvo);
    }


    public UsuarioDtoResponse loginUser(LoginRequest loginRequest) {

        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Email não encontrado"));

        if (!loginRequest.getPassword().equals(usuario.getSenha())) {
            try {
                throw new InvalidCredentialsException("Senha incorreta");
            } catch (InvalidCredentialsException e) {
                throw new RuntimeException(e);
            }
        }
        return UsuarioMapper.toDto(usuario);
    }

    public List<UsuarioDtoResponse> getAllUsers() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return UsuarioMapper.toDtoList(usuarios);
    }

}
