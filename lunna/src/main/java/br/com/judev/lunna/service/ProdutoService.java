package br.com.judev.lunna.service;

import br.com.judev.lunna.dto.ProdutoDtoRequest;
import br.com.judev.lunna.dto.ProdutoDtoResponse;
import br.com.judev.lunna.entity.Categoria;
import br.com.judev.lunna.entity.Produto;
import br.com.judev.lunna.mapper.ProdutoMapper;
import br.com.judev.lunna.repositories.CategoriaRepository;
import br.com.judev.lunna.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ProdutoDtoResponse criarProduto(Long categoriaId, MultipartFile imagem, ProdutoDtoRequest dto) {

        Categoria categoria = categoriaRepository.findById(Math.toIntExact(categoriaId))
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        Produto produto = ProdutoMapper.toEntity(dto);
        produto.setCategoria(categoria);

        if (imagem != null && !imagem.isEmpty()) {
            produto.setImagemUrl("temp");
        }
        produtoRepository.save(produto);
        return ProdutoMapper.toDto(produto);
    }


    public ProdutoDtoResponse atualizarProduto(Long produtoId, Long categoriaId, MultipartFile imagem, ProdutoDtoRequest dto) {

        Produto produto = produtoRepository.findById(Math.toIntExact(produtoId))
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        if (categoriaId != null) {
            Categoria categoria = categoriaRepository.findById(Math.toIntExact(categoriaId))
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
            produto.setCategoria(categoria);
        }

        // Atualizar campos básicos do produto
        if (dto.getNome() != null) produto.setNome(dto.getNome());
        if (dto.getDescricao() != null) produto.setDescricao(dto.getDescricao());
        if (dto.getPreco() != null) produto.setPreco(dto.getPreco());

        // Atualizar imagem caso enviada
        if (imagem != null && !imagem.isEmpty()) {
            // TODO: upload real futuramente
            produto.setImagemUrl("nova_url_temporaria");
        }

        Produto produtoAtualizado = produtoRepository.save(produto);

        return ProdutoMapper.toDto(produtoAtualizado);
    }


    public void deletarProduto(Long produtoId) {
        Produto produto = produtoRepository.findById(Math.toIntExact(produtoId))
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produtoRepository.delete(produto);

    }

    public ProdutoDtoResponse buscarPorId(Long produtoId) {
        Produto produto = produtoRepository.findById(Math.toIntExact(produtoId))
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        return ProdutoMapper.toDto(produto);
    }

    @Override
    public List<ProdutoDtoResponse> buscarTodos() {
        List<Produto> produtos = produtoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return ProdutoMapper.toDtoList(produtos);
    }

    public List<ProdutoDtoResponse> buscarPorCategoria(Long categoriaId) {
        List<Produto> produtos = produtoRepository.findByCategoriaId(categoriaId);

        if (produtos.isEmpty()) {
            throw new EntityNotFoundException("Nenhum produto encontrado para esta categoria");
        }

        return ProdutoMapper.toDtoList(produtos);
    }

    public List<ProdutoDtoResponse> buscarPorTexto(String texto) {
        List<Produto> produtos =
                produtoRepository.findByNomeContainingOrDescricaoContaining(texto, texto);

        if (produtos.isEmpty()) {
            throw new EntityNotFoundException("Nenhum produto encontrado");
        }

        return ProdutoMapper.toDtoList(produtos);
    }

}
