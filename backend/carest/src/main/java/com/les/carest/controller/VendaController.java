package com.les.carest.controller;

import com.les.carest.DTO.VendaDTO;
import com.les.carest.model.Cliente;
import com.les.carest.model.Venda;
import com.les.carest.service.ClienteService;
import com.les.carest.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vendas")
@Tag(name = "Vendas", description = "Gerenciamento de vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Cria uma nova venda")  // Swagger
    public ResponseEntity<Object> criarVenda(@RequestBody VendaDTO vendaDTO) {
        Cliente cliente = clienteService.buscarPorId(vendaDTO.getClienteId());
        double valorDisponivel = cliente.getSaldo() + cliente.getLimite();
        double valorTotal = vendaService.valorVenda(vendaDTO);

        if(valorTotal > valorDisponivel){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(vendaDTO);
        }else{
            Venda venda = vendaService.criarVenda(vendaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(venda);
        }

    }
//
// Buscar venda por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca uma venda pelo ID")
    public ResponseEntity<Venda> buscarPorId(@PathVariable UUID id) {
        Venda venda = vendaService.buscarPorId(id);
        return ResponseEntity.ok(venda);
    }

    // Listar todas as vendas
    @GetMapping
    @Operation(summary = "Lista todas as vendas")
    public ResponseEntity<List<Venda>> listarTodas() {
        List<Venda> vendas = vendaService.listarTodas();
        return ResponseEntity.ok(vendas);
    }

    // Deletar venda
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma venda")
    public ResponseEntity<Void> deletarVenda(@PathVariable UUID id) {
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }
}