package br.com.senac.api.useCases.clientes.impl;

import br.com.senac.api.entitys.Clientes;
import br.com.senac.api.entitys.Enderecos;
import br.com.senac.api.frameWork.annotions.Business;
import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.frameWork.utils.StringUtil;
import br.com.senac.api.useCases.clientes.ClientesBusiness;
import br.com.senac.api.useCases.clientes.domanis.ClientesRequestDom;
import br.com.senac.api.useCases.clientes.domanis.ClientesResponseDom;
import br.com.senac.api.useCases.clientes.impl.mappers.ClientesMapper;
import br.com.senac.api.useCases.clientes.impl.repositorys.ClientesEnderecosRespository;
import br.com.senac.api.useCases.clientes.impl.repositorys.ClientesRespository;
import br.com.senac.api.useCases.enderecos.impl.EnderecosBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class ClientesBusinessImpl implements ClientesBusiness {

    @Autowired
    private ClientesRespository clientesRepository;

    @Autowired
    private ClientesEnderecosRespository clientesEnderecosRespository;


    @Override
    public List<ClientesResponseDom> carregarClientes() {
        List<Clientes> clientesList = clientesRepository.findAll();

        List<ClientesResponseDom> out = clientesList
                .stream()
                .map(ClientesMapper :: clientesToClientesResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public ClientesResponseDom criarCliente(ClientesRequestDom clientesRequestDom) throws SenacException {
        List<String> messages =  this.validacaoManutencaoCliente(clientesRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Clientes clientes = ClientesMapper.clientesRequestDomToClientes(clientesRequestDom);

        Clientes resultClientes = clientesRepository.save(clientes);

        ClientesResponseDom out = ClientesMapper.clientesToClientesResponseDom(resultClientes);

        return out;
    }

    @Override
    public ClientesResponseDom atualizarCliente(Long id, ClientesRequestDom clientesRequestDom) throws SenacException {
        List<String> messages =  this.validacaoManutencaoCliente(clientesRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Clientes> clientes = clientesRepository.findById(id).map(record -> {
           record.setSobreNome(clientesRequestDom.getSobrenome());
           record.setNome(clientesRequestDom.getNome());
           record.setEmail(clientesRequestDom.getEmail());
           record.setTelefone(clientesRequestDom.getTelefone());
           record.setDataNascimento(clientesRequestDom.getDataNascimento());

           return clientesRepository.save(record);
        });

        if(!clientes.isPresent()){
            throw new SenacException("Cliente informado não existe!");
        }

        ClientesResponseDom out = ClientesMapper.clientesToClientesResponseDom(clientes.get());

        return out;
    }

    @Override
    public void deletarCliente(Long id) {
        clientesRepository.deleteById(id);
    }

    @Override
    public Clientes carregarClienteEntidade(Long id) {
        Clientes cliente = clientesRepository.findById(id).get();

        return cliente;
    }

    @Override
    public ClientesResponseDom carregarClienteById(Long id) {
        Clientes cliente = clientesRepository.findById(id).get();

        List<Enderecos> enderecos = clientesEnderecosRespository.carregarEnderecosByClienteId(id);

        ClientesResponseDom out = ClientesMapper.clientesToClientesResponseDom(cliente, enderecos);

        return out;
    }

    private List<String> validacaoManutencaoCliente(ClientesRequestDom cliente) {
        List<String> messages = new ArrayList<>();

        if(StringUtil.validarString(cliente.getNome())){
            messages.add("Cliente informado não possui nome!");
        }

        if(StringUtil.validarString(cliente.getSobrenome())){
            messages.add("Cliente informado não possui sobrenome!");
        }

        if(StringUtil.validarString(cliente.getEmail())){
            messages.add("Cliente informado não possui email");
        }

        return messages;
    }
}
