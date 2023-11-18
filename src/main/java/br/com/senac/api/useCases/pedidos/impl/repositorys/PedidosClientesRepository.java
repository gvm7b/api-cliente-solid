package br.com.senac.api.useCases.pedidos.impl.repositorys;

import br.com.senac.api.entitys.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosClientesRepository extends JpaRepository<Clientes, Long> {
}
