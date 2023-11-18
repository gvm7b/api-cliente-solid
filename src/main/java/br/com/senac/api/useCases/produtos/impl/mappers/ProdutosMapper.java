package br.com.senac.api.useCases.produtos.impl.mappers;

import br.com.senac.api.entitys.Produtos;
import br.com.senac.api.useCases.produtos.domanis.ProdutosRequestDom;
import br.com.senac.api.useCases.produtos.domanis.ProdutosResponseDom;

public class ProdutosMapper {

    public static ProdutosResponseDom produtosToProdutosResponseDom(Produtos produtos){
        ProdutosResponseDom out = new ProdutosResponseDom();
        out.setId(produtos.getId());
        out.setNome(produtos.getNome());
        out.setDescricao(produtos.getDescricao());

        return out;
    }

    public static Produtos produtosRequestDomToProdutos(ProdutosRequestDom produtosRequestDom){
        Produtos out = new Produtos();
        out.setNome(produtosRequestDom.getNome());
        out.setDescricao(produtosRequestDom.getDescricao());

        return out;

    }
}
