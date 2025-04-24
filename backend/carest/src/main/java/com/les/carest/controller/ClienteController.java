package com.les.carest.controller;

import com.les.carest.DTO.ClienteDTO;
import com.les.carest.DTO.RecargaDTO;
import com.les.carest.model.Cliente;
import com.les.carest.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "Gerencia de Clientes")
public class ClienteController extends _GenericController<Cliente> {
    public ClienteController(ClienteService clienteService) {
        super(clienteService);
    }

    @Autowired
    private ClienteService clienteService;

    // Buscar
    @GetMapping("/{id}")//devia usar os DTO provavelmente
    @Operation(summary = "Busca um Cliente pelo ID")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable UUID id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }


    // Buscar
    @GetMapping("/codigo/{codigo}")//devia usar os DTO provavelmente
    @Operation(summary = "Busca um Cliente pelo ID")
    public ResponseEntity<ClienteDTO> buscarPorCodigo(@PathVariable String codigo) {
        ClienteDTO cliente = clienteService.acharCliente(codigo);
        return ResponseEntity.ok(cliente);
    }

    // Listar
    @GetMapping("/divida")
    @Operation(summary = "Lista todas os endividados")
    public ResponseEntity<List<ClienteDTO>> listarTodas() {
        List<ClienteDTO> clientes = clienteService.listarEndividados();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/recarga")
    @Operation(summary = "Adicionar Crédito")
    public ResponseEntity<ClienteDTO> adicionarCredito(@RequestBody @Valid RecargaDTO recargaDTO) {
        try {
            ClienteDTO clienteAtualizado = clienteService.adicionarCredito(
                    recargaDTO.getCodigoCliente(),
                    recargaDTO.getValorRecarga()
            );
            return ResponseEntity.ok(clienteAtualizado);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/diario")
    public ResponseEntity<List<ClienteDTO>> listarDiario(Date data) {//incluir limite
        List<ClienteDTO> clientes = clienteService.listarEndividados();
        return ResponseEntity.ok(clientes);
    }

}