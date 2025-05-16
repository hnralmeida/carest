package com.les.carest.controller;

import com.les.carest.pdfGenerator.GenericPDF;
import com.les.carest.relatoriosDTO.AniversarianteDTO;
import com.les.carest.relatoriosDTO.ClienteDiarioDTO;
import com.les.carest.relatoriosDTO.TicketMedioDTO;
import com.les.carest.relatoriosDTO.UltimaVendaDTO;
import com.les.carest.service.RelatorioService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/relatorios")  // Prefixo para todos os endpoints
@Validated
public class RelatorioController {//modificar os Resquest params para igual do aniversariamente

    private final RelatorioService relatorioService;

    // Injeção de dependência via construtor
    @Autowired
    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

//    // Endpoint 1: Ticket médio de um cliente específico
//    @GetMapping("/ticket-medio/{clienteId}")
//    public ResponseEntity<Double> getTicketMedio(@PathVariable UUID clienteId) {
//        Double ticketMedio = relatorioService.getTicketMedio(clienteId);
//        return ResponseEntity.ok(ticketMedio);
//    }

    // Endpoint 2 (GET): Ticket médio para múltiplos clientes
    @GetMapping("/ticketMedio")
    public ResponseEntity<byte[]> getTicketMedioMultiplosClientes(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataFim
    ) {  // Requisição: ?clientesIds=id1,id2,id3
        List<TicketMedioDTO> resultados = relatorioService.getTicketMedioMultiplosClientes(dataInicio, dataFim);
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados,"Ticket Medio"));
    }

//    // Endpoint 3: Última venda de um cliente específico
//    @GetMapping("/ultima-vendas/{clienteId}")
//    public ResponseEntity<UltimaVendaDTO> getUltimaVenda(@PathVariable UUID clienteId) {
//        UltimaVendaDTO ultimaVenda = relatorioService.getUltimaVenda(clienteId);
//        return ResponseEntity.ok(ultimaVenda);
//    }

    @GetMapping("/ultimasVendas")
    public ResponseEntity<byte[]> getUltimasVendas() {
        List<UltimaVendaDTO> resultados = relatorioService.getUltimasVendasComClientesNativo();
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Ultimas Vendas"));
    }

    @GetMapping("/ultimas-vendas")
    public ResponseEntity<List<UltimaVendaDTO>> getUltimasVendasTable() {
        return ResponseEntity.ok(
                relatorioService.getUltimasVendasComClientesNativo()
        );
    }

    @GetMapping("/aniversariantes")
    public ResponseEntity<List<AniversarianteDTO>> listarAniversariantesPorData(
            @RequestParam @Min(1) @Max(12) int mes) {
        return ResponseEntity.ok(relatorioService.listarAniversariantesPorMes(mes));
    }

    @GetMapping("/diario/{data}")
    public ResponseEntity<byte[]> getDiario(@PathVariable Date data) {
        List<ClienteDiarioDTO> resultados = relatorioService.findClientesDiariosData(data);

        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Ultimas Vendas"));
    }

    @GetMapping("/diario")
    public ResponseEntity<byte[]> getClientesDiariosComGasto() {//revisar
        List<ClienteDiarioDTO> resultados = relatorioService.findClientesDiariosComGasto();
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Ultimas Vendas"));
    }

}