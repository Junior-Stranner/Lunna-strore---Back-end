package br.com.judev.lunna.repositories;

import br.com.judev.lunna.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
