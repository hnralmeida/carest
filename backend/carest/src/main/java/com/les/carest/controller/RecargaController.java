package com.les.carest.controller;

import com.les.carest.DTO.ClienteDTO;
import com.les.carest.service.RecargaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/recargas")
@Tag(name = "Recargas", description = "Operações de recarga")
public class RecargaController {

    private final RecargaService recargaService;

    @Autowired
    public RecargaController(RecargaService recargaService) {
        this.recargaService = recargaService;
    }

    // Registra uma nova recarga
    @PostMapping("/{clienteId}")
    @Operation(summary = "Registra uma recarga")
    public ResponseEntity<ClienteDTO> registrarRecarga(@PathVariable UUID clienteId, @RequestBody double valorRecarga) {
        ClienteDTO recarga = recargaService.adicionarCredito(clienteId,valorRecarga);
        return ResponseEntity.status(HttpStatus.CREATED).body(recarga);
    }

    // Ajusta limite do cliente
    @PutMapping("/limite")
    @Operation(summary = "Ajusta o limite de crédito")
    public ResponseEntity<ClienteDTO> ajustarLimite(@RequestBody UUID clienteId, @RequestBody double valorRecarga) {
        ClienteDTO cliente = recargaService.adicionarLimite(clienteId,valorRecarga);
        return ResponseEntity.ok(cliente);
    }

    // Altera estado de bloqueio
    @PutMapping("/bloqueio/{id}")
    @Operation(summary = "Altera o estado de bloqueio")
    public ResponseEntity<String> alterarBloqueio(@PathVariable UUID id) {
        recargaService.mudarEstadoDeAcesso(id);
        return ResponseEntity.ok("Estado de bloqueio alterado");
    }

    @PostMapping("/quitarDivida")
    @Operation(summary = "Registra uma recarga")
    public ResponseEntity<ClienteDTO> pagamentoDivida(@RequestBody UUID clienteId, @RequestBody double divida) {//modificar
        ClienteDTO recarga = recargaService.adicionarCredito(clienteId,divida);


        return ResponseEntity.status(HttpStatus.CREATED).body(recarga);
    }



    @PutMapping("/estadoUso")
    @Operation(summary = "Altera o estado de uso")
    public ResponseEntity<String> estaEmUso(UUID idCLiente) {
        recargaService.mudarEstadoDeUso(idCLiente);
        return ResponseEntity.ok("Estado de uso alterado");
    }
}