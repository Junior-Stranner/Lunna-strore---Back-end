package br.com.judev.lunna.repositories;

import br.com.judev.lunna.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  Optional<Usuario> findByEmail(@NotBlank(message = "Email is required") String email);
}
