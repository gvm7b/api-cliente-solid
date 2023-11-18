package br.com.senac.api.useCases.enderecos.impl;

import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.enderecos.EnderecosService;
import br.com.senac.api.useCases.enderecos.domanis.EnderecosRequestDom;
import br.com.senac.api.useCases.enderecos.domanis.EnderecosResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecosServiceImpl implements EnderecosService {

    @Autowired
    private EnderecosBusinessImpl enderecosBusiness;

    @Override
    public List<EnderecosResponseDom> carregarEnderecos() {
        return enderecosBusiness.carregarEnderecos();
    }

    @Override
    public EnderecosResponseDom criarEndereco(EnderecosRequestDom endereco) throws SenacException {
        return enderecosBusiness.criarEndereco(endereco);
    }

    @Override
    public EnderecosResponseDom atualizarEndereco(Long id, EnderecosRequestDom endereco) throws SenacException {
        return enderecosBusiness.atualizarEndereco(id, endereco);
    }

    @Override
    public void deletarEndereco(Long id) {
        enderecosBusiness.deletarEndereco(id);
    }
}
