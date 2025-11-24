package br.com.judev.lunna.repositories;

import br.com.judev.lunna.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
