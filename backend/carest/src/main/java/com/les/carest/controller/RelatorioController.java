package com.les.carest.controller;

import com.les.carest.DTO.AniversarianteDTO;
import com.les.carest.model.Cliente;
import com.les.carest.pdfGenerator.GenericPDF;
import com.les.carest.relatoriosDTO.ClienteDiarioDTO;
import com.les.carest.relatoriosDTO.TicketMedioDTO;
import com.les.carest.relatoriosDTO.UltimaVendaDTO;
import com.les.carest.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorios")
@Tag(name = "Relatórios", description = "Endpoints para geração de relatórios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    @Autowired
    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    // Ticket médio - PDF e JSON
    @Operation(summary = "Ticket médio por período (PDF)")
    @GetMapping(value = "/pdf/ticketMedio", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getTicketMedioMultiplosClientesPDF(
            @Parameter(description = "Data inicial (yyyy-MM-dd)", example = "2023-05-01")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
            @Parameter(description = "Data final (yyyy-MM-dd)", example = "2023-05-31")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim) {

        List<TicketMedioDTO> resultados = relatorioService.getTicketMedioMultiplosClientes(dataInicio, dataFim);
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Ticket Médio"));
    }

    @Operation(summary = "Ticket médio por período (JSON)")
    @GetMapping(value = "/ticketMedio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TicketMedioDTO>> getTicketMedioMultiplosClientesJSON(
            @Parameter(description = "Data inicial (yyyy-MM-dd)", example = "2023-05-01")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
            @Parameter(description = "Data final (yyyy-MM-dd)", example = "2023-05-31")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim) {

        List<TicketMedioDTO> resultados = relatorioService.getTicketMedioMultiplosClientes(dataInicio, dataFim);
        return ResponseEntity.ok(resultados);
    }

    // Última venda por cliente - PDF e JSON
    @Operation(summary = "Última venda por cliente (PDF)")
    @GetMapping(value = "/pdf/ultimasVendas/{clienteId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getUltimaVendaPDF(
            @Parameter(description = "ID do cliente")
            @PathVariable UUID clienteId) {

        UltimaVendaDTO resultado = relatorioService.getUltimaVenda(clienteId);
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(List.of(resultado), "Última Venda"));
    }

    @Operation(summary = "Última venda por cliente (JSON)")
    @GetMapping(value = "/ultimasVendas/{clienteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UltimaVendaDTO> getUltimaVendaJSON(
            @Parameter(description = "ID do cliente")
            @PathVariable UUID clienteId) {

        UltimaVendaDTO resultado = relatorioService.getUltimaVenda(clienteId);
        return ResponseEntity.ok(resultado);
    }

    // Últimas vendas de todos clientes - PDF e JSON
    @Operation(summary = "Última venda de cada cliente (PDF)",
            description = "Retorna a última venda registrada para cada cliente em PDF")
    @GetMapping(value = "/pdf/ultimasVendas", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getUltimasVendasTodosClientesPDF() {
        List<UltimaVendaDTO> resultados = relatorioService.getUltimasVendasTodosClientes();
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Últimas Vendas por Cliente"));
    }

    @Operation(summary = "Última venda de cada cliente (JSON)",
            description = "Retorna a última venda registrada para cada cliente em JSON")
    @GetMapping(value = "/ultimasVendas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UltimaVendaDTO>> getUltimasVendasTodosClientesJSON() {
        List<UltimaVendaDTO> resultados = relatorioService.getUltimasVendasTodosClientes();
        return ResponseEntity.ok(resultados);
    }

    // Vendas diárias - PDF e JSON
    @Operation(summary = "Vendas por data específica (PDF)")
    @GetMapping(value = "/pdf/diario/{data}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getVendasDiariasPDF(
            @Parameter(description = "Data no formato yyyy-MM-dd", example = "2023-05-15")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) {

        List<ClienteDiarioDTO> resultados = relatorioService.getVendasPorData(data);
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Vendas Diárias"));
    }

    @Operation(summary = "Vendas por data específica (JSON)")
    @GetMapping(value = "/diario/{data}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteDiarioDTO>> getVendasDiariasJSON(
            @Parameter(description = "Data no formato yyyy-MM-dd", example = "2023-05-15")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) {

        List<ClienteDiarioDTO> resultados = relatorioService.getVendasPorData(data);
        return ResponseEntity.ok(resultados);
    }

    // Vendas do dia atual - PDF e JSON
    @Operation(summary = "Vendas do dia atual (PDF)")
    @GetMapping(value = "/pdf/diario", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getVendasDoDiaAtualPDF() {
        List<ClienteDiarioDTO> resultados = relatorioService.getVendasDoDia();
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Vendas Hoje"));
    }

    @Operation(summary = "Vendas do dia atual (JSON)")
    @GetMapping(value = "/diario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteDiarioDTO>> getVendasDoDiaAtualJSON() {
        List<ClienteDiarioDTO> resultados = relatorioService.getVendasDoDia();
        return ResponseEntity.ok(resultados);
    }

//    // Aniversariantes do dia - PDF e JSON
//    @Operation(summary = "Aniversariantes do dia (PDF)")
//    @GetMapping(value = "/pdf/aniversariantesdia", produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<byte[]> getAniversariantesDoDiaPDF() {
//        List<AniversarianteDTO> resultados = relatorioService.getAniversariantesDoDia();
//        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Aniversariantes do Dia"));
//    }
//
//    @Operation(summary = "Aniversariantes do dia (JSON)")
//    @GetMapping(value = "/aniversariantesdia", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<AniversarianteDTO>> getAniversariantesDoDiaJSON() {
//        List<AniversarianteDTO> resultados = relatorioService.getAniversariantesDoDia();
//        return ResponseEntity.ok(resultados);
//    }

    // Aniversariantes do mês - PDF e JSON
    @Operation(summary = "Aniversariantes do mês (PDF)")
    @GetMapping(value = "/pdf/aniversariantes", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getAniversariantesDoMesPDF(
            @Parameter(description = "Mês (1-12)", example = "5")
            @RequestParam int mes) {

        List<AniversarianteDTO> resultados = relatorioService.getAniversariantesDoMes(mes);
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Aniversariantes do Mês"));
    }

    @Operation(summary = "Aniversariantes do mês (JSON)")
    @GetMapping(value = "/aniversariantes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AniversarianteDTO>> getAniversariantesDoMesJSON(
            @Parameter(description = "Mês (1-12)", example = "5")
            @RequestParam int mes) {

        List<AniversarianteDTO> resultados = relatorioService.getAniversariantesDoMes(mes);
        return ResponseEntity.ok(resultados);
    }

    // Clientes endividados - PDF e JSON
    @Operation(summary = "Clientes endividados (PDF)")
    @GetMapping(value = "/pdf/clientesEmAberto", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getClientesEndividadosPDF() {
        List<Cliente> resultados = relatorioService.getClientesEndividados();
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Clientes Endividados"));
    }

    @Operation(summary = "Clientes endividados (JSON)")
    @GetMapping(value = "/clientesEmAberto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getClientesEndividadosJSON() {
        List<Cliente> resultados = relatorioService.getClientesEndividados();
        return ResponseEntity.ok(resultados);
    }
}