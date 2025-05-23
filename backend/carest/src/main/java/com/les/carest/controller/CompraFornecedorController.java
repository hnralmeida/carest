package com.les.carest.controller;

import com.les.carest.model.CompraFornecedor;
import com.les.carest.service.CompraFornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/compra-fornecedor")
public class CompraFornecedorController extends GenericController<CompraFornecedor> {

    private final CompraFornecedorService service;

    public CompraFornecedorController(CompraFornecedorService service) {
        super(service);
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<CompraFornecedor> buscarPorId(@PathVariable UUID id) {
        try {
            CompraFornecedor compra = service.buscarPorId(id);
            if (compra == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(compra);
        } catch (Exception e) {
            // Pode registrar o erro e retornar algo apropriado
            return ResponseEntity.status(500).build();
        }
    }
}
