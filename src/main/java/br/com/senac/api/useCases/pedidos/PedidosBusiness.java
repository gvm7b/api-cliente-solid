package br.com.senac.api.useCases.pedidos;

import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.pedidos.domanis.PedidosRequestDom;
import br.com.senac.api.useCases.pedidos.domanis.PedidosResponseDom;

import java.util.List;

public interface PedidosBusiness {
    List<PedidosResponseDom> carregarPedidos();
    PedidosResponseDom criarPedidos(PedidosRequestDom pedido) throws SenacException;
    PedidosResponseDom atualizarPedidos(Long id, PedidosRequestDom pedido) throws SenacException;
    void deletarPedido (Long id);

}
