package com.les.carest.controller;


import com.les.carest.DTO.ClienteDTO;
import com.les.carest.model.Cliente;
import com.les.carest.service.ClienteService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/aniversariantes")
public class AniversariantesController extends _GenericController<Cliente> {

    private final ClienteService clienteService;

    @Autowired
    public AniversariantesController(ClienteService clienteService) {
        super(clienteService); // Passa o ClienteService para a classe pai
        this.clienteService = clienteService;
    }

    @GetMapping("/hoje")
    public ResponseEntity<List<ClienteDTO>> listarAniversariantesDoDia() {
        return ResponseEntity.ok(clienteService.listarAniversariantesDoDia());
    }

    @GetMapping("/por-data")
    public ResponseEntity<List<ClienteDTO>> listarAniversariantesPorData(
            @RequestParam @Min(1) @Max(12) int mes,
            @RequestParam @Min(1) @Max(31) int dia) {
        return ResponseEntity.ok(clienteService.listarAniversariantesPorData(mes, dia));
    }
}
