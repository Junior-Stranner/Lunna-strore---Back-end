package br.com.judev.lunna.repositories;

import br.com.judev.lunna.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
