package br.com.judev.lunna.repositories;

import br.com.judev.lunna.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
