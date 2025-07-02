package com.les.carest.controller;

import com.les.carest.model.Acesso;
import com.les.carest.model.Cliente;
import com.les.carest.service.AcessoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/acesso")
public class AcessoController extends _GenericController<Acesso> {

    private final AcessoService acessoService;

    public AcessoController(AcessoService acessoService) {
        super(acessoService);
        this.acessoService = acessoService;
    }

    @PostMapping("/entrada")
    public ResponseEntity<?> registrarEntrada(@RequestParam String codigo) {
        try {
            Cliente cliente = acessoService.findClienteByCodigo(codigo);

            if(cliente==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
            }
            if (cliente.isEm_uso()) {
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(cliente);
            }
            if (cliente.getBloqueado()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(cliente);
            }

            double valorTotal = cliente.getSaldo() + cliente.getLimite();

            if (valorTotal <= 0) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(cliente);
            }

            // Busca o cliente e verifica condições
            Acesso acesso = acessoService.registrarEntrada(codigo);
            return ResponseEntity.ok(acesso);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
        }
    }

    @PostMapping("/saida")
    public ResponseEntity<?> registrarSaida(@RequestParam String codigo) {
        try {
            Acesso acesso = acessoService.registrarSaidaPorCodigoCliente(codigo);
            return ResponseEntity.ok(acesso);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}