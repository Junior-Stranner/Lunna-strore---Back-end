package br.com.judev.lunna.controller;

import br.com.judev.lunna.dto.EnderecoDtoRequest;
import br.com.judev.lunna.dto.EnderecoDtoResponse;
import br.com.judev.lunna.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<EnderecoDtoResponse> salvarOuAtualizar(
            @PathVariable Long usuarioId,
            @RequestBody EnderecoDtoRequest dto) {

        return ResponseEntity.ok(enderecoService.salvarOuAtualizar(usuarioId, dto));
    }
}
