package br.com.judev.lunna.service;

import br.com.judev.lunna.dto.CategoriaDtoRequest;
import br.com.judev.lunna.dto.CategoriaDtoResponse;
import br.com.judev.lunna.entity.Categoria;
import br.com.judev.lunna.mapper.CategoriaMapper;
import br.com.judev.lunna.repositories.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    public CategoriaDtoResponse criarCategoria(CategoriaDtoRequest dto) {

        Categoria categoria = CategoriaMapper.toEntity(dto);
        categoriaRepository.save(categoria);

        return CategoriaMapper.toDto(categoria);
    }


    public CategoriaDtoResponse atualizarCategoria(Long id, CategoriaDtoRequest dto) {

        Categoria categoria = categoriaRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        categoria.setNome(dto.getNome());
        Categoria atualizada = categoriaRepository.save(categoria);

        return CategoriaMapper.toDto(atualizada);
    }


    public List<CategoriaDtoResponse> buscarTodas() {

        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaMapper::toDto)
                .toList();
    }

    public CategoriaDtoResponse buscarPorId(Long id) {

        Categoria categoria = categoriaRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        return CategoriaMapper.toDto(categoria);
    }


    public void deletarCategoria(Long id) {

        Categoria categoria = categoriaRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        categoriaRepository.delete(categoria);
    }
}
