package br.com.senac.api.useCases.pedidosItens.impl.mappers;

import br.com.senac.api.entitys.Pedidos;
import br.com.senac.api.entitys.PedidosItens;
import br.com.senac.api.entitys.Produtos;
import br.com.senac.api.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import br.com.senac.api.useCases.pedidosItens.domanis.PedidosItensResponseDom;

public class PedidosItensMapper {

    public static PedidosItensResponseDom pedidosItensToPedidosItensResponseDom(PedidosItens pedidosItens) {
        PedidosItensResponseDom out = new PedidosItensResponseDom();
        out.setId(pedidosItens.getId());
        out.setQuantidade(pedidosItens.getQuantidade());
        out.setValorUnitario(pedidosItens.getValorUnitario());
        out.setPedidoId(pedidosItens.getPedidosId().getId());

        return out;
    }

    public static PedidosItens pedidosItensRequestDomToPedidosItens(PedidosItensRequestDom pedidosItens, Pedidos pedido, Produtos produto){
        PedidosItens out = new PedidosItens();
        out.setQuantidade(pedidosItens.getQuantidade());
        out.setValorUnitario(pedidosItens.getValorUnitario());
        out.setPedidosId(pedido);
        out.setProdutoId(produto);

        return out;
    }
}
