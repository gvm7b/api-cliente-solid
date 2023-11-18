package br.com.senac.api.useCases.pedidos.impl.repositorys;

import br.com.senac.api.entitys.PedidosItens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosPedidosItensRepository extends JpaRepository<PedidosItens, Long> {
}
