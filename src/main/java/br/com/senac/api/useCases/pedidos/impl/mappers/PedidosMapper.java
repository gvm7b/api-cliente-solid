package br.com.senac.api.useCases.pedidos.impl.mappers;

import br.com.senac.api.entitys.Clientes;
import br.com.senac.api.entitys.Pedidos;
import br.com.senac.api.entitys.PedidosItens;
import br.com.senac.api.useCases.pedidos.domanis.PedidosResponseDom;

import java.time.LocalDateTime;


public class PedidosMapper {

    public static PedidosResponseDom pedidosToPedidosResponseDom(Pedidos pedidos){
        PedidosResponseDom out = new PedidosResponseDom();
        out.setId(pedidos.getId());
        out.setDataCriacao(pedidos.getDataCriacao());
        out.setDataEntrega(pedidos.getDataEntrega());
        out.setValorDesconto(pedidos.getValorDesconto());
        out.setClienteId(pedidos.getCliente().getId());

        return out;
    }

    public static Pedidos pedidosRequestDomToPedidos(PedidosResponseDom pedidos, Clientes cliente) {
        Pedidos out = new Pedidos();
        out.setDataCriacao(LocalDateTime.now());
        out.setDataEntrega(pedidos.getDataEntrega());
        out.setValorDesconto(pedidos.getValorDesconto());
        out.setCliente(cliente);

        return out;
    }


}
