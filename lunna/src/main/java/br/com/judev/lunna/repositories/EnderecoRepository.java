package br.com.judev.lunna.repositories;

import br.com.judev.lunna.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
