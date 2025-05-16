package com.les.carest.controller;

import com.les.carest.model.Acesso;
import com.les.carest.service.AcessoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acesso")
public class AcessoController extends GenericController<Acesso> {

    private final AcessoService acessoService;

    public AcessoController(AcessoService acessoService) {
        super(acessoService);
        this.acessoService = acessoService;
    }

    @PostMapping("/entrada")
    public ResponseEntity<?> registrarEntrada(@RequestParam String codigo) {
        try {
            // Busca o cliente e verifica condições
            Acesso acesso = acessoService.registrarEntrada(codigo);
            return ResponseEntity.ok(acesso);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
        }
    }

    @PutMapping("/saida")
    public ResponseEntity<?> registrarSaida(@RequestParam String codigoCliente) {
        try {
            Acesso acesso = acessoService.registrarSaidaPorCodigoCliente(codigoCliente);
            return ResponseEntity.ok(acesso);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}