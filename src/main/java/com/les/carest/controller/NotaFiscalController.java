//package com.les.carest.controller;
//
//import com.les.carest.DTO.NotaFiscalDTO;
//import com.les.carest.model.Cliente;
//import com.les.carest.model.Produto;
//import com.les.carest.repository.ClienteRepository;
//import com.les.carest.repository.ProdutoRepository;
//import com.les.carest.service.PrinterService;
//import com.les.carest.util.PrinterUtils;
//import org.springframework.web.bind.annotation.*;
//
//import javax.print.PrintService;
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/notas-fiscais")
//public class NotaFiscalController {
//
//    private final PrinterService printerService;
//    private final ClienteRepository clienteRepository;
//    private final ProdutoRepository produtoRepository;
//
//    public NotaFiscalController(PrinterService printerService,
//                                ClienteRepository clienteRepository,
//                                ProdutoRepository produtoRepository) {
//        this.printerService = printerService;
//        this.clienteRepository = clienteRepository;
//        this.produtoRepository = produtoRepository;
//    }
//
//    @PostMapping("/imprimir")
//    public String imprimirNotaFiscal(
//            @RequestParam UUID clienteId,
//            @RequestParam List<UUID> produtosIds,
//            @RequestParam BigDecimal valorPago) throws Exception {
//
//        PrintService printer = PrinterUtils.getDefaultPrinter();
//        if (printer == null) {
//            return "Erro: Nenhuma impressora encontrada";
//        }
//
//        Cliente cliente = clienteRepository.findById(clienteId)
//                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
//
//        List<Produto> produtos = produtoRepository.findAllById(produtosIds);
//
//        NotaFiscalDTO nota = new NotaFiscalDTO(cliente, produtos, valorPago);
//        printerService.imprimirNotaFiscal(nota, printer);
//
//        return "Nota fiscal enviada para impressão na " + printer.getName();
//    }
//
//    @GetMapping("/impressoras")
//    public List<String> listarImpressoras() {
//        return PrinterUtils.getAvailablePrinters().stream()
//                .map(PrintService::getName)
//                .collect(Collectors.toList());
//    }
//}