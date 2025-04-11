package com.les.carest.controller;

import com.les.carest.DTO.ProdutoDTO_Serial;
import com.les.carest.model.Cliente;
import com.les.carest.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/cliente")
public class ClienteController extends _GenericController<Cliente> {
    private final ClienteService clienteService;
    public ClienteController(ClienteService clienteService) {
        super(clienteService);
        this.clienteService = clienteService;
    }

    @GetMapping("/codigo/{codigo}")
    @Operation(summary = "Busca um cliente por Codigo")
    public ResponseEntity<Cliente> buscarPorCodigo(@PathVariable String codigo) {
        Cliente cliente = clienteService.buscarClientePorCodigo(codigo);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }
}