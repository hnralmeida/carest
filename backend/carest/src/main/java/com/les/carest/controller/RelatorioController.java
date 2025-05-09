package com.les.carest.controller;

import com.les.carest.DTO.AniversarianteDTO;
import com.les.carest.relatoriosDTO.TicketMedioDTO;
import com.les.carest.relatoriosDTO.UltimaVendaDTO;
import com.les.carest.service.RelatorioService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorios")  // Prefixo para todos os endpoints
public class RelatorioController {//modificar os Resquest params para igual do aniversariamente

    private final RelatorioService relatorioService;

    // Injeção de dependência via construtor
    @Autowired
    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    // Endpoint 1: Ticket médio de um cliente específico
    @GetMapping("/ticket-medio/{clienteId}")
    public ResponseEntity<Double> getTicketMedio(@PathVariable UUID clienteId) {
        Double ticketMedio = relatorioService.getTicketMedio(clienteId);
        return ResponseEntity.ok(ticketMedio);
    }

    // Endpoint 2 (GET): Ticket médio para múltiplos clientes
    @GetMapping("/ticket-medio/multiplos-clientes")
    public ResponseEntity<List<TicketMedioDTO>> getTicketMedioMultiplosClientes(
            @RequestParam List<UUID> clientesIds) {  // Requisição: ?clientesIds=id1,id2,id3
        List<TicketMedioDTO> resultados = relatorioService.getTicketMedioMultiplosClientes(clientesIds);
        return ResponseEntity.ok(resultados);
    }

    // Endpoint 3: Última venda de um cliente específico
    @GetMapping("/ultima-venda/{clienteId}")
    public ResponseEntity<UltimaVendaDTO> getUltimaVenda(@PathVariable UUID clienteId) {
        UltimaVendaDTO ultimaVenda = relatorioService.getUltimaVenda(clienteId);
        return ResponseEntity.ok(ultimaVenda);
    }

    // Endpoint 4: Últimas vendas de múltiplos clientes (via lista de IDs no corpo da requisição)
    @PostMapping("/ultimas-vendas/multiplos-clientes")
    public ResponseEntity<List<UltimaVendaDTO>> getUltimasVendasMultiplosClientes(
            @RequestBody List<UUID> clientesIds) {
        List<UltimaVendaDTO> resultados = relatorioService.getUltimasVendasMultiplosClientes(clientesIds);
        return ResponseEntity.ok(resultados);
    }

    // Endpoint 5: Aniversariantes do dia
    @GetMapping("/aniversariantes/hoje")
    public ResponseEntity<List<AniversarianteDTO>> getAniversariantesDoDia() {
        List<AniversarianteDTO> aniversariantes = relatorioService.getAniversariantesDoDia();
        return ResponseEntity.ok(aniversariantes);
    }

    // Endpoint 6: Aniversariantes por data
    @GetMapping("/aniversariantes/por-data")
    public ResponseEntity<List<AniversarianteDTO>> getAniversariantesPorData(
            @RequestParam @Min(1) @Max(12) int mes,
            @RequestParam @Min(1) @Max(31) int dia) {
        List<AniversarianteDTO> aniversariantes = relatorioService.getAniversariantesPorData(mes, dia);
        return ResponseEntity.ok(aniversariantes);
    }


//    // Lista clientes endividados
//    @GetMapping("/endividados")
//    @Operation(summary = "Lista clientes com saldo negativo")
//    public ResponseEntity<List<ClienteDTO>> listarEndividados() {
//        List<ClienteDTO> clientes = relatorioService.clientesEndividados();
//        return ResponseEntity.ok(clientes);
//    }Precisa do DTO


}