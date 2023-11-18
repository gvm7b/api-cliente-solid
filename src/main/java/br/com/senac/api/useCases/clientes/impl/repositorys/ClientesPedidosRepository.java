package br.com.senac.api.useCases.clientes.impl.repositorys;

import br.com.senac.api.entitys.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesPedidosRepository extends JpaRepository<Pedidos, Long> {
}
