package br.com.senac.api.useCases.produtos;

import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.produtos.domanis.ProdutosRequestDom;
import br.com.senac.api.useCases.produtos.domanis.ProdutosResponseDom;

import java.util.List;

public interface ProdutosBusiness {
    List<ProdutosResponseDom> carregarProdutos();
    ProdutosResponseDom criarProdutos (ProdutosRequestDom produtos) throws SenacException;
    ProdutosResponseDom atualizarProdutos(Long id, ProdutosRequestDom produtos) throws SenacException;
    void deletarProdutos(Long id);
}
