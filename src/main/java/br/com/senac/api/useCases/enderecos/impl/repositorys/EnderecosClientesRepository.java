package br.com.senac.api.useCases.enderecos.impl.repositorys;

import br.com.senac.api.entitys.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecosClientesRepository extends JpaRepository<Clientes, Long> {
}
