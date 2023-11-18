package br.com.senac.api.useCases.pedidosItens.impl;


import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.pedidosItens.PedidosItensService;
import br.com.senac.api.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import br.com.senac.api.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosItensServiceImpl implements PedidosItensService {

    @Autowired
    private PedidosItensBusinessImpl pedidosItensBusiness;

    @Override
    public List<PedidosItensResponseDom> carregarPedidosItens() {
        return pedidosItensBusiness.carregarPedidosItens();
    }

    @Override
    public PedidosItensResponseDom criarPedidosItens(PedidosItensRequestDom pedidoItens) throws SenacException {
        return pedidosItensBusiness.criarPedidosItens(pedidoItens);
    }

    @Override
    public PedidosItensResponseDom atualizarPedidosItens(Long id, PedidosItensRequestDom pedidosItens) throws SenacException {
        return pedidosItensBusiness.atualizarPedidosItens(id, pedidosItens);
    }

    @Override
    public void deletarPedidoItens(Long id) {
        pedidosItensBusiness.deletarPedidoItens(id);
    }
}
