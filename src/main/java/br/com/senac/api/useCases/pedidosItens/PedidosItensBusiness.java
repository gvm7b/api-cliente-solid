package br.com.senac.api.useCases.pedidosItens;

import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import br.com.senac.api.useCases.pedidosItens.domanis.PedidosItensResponseDom;

import java.util.List;

public interface PedidosItensBusiness {
    List<PedidosItensResponseDom> carregarPedidosItens();
    PedidosItensResponseDom criarPedidosItens(PedidosItensRequestDom pedidoItens) throws SenacException;
    PedidosItensResponseDom atualizarPedidosItens(Long id, PedidosItensRequestDom pedidosItens) throws SenacException;
    void deletarPedidoItens(Long id);
}
