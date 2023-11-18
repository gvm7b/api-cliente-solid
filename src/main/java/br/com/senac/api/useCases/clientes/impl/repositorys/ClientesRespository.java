package br.com.senac.api.useCases.clientes.impl.repositorys;

import br.com.senac.api.entitys.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRespository extends JpaRepository<Clientes, Long> {
}
