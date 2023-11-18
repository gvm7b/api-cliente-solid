package br.com.senac.api.useCases.produtos.impl;

import br.com.senac.api.entitys.Produtos;
import br.com.senac.api.frameWork.annotions.Business;
import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.frameWork.utils.StringUtil;
import br.com.senac.api.useCases.produtos.ProdutosBusiness;
import br.com.senac.api.useCases.produtos.domanis.ProdutosRequestDom;
import br.com.senac.api.useCases.produtos.domanis.ProdutosResponseDom;
import br.com.senac.api.useCases.produtos.impl.mappers.ProdutosMapper;
import br.com.senac.api.useCases.produtos.impl.repositorys.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class ProdutosBusinessImpl implements ProdutosBusiness {

    @Autowired
    private ProdutosRepository produtosRepository;


    @Override
    public List<ProdutosResponseDom> carregarProdutos() {

        List<Produtos> produtosList = produtosRepository.findAll();

        List<ProdutosResponseDom> out = produtosList.stream()
                .map(ProdutosMapper::produtosToProdutosResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public ProdutosResponseDom criarProdutos(ProdutosRequestDom produtos) throws SenacException {
        List<String> messages = this.validacaoProduto(produtos);

        if (!messages.isEmpty()){
            throw new SenacException(messages);
        }
        Produtos produtosRetorno = ProdutosMapper.produtosRequestDomToProdutos(produtos);
        Produtos resultProdutos = produtosRepository.save(produtosRetorno);

        ProdutosResponseDom out = ProdutosMapper.produtosToProdutosResponseDom(resultProdutos);

        return out;
    }

    @Override
    public ProdutosResponseDom atualizarProdutos(Long id, ProdutosRequestDom produtos) throws SenacException {
        List<String> messages = this.validacaoProduto(produtos);

        if (!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Produtos> produtosRetorno = produtosRepository.findById(id).map(record -> {
            record.setNome(produtos.getNome());
            record.setDescricao(produtos.getDescricao());

            return produtosRepository.save(record);
        });

        if (!produtosRetorno.isPresent()){
            throw new SenacException("Produtos Informado não existe!");
        }

        ProdutosResponseDom out = ProdutosMapper.produtosToProdutosResponseDom(produtosRetorno.get());

        return out;
    }

    @Override
    public void deletarProdutos(Long id) {
        produtosRepository.deleteById(id);
    }

    private List<String> validacaoProduto(ProdutosRequestDom produtos){
        List<String> messages = new ArrayList<>();

        if(StringUtil.validarString(produtos.getNome())){
            messages.add("Produto informado não possui nome!");
        }
        if(StringUtil.validarString(produtos.getDescricao())){
            messages.add("Produto informado não possui descrição!");
        }

        return messages;
    }
}
