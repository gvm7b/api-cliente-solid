package br.com.senac.api.useCases.pedidosItens.impl;

import br.com.senac.api.entitys.Pedidos;
import br.com.senac.api.entitys.PedidosItens;
import br.com.senac.api.entitys.Produtos;
import br.com.senac.api.frameWork.annotions.Business;
import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.pedidosItens.PedidosItensBusiness;
import br.com.senac.api.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import br.com.senac.api.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import br.com.senac.api.useCases.pedidosItens.impl.mappers.PedidosItensMapper;
import br.com.senac.api.useCases.pedidosItens.impl.repositorys.PedidosItensPedidosRespository;
import br.com.senac.api.useCases.pedidosItens.impl.repositorys.PedidosItensProdutosRepository;
import br.com.senac.api.useCases.pedidosItens.impl.repositorys.PedidosItensRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class PedidosItensBusinessImpl implements PedidosItensBusiness {

    @Autowired
    private PedidosItensRepository pedidosItensRepository;

    @Autowired
    private PedidosItensPedidosRespository pedidosItensPedidosRespository;
    @Autowired
    private PedidosItensProdutosRepository pedidosItensProdutosRepository;


    @Override
    public List<PedidosItensResponseDom> carregarPedidosItens() {
        List<PedidosItens> pedidosItensList = pedidosItensRepository.findAll();

        List<PedidosItensResponseDom> out = pedidosItensList.stream()
                .map(PedidosItensMapper::pedidosItensToPedidosItensResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public PedidosItensResponseDom criarPedidosItens(PedidosItensRequestDom pedidoItens) throws SenacException {
        List<String> messages = this.validacaoPedidoItens(pedidoItens);

        if (!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Pedidos> pedido = pedidosItensPedidosRespository.findById(pedidoItens.getPedidoId());
        if (!pedido.isPresent()){
            throw new SenacException("Cliente não encontrado");
        }
        Optional<Produtos> produto = pedidosItensProdutosRepository.findById(pedidoItens.getProdutoId());
        if(!produto.isPresent()){
            throw new SenacException("Produto não encontrado");
        }

        PedidosItens pedidosItensRetorno = pedidosItensRepository.save(PedidosItensMapper.pedidosItensRequestDomToPedidosItens(pedidoItens, pedido.get(), produto.get()));

        return PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidosItensRetorno);
    }

    @Override
    public PedidosItensResponseDom atualizarPedidosItens(Long id, PedidosItensRequestDom pedidosItens) throws SenacException {
        List<String> messages = this.validacaoPedidoItens(pedidosItens);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }
        Optional<Pedidos> pedidos = pedidosItensPedidosRespository.findById(pedidosItens.getPedidoId());
        if (!pedidos.isPresent()){
            throw new SenacException("Pedido não encontrado!");
        }
        Optional<Produtos> produtos = pedidosItensProdutosRepository.findById(pedidosItens.getProdutoId());
        if(!produtos.isPresent()){
            throw new SenacException("Produto não encontrado");
        }
        Optional<PedidosItens> pedidosItensRetorno = pedidosItensRepository.findById(id).map(record -> {
            record.setQuantidade(pedidosItens.getQuantidade());
            record.setValorUnitario(pedidosItens.getValorUnitario());
            record.setPedidosId(pedidos.get());
            record.setProdutoId(produtos.get());

            return pedidosItensRepository.save(record);
        });
        if(pedidosItensRetorno.isPresent() == false){
            throw new SenacException("Pedido item não encontrado!");
        }
        PedidosItensResponseDom out = PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidosItensRetorno.get());

        return out;
    }

    @Override
    public void deletarPedidoItens(Long id) {
        pedidosItensRepository.deleteById(id);
    }


    public List<String> validacaoPedidoItens(PedidosItensRequestDom pedidoItens) {
        List<String> messages = new ArrayList<>();

        if (pedidoItens.getQuantidade() == null || pedidoItens.getQuantidade() < 1){
            messages.add("Quantidade não informado ou invalido!");
        }
        if(pedidoItens.getValorUnitario() == null || pedidoItens.getValorUnitario() < 1){
            messages.add("Valor unitario não informado ou invalido!");
        }
        if (pedidoItens.getPedidoId() == null || pedidoItens.getPedidoId() < 1){
            messages.add("PedidoId não informado ou valor invalido!");
        }
        if (pedidoItens.getProdutoId() == null || pedidoItens.getProdutoId() < 1){
            messages.add("ProdutoId não informado ou valor invalido!");
        }

        return messages;
    }
}
