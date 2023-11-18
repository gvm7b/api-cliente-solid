package br.com.senac.api.useCases.pedidos.impl;

import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.pedidos.PedidosService;
import br.com.senac.api.useCases.pedidos.domanis.PedidosRequestDom;
import br.com.senac.api.useCases.pedidos.domanis.PedidosResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosServiceImpl implements PedidosService {

    @Autowired
    private PedidosBusinessImpl pedidosBusiness;

    @Override
    public List<PedidosResponseDom> carregarPedidos() {
        return pedidosBusiness.carregarPedidos();
    }

    @Override
    public PedidosResponseDom criarPedidos(PedidosRequestDom pedido) throws SenacException {
        return pedidosBusiness.criarPedidos(pedido);
    }

    @Override
    public PedidosResponseDom atualizarPedidos(Long id, PedidosRequestDom pedido) throws SenacException {
        return pedidosBusiness.atualizarPedidos(id, pedido);
    }

    @Override
    public void deletarPedido(Long id) {
        pedidosBusiness.deletarPedido(id);
    }
}
