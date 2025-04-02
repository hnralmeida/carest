package com.les.carest.controller;


import com.les.carest.DTO.AniversarianteDTO;
import com.les.carest.DTO.MesDTO;
import com.les.carest.model.Cliente;
import com.les.carest.service.ClienteService;
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

    @GetMapping("/hoje")
    public ResponseEntity<List<AniversarianteDTO>> listarAniversariantesDoDia() {
        return ResponseEntity.ok(clienteService.listarAniversariantesDoDia());
    }

    @PostMapping("/mes")
    public ResponseEntity<List<AniversarianteDTO>> listarPorMes(@RequestBody MesDTO request) {
        return ResponseEntity.ok(clienteService.listarAniversariantesPorMes(request.getMes()));
    }

    // Endpoint alternativo para listar por mês VIA GET (com path variable)
    @GetMapping("/por-mes/{mes}")
    public ResponseEntity<List<AniversarianteDTO>> listarPorMesGet(@PathVariable int mes) {
        return ResponseEntity.ok(clienteService.listarAniversariantesPorMes(mes));
    }

}
