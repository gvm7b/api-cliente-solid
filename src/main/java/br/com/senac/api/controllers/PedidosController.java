package br.com.senac.api.controllers;

import br.com.senac.api.frameWork.annotions.LogRest;
import br.com.senac.api.frameWork.utils.ResponseUtil;
import br.com.senac.api.frameWork.utils.SenacException;
import br.com.senac.api.useCases.pedidos.domanis.PedidosRequestDom;
import br.com.senac.api.useCases.pedidos.domanis.PedidosResponseDom;
import br.com.senac.api.useCases.pedidos.impl.PedidosServiceImpl;
import br.com.senac.api.useCases.pedidos.impl.repositorys.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    PedidosRepository pedidosRepository;

    @Autowired
    private PedidosServiceImpl pedidosService;

    @GetMapping("/carregar")
    @LogRest
    public ResponseEntity<List<PedidosResponseDom>> carregarPedidos(){
        List<PedidosResponseDom> out = pedidosService.carregarPedidos();

        return ResponseEntity.ok(out);
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarPedidos(@RequestBody PedidosRequestDom pedido){

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.criarPedidos(pedido));
        } catch (SenacException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMapper("Erro não mapeado:" + e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    @LogRest
    public ResponseEntity<?> atualizarEndereco (@PathVariable Long id, @RequestBody PedidosRequestDom pedido){
        try {
            return ResponseEntity.ok(pedidosService.atualizarPedidos(id, pedido));
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
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id){
        pedidosService.deletarPedido(id);

        return ResponseEntity.ok(null);
    }




}
