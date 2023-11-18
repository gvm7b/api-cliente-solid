package br.com.senac.api.useCases.enderecos.impl;

import br.com.senac.api.entitys.Clientes;
import br.com.senac.api.entitys.Enderecos;
import br.com.senac.api.frameWork.annotions.Business;
import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.enderecos.EnderecosBusiness;
import br.com.senac.api.useCases.enderecos.domanis.EnderecosRequestDom;
import br.com.senac.api.useCases.enderecos.domanis.EnderecosResponseDom;
import br.com.senac.api.useCases.enderecos.impl.mappers.EnderecosMapper;
import br.com.senac.api.useCases.enderecos.impl.repositorys.EnderecosClientesRepository;
import br.com.senac.api.useCases.enderecos.impl.repositorys.EnderecosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class EnderecosBusinessImpl implements EnderecosBusiness {

    @Autowired
    private EnderecosRepository enderecosRepository;

    @Autowired
    private EnderecosClientesRepository enderecosClientesRepository;

    @Override
    public List<EnderecosResponseDom> carregarEnderecos() {
        List<Enderecos> enderecosList = enderecosRepository.findAll();

        List<EnderecosResponseDom> out = enderecosList.stream()
                .map(EnderecosMapper::enderecosToEnderecosResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public EnderecosResponseDom criarEndereco(EnderecosRequestDom endereco) throws SenacException {
        List<String> messages = this.validacaoManutencaoEndereco(endereco);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Clientes> cliente = enderecosClientesRepository.findById(endereco.getClienteId());
        if(!cliente.isPresent()){
            throw new SenacException("Cliente não encontrado!");
        }

        Enderecos enderecoRetorno = enderecosRepository.save(EnderecosMapper.enderecosResquestDomToEnderecos(endereco, cliente.get()));

        return EnderecosMapper.enderecosToEnderecosResponseDom(enderecoRetorno);
    }

    @Override
    public EnderecosResponseDom atualizarEndereco(Long id, EnderecosRequestDom endereco) throws SenacException {
        List<String> messages = this.validacaoManutencaoEndereco(endereco);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Clientes> cliente = enderecosClientesRepository.findById(endereco.getClienteId());
        if(!cliente.isPresent()){
            throw new SenacException("Cliente não encontrado");
        }

        Optional<Enderecos> enderecoRetorno = enderecosRepository.findById(id).map(record -> {
            record.setRua(endereco.getRua());
            record.setBairro(endereco.getBairro());
            record.setCidade(endereco.getCidade());
            record.setEstado(endereco.getEstado());
            record.setCliente(cliente.get());

            return enderecosRepository.save(record);
        });
        if(!enderecoRetorno.isPresent()){
            throw new SenacException("Endereco não encontrado!");
        }

        EnderecosResponseDom out = EnderecosMapper.enderecosToEnderecosResponseDom(enderecoRetorno.get());

        return out;
    }

    @Override
    public void deletarEndereco(Long id) {
        enderecosRepository.deleteById(id);
    }

    private List<String> validacaoManutencaoEndereco(EnderecosRequestDom endereco){
        List<String> messages = new ArrayList<>();

        if(endereco.getBairro() == null || endereco.getBairro() == ""){
            messages.add("Endereco informado não possui bairro!");
        }

        if(endereco.getRua() == null || endereco.getRua() == ""){
            messages.add("Endereco informado não possui rua!");
        }

        if(endereco.getCidade() == null || endereco.getCidade() == ""){
            messages.add("Endereco informado não possui cidade!");
        }

        if(endereco.getEstado() == null || endereco.getEstado() == ""){
            messages.add("Endereco informado não possui estado!");
        }

        if(endereco.getClienteId() == null || endereco.getClienteId() < 1){
            messages.add("ClienteId não informado ou valor invalido!");
        }

        return messages;
    }
}
