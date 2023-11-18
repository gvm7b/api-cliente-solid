package br.com.senac.api.useCases.enderecos;

import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.enderecos.domanis.EnderecosRequestDom;
import br.com.senac.api.useCases.enderecos.domanis.EnderecosResponseDom;

import java.util.List;

public interface EnderecosService {
    List<EnderecosResponseDom> carregarEnderecos();
    EnderecosResponseDom criarEndereco(EnderecosRequestDom endereco) throws SenacException;
    EnderecosResponseDom atualizarEndereco(Long id, EnderecosRequestDom endereco) throws SenacException;
    void deletarEndereco(Long id);
}
