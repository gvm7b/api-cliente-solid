package br.com.senac.api.useCases.pedidos.impl;

import br.com.senac.api.entitys.Clientes;
import br.com.senac.api.entitys.Pedidos;
import br.com.senac.api.frameWork.annotions.Business;
import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.enderecos.impl.repositorys.EnderecosRepository;
import br.com.senac.api.useCases.pedidos.PedidosBusiness;
import br.com.senac.api.useCases.pedidos.PedidosService;
import br.com.senac.api.useCases.pedidos.domanis.PedidosRequestDom;
import br.com.senac.api.useCases.pedidos.domanis.PedidosResponseDom;
import br.com.senac.api.useCases.pedidos.impl.mappers.PedidosMapper;
import br.com.senac.api.useCases.pedidos.impl.repositorys.PedidosClientesRepository;
import br.com.senac.api.useCases.pedidos.impl.repositorys.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class PedidosBusinessImpl implements PedidosBusiness {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private PedidosClientesRepository pedidosClientesRepository;


    @Override
    public List<PedidosResponseDom> carregarPedidos() {
        List<Pedidos> pedidosList = pedidosRepository.findAll();

        List<PedidosResponseDom> out = pedidosList.stream()
                .map(PedidosMapper::pedidosToPedidosResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public PedidosResponseDom criarPedidos(PedidosRequestDom pedido) throws SenacException {
        List<String> messages = this.validacaoPedido(pedido);

        if (!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Clientes> cliente = pedidosClientesRepository.findById(pedido.getClienteId());
        if (!cliente.isPresent()){
            throw new SenacException("Cliente não encontrado");
        }

        Pedidos pedidoRetorno = pedidosRepository.save(PedidosMapper.pedidosRequestDomToPedidos(pedido, cliente.get()));

        return PedidosMapper.pedidosToPedidosResponseDom(pedidoRetorno);
    }

    @Override
    public PedidosResponseDom atualizarPedidos(Long id, PedidosRequestDom pedido) throws SenacException {
        List<String> messages = this.validacaoPedido(pedido);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Clientes> cliente = pedidosClientesRepository.findById(pedido.getClienteId());
        if(!cliente.isPresent()){
            throw new SenacException("Cliente não encontrado");
        }

        Optional<Pedidos> pedidoRetorno = pedidosRepository.findById(id).map(record -> {
            record.setDataCriacao(pedido.getDataCriacao());
            record.setDataEntrega(pedido.getDataEntrega());
            record.setValorDesconto(pedido.getValorDesconto());
            record.setCliente(cliente.get());

            return pedidosRepository.save(record);
        });

        if(!pedidoRetorno.isPresent()){
            throw new SenacException("pedido não encontrado!");
        }

        PedidosResponseDom out = PedidosMapper.pedidosToPedidosResponseDom(pedidoRetorno.get());

        return out;
    }

    @Override
    public void deletarPedido(Long id) {
        pedidosRepository.deleteById(id);
    }

    public List<String> validacaoPedido(PedidosRequestDom pedido) {
        List<String> messages = new ArrayList<>();

        if (pedido.getDataEntrega() == null){
            messages.add("Data de entrega não inserido ou invalido!");
        }

        if(pedido.getClienteId() == null || pedido.getClienteId() < 1) {
            messages.add("ClienteId não informado ou valor invalido!");
        }
        if (pedido.getEnderecoId() == null || pedido.getEnderecoId() < 1){
            messages.add("EnderecoId não informado ou valor invalido!");
        }

        return messages;
    }
}
