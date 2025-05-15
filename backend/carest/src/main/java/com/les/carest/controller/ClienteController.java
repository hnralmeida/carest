package com.les.carest.controller;

import com.les.carest.DTO.ClienteDTO;
import com.les.carest.model.Cliente;
import com.les.carest.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Gestão de clientes")
public class ClienteController extends _GenericController<Cliente>{

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService)  {
        super(clienteService);
        this.clienteService = clienteService;
    }

//    // Busca cliente por ID (usando DTO)
//    @GetMapping("/{id}")
//    @Operation(summary = "Busca cliente por ID")
//    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable UUID id) {
//        ClienteDTO cliente = clienteService.toDTO(clienteService.buscarPorId(id));
//        return ResponseEntity.ok(cliente);
//    }

    // Buscar
    @GetMapping("/codigo/{codigo}")//devia usar os DTO provavelmente
    @Operation(summary = "Busca um Cliente pelo ID")
    public ResponseEntity<ClienteDTO> buscarPorCodigo(@PathVariable String codigo) {
        ClienteDTO cliente = clienteService.acharCliente(codigo);
        return ResponseEntity.ok(cliente);
    }

}