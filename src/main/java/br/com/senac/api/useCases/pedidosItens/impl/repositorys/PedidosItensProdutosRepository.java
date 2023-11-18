package br.com.senac.api.useCases.pedidosItens.impl.repositorys;

import br.com.senac.api.entitys.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidosItensProdutosRepository extends JpaRepository<Produtos, Long> {
    @Query("SELECT p.nome FROM pedidos_itens pi2 \n" +
            "JOIN produto p ON p.id = pi2.produtoId.id\n" +
            "WHERE pi2.produtoId = :id")
    public List<Produtos> carregarProdutosPedidosItensId(@Param("id") Long id);
}
