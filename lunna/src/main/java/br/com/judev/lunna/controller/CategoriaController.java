package br.com.judev.lunna.controller;

import br.com.judev.lunna.dto.CategoriaDtoRequest;
import br.com.judev.lunna.dto.CategoriaDtoResponse;
import br.com.judev.lunna.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaDtoResponse> criarCategoria(
            @Valid @RequestBody CategoriaDtoRequest dto) {

        return ResponseEntity.ok(categoriaService.criarCategoria(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDtoResponse> atualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaDtoRequest dto) {

        return ResponseEntity.ok(categoriaService.atualizarCategoria(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDtoResponse>> buscarTodas() {
        return ResponseEntity.ok(categoriaService.buscarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDtoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {

        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
