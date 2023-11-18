package br.com.senac.api.useCases.clientes.impl;

import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.clientes.ClientesBusiness;
import br.com.senac.api.useCases.clientes.ClientesService;
import br.com.senac.api.useCases.clientes.domanis.ClientesRequestDom;
import br.com.senac.api.useCases.clientes.domanis.ClientesResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServiceImpl implements ClientesService {

    @Autowired
    private ClientesBusinessImpl clientesBusiness;

    @Override
    public List<ClientesResponseDom> carregarClientes() {
        return clientesBusiness.carregarClientes();
    }

    @Override
    public ClientesResponseDom criarCliente(ClientesRequestDom clientesRequestDom) throws SenacException {
        return clientesBusiness.criarCliente(clientesRequestDom);
    }

    @Override
    public ClientesResponseDom atualizarCliente(Long id, ClientesRequestDom clientesRequestDom) throws SenacException {
        return clientesBusiness.atualizarCliente(id, clientesRequestDom);
    }

    @Override
    public void deletarCliente(Long id) {
        clientesBusiness.deletarCliente(id);
    }

    @Override
    public ClientesResponseDom carregarClienteById(Long id) {
        return clientesBusiness.carregarClienteById(id);
    }
}
