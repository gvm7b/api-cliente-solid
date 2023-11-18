package br.com.senac.api.useCases.pedidosItens.impl.repositorys;

import br.com.senac.api.entitys.PedidosItens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosItensRepository extends JpaRepository<PedidosItens, Long> {
}
