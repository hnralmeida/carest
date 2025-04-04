package com.les.carest.controller;


import com.les.carest.DTO.AniversarianteDTO;
import com.les.carest.model.Cliente;
import com.les.carest.service.ClienteService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/mes")
    public ResponseEntity<List<Cliente>> listarAniversariantesPorData(
            @RequestParam @Min(1) @Max(12) int mes) {
        return ResponseEntity.ok(clienteService.listarAniversariantesPorMes(mes));
    }
}
