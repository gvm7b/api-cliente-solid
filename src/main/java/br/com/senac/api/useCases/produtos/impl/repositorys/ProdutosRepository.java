package br.com.senac.api.useCases.produtos.impl.repositorys;

import br.com.senac.api.entitys.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
}
