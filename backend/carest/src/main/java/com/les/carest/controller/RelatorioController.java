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

    @Operation(summary = "Ticket médio por período")
    @GetMapping(value = "/ticketmedio", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getTicketMedioMultiplosClientes(
            @Parameter(description = "Data inicial (yyyy-MM-dd)", example = "2023-05-01")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,

            @Parameter(description = "Data final (yyyy-MM-dd)", example = "2023-05-31")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim) {

        List<TicketMedioDTO> resultados = relatorioService.getTicketMedioMultiplosClientes(dataInicio, dataFim);
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Ticket Médio"));
    }


    @Operation(summary = "Última venda por cliente")
    @GetMapping(value = "/ultimavenda/{clienteId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getUltimaVenda(
            @Parameter(description = "ID do cliente")
            @PathVariable UUID clienteId) {

        UltimaVendaDTO resultado = relatorioService.getUltimaVenda(clienteId);
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(List.of(resultado), "Última Venda"));
    }


    @Operation(summary = "Última venda de cada cliente",
            description = "Retorna a última venda registrada para cada cliente")
    @GetMapping(value = "/ultimasvendas", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getUltimasVendasTodosClientes() {
        List<UltimaVendaDTO> resultados = relatorioService.getUltimasVendasTodosClientes();
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Últimas Vendas por Cliente"));

    }

    @Operation(summary = "Vendas por data específica")
    @GetMapping(value = "/vendasdiaria", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getVendasDiarias(
            @Parameter(description = "Data no formato yyyy-MM-dd", example = "2023-05-15")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) {

        List<ClienteDiarioDTO> resultados = relatorioService.getVendasPorData(data);
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Vendas Diárias"));
    }

    @Operation(summary = "Vendas do dia atual")
    @GetMapping(value = "/vendashoje", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getVendasDoDiaAtual() {
        List<ClienteDiarioDTO> resultados = relatorioService.getVendasDoDia();
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Vendas Hoje"));
    }

    @Operation(summary = "Aniversariantes do dia")
    @GetMapping(value = "/aniversariantesdia", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getAniversariantesDoDia() {
        List<AniversarianteDTO> resultados = relatorioService.getAniversariantesDoDia();
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Aniversariantes do Dia"));
    }

    @Operation(summary = "Aniversariantes do mês")
    @GetMapping(value = "/aniversariantesmes", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getAniversariantesDoMes(
            @Parameter(description = "Mês (1-12)", example = "5")
            @RequestParam int mes) {

        List<AniversarianteDTO> resultados = relatorioService.getAniversariantesDoMes(mes);
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Aniversariantes do Mês"));
    }

    @Operation(summary = "Clientes endividados")
    @GetMapping(value = "/clientesendividados", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getClientesEndividados() {
        List<Cliente> resultados = relatorioService.getClientesEndividados();
        return ResponseEntity.ok(GenericPDF.gerarRelatorioBytes(resultados, "Clientes Endividados"));
    }


}

