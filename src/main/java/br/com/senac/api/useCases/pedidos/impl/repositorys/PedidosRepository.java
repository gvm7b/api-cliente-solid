package br.com.senac.api.useCases.pedidos.impl.repositorys;

import br.com.senac.api.entitys.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
}
