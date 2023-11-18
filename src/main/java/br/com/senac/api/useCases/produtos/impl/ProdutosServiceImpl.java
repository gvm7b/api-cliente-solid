package br.com.senac.api.useCases.produtos.impl;

import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.produtos.ProdutosService;
import br.com.senac.api.useCases.produtos.domanis.ProdutosRequestDom;
import br.com.senac.api.useCases.produtos.domanis.ProdutosResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosServiceImpl implements ProdutosService {

    @Autowired
    private ProdutosBusinessImpl produtosBusiness;

    @Override
    public List<ProdutosResponseDom> carregarProdutos() {
        return produtosBusiness.carregarProdutos();
    }

    @Override
    public ProdutosResponseDom criarProdutos(ProdutosRequestDom produtos) throws SenacException {
        return produtosBusiness.criarProdutos(produtos);
    }

    @Override
    public ProdutosResponseDom atualizarProdutos(Long id, ProdutosRequestDom produtos) throws SenacException {
        return produtosBusiness.atualizarProdutos(id, produtos);
    }

    @Override
    public void deletarProdutos(Long id) {
        produtosBusiness.deletarProdutos(id);
    }
}
