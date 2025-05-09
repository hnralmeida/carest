package com.les.carest.controller;

import com.les.carest.DTO.ClienteDTO;
import com.les.carest.DTO.RecargaDTO;
import com.les.carest.service.RecargaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping
    @Operation(summary = "Registra uma recarga")
    public ResponseEntity<RecargaDTO> registrarRecarga(@RequestBody @Valid RecargaDTO recargaDTO) {
        RecargaDTO recarga = recargaService.registrarRecarga(recargaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(recarga);
    }

    // Ajusta limite do cliente
    @PutMapping("/limite")
    @Operation(summary = "Ajusta o limite de crédito")
    public ResponseEntity<ClienteDTO> ajustarLimite(@RequestBody @Valid RecargaDTO recargaDTO) {
        ClienteDTO cliente = recargaService.ajustarLimite(
                recargaDTO.getIdCliente(),
                recargaDTO.getValorRecarga()
        );
        return ResponseEntity.ok(cliente);
    }

    // Altera estado de bloqueio
    @PutMapping("/bloqueio")
    @Operation(summary = "Altera o estado de bloqueio")
    public ResponseEntity<String> alterarBloqueio(@RequestBody @Valid RecargaDTO recargaDTO) {
        recargaService.alternarBloqueio(recargaDTO.getIdCliente());
        return ResponseEntity.ok("Estado de bloqueio alterado");
    }
}