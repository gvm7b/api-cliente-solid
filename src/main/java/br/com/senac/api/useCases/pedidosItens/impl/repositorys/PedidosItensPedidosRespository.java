package br.com.senac.api.useCases.pedidosItens.impl.repositorys;

import br.com.senac.api.entitys.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosItensPedidosRespository extends JpaRepository<Pedidos, Long> {
}
