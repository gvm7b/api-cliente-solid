package br.com.senac.api.controllers;

import br.com.senac.api.frameWork.annotions.LogRest;
import br.com.senac.api.frameWork.utils.ResponseUtil;
import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import br.com.senac.api.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import br.com.senac.api.useCases.pedidosItens.impl.PedidosItensServiceImpl;
import br.com.senac.api.useCases.pedidosItens.impl.repositorys.PedidosItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidos_itens")
public class PedidosItensController {

    @Autowired
    PedidosItensRepository pedidosItensRepository;

    @Autowired
    private PedidosItensServiceImpl pedidosItensService;

    @GetMapping("/carregar")
    @LogRest
    public ResponseEntity<List<PedidosItensResponseDom>> carregarPedidosItens() {
        List<PedidosItensResponseDom> out = pedidosItensService.carregarPedidosItens();

        return ResponseEntity.ok(out);
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarPedidosItens(@RequestBody PedidosItensRequestDom pedidosItens) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidosItensService.criarPedidosItens(pedidosItens));
        } catch (SenacException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMapper("Erro não mapeado:" + e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    @LogRest
    public ResponseEntity<?> atualizarPedidosItens (@PathVariable Long id, @RequestBody PedidosItensRequestDom pedidosItens){

        try {
            return ResponseEntity.ok(pedidosItensService.atualizarPedidosItens(id, pedidosItens));
        } catch (SenacException senac) {
            senac.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(senac.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMapper("Erro não mapeado:" +e.getMessage()));
        }
    }
    @DeleteMapping("/deletar/{id}")
    @LogRest
    public ResponseEntity<?> deletarPedidoItens(@PathVariable Long id){
        pedidosItensService.deletarPedidoItens(id);

        return ResponseEntity.ok(null);
    }


}
