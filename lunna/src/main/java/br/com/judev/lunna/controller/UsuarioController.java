package br.com.judev.lunna.controller;

import br.com.judev.lunna.dto.LoginRequest;
import br.com.judev.lunna.dto.UsuarioDtoRequest;
import br.com.judev.lunna.dto.UsuarioDtoResponse;
import br.com.judev.lunna.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDtoResponse> cadastrarUsuario(
            @Valid @RequestBody UsuarioDtoRequest usuarioDtoRequest) {

        UsuarioDtoResponse usuarioCriado = usuarioService.cadastrarUsuario(usuarioDtoRequest);
        return ResponseEntity.ok(usuarioCriado);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDtoResponse> login(
            @Valid @RequestBody LoginRequest loginRequest) {

        UsuarioDtoResponse usuarioLogado = usuarioService.loginUser(loginRequest);
        return ResponseEntity.ok(usuarioLogado);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDtoResponse>> getAllUsers() {
        List<UsuarioDtoResponse> usuarios = usuarioService.getAllUsers();
        return ResponseEntity.ok(usuarios);
    }
}
