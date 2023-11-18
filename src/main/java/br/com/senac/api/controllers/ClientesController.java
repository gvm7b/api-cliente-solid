package br.com.senac.api.controllers;

import br.com.senac.api.frameWork.annotions.LogRest;
import br.com.senac.api.frameWork.utils.ResponseUtil;
import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.clientes.domanis.ClientesRequestDom;
import br.com.senac.api.useCases.clientes.impl.repositorys.ClientesRespository;
import br.com.senac.api.useCases.clientes.domanis.ClientesResponseDom;
import br.com.senac.api.useCases.clientes.impl.ClientesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesServiceImpl clientesService;

    @Autowired
    ClientesRespository clientesRespository;

    @GetMapping(path = "/carregar")
    @LogRest
    public ResponseEntity<List<ClientesResponseDom>> carregarClientes(){
        return ResponseEntity.ok(clientesService.carregarClientes());
    }

    @GetMapping("/carregar/{id}")
    @LogRest
    public ResponseEntity<ClientesResponseDom> carregarClienteById(@PathVariable Long id){
        return ResponseEntity.ok(clientesService.carregarClienteById(id));
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarCliente(@RequestBody ClientesRequestDom clientesRequestDom){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.criarCliente(clientesRequestDom));
        } catch (SenacException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        }
    }

    @PutMapping("/atualizar/{id}")
    @LogRest
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody ClientesRequestDom clientesRequestDom) throws SenacException {

        try {
            return ResponseEntity.ok(clientesService.atualizarCliente(id, clientesRequestDom));
        } catch (SenacException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMapper("Erro não mapeado:" + e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    @LogRest
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clientesService.deletarCliente(id);

        return ResponseEntity.ok(null);
    }
}
