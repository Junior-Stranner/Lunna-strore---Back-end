package br.com.judev.lunna.controller;

import br.com.judev.lunna.dto.ProdutoDtoRequest;
import br.com.judev.lunna.dto.ProdutoDtoResponse;
import br.com.judev.lunna.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping(
            value = "/categoria/{categoriaId}",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }
    )
    public ResponseEntity<ProdutoDtoResponse> criarProduto(
            @PathVariable Long categoriaId,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem,
            @RequestPart(value = "dados", required = false) ProdutoDtoRequest dtoMultipart,
            @RequestBody(required = false) ProdutoDtoRequest dtoJson
    ) {

        ProdutoDtoRequest dados = dtoMultipart != null ? dtoMultipart : dtoJson;

        ProdutoDtoResponse response = produtoService.criarProduto(categoriaId, imagem, dados);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{produtoId}")
    public ResponseEntity<ProdutoDtoResponse> atualizarProduto(
            @PathVariable Long produtoId,
            @RequestParam(value = "categoriaId", required = false) Long categoriaId,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem,
            @RequestPart("dados") ProdutoDtoRequest dto) {

        ProdutoDtoResponse response = produtoService.atualizarProduto(produtoId, categoriaId, imagem, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long produtoId) {
        produtoService.deletarProduto(produtoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoDtoResponse> buscarPorId(@PathVariable Long produtoId) {
        return ResponseEntity.ok(produtoService.buscarPorId(produtoId));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDtoResponse>> buscarTodos() {
        return ResponseEntity.ok(produtoService.buscarTodos());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProdutoDtoResponse>> buscarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(produtoService.buscarPorCategoria(categoriaId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProdutoDtoResponse>> buscarPorTexto(@RequestParam String texto) {
        return ResponseEntity.ok(produtoService.buscarPorTexto(texto));
    }
}
