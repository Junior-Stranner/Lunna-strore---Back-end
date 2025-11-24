package br.com.judev.lunna.repositories;

import br.com.judev.lunna.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByNomeContainingOrDescricaoContaining(String texto, String texto1);
}
