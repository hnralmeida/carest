package com.les.carest.controller;

import com.les.carest.DTO.ProdutoDTO_Balanca;
import com.les.carest.DTO.ProdutoDTO_Serial;
import com.les.carest.service.ProdutoBalancaService;
import com.les.carest.service.ProdutoSerialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos Controller", description = "Endpoints unificados para produtos (Balança e Serial)")
public class ProdutoController {

    private final ProdutoBalancaService produtoBalancaService;
    private final ProdutoSerialService produtoSerialService;

    public ProdutoController(ProdutoBalancaService produtoBalancaService, ProdutoSerialService produtoSerialService) {
        this.produtoBalancaService = produtoBalancaService;
        this.produtoSerialService = produtoSerialService;

    }

    // Endpoints para Produto Balança
    @PostMapping("/balanca")
    @Operation(summary = "Cria um novo produto de balança")
    public ResponseEntity<ProdutoDTO_Balanca> criarProdutoBalanca(@Valid @RequestBody ProdutoDTO_Balanca produtoDTO) {
        ProdutoDTO_Balanca novoProduto = produtoBalancaService.criarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping("/balanca/{id}")
    @Operation(summary = "Busca um produto de balança por ID")
    public ResponseEntity<ProdutoDTO_Balanca> buscarProdutoBalancaPorId(@PathVariable UUID id) {
        ProdutoDTO_Balanca produto = produtoBalancaService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/balanca")
    @Operation(summary = "Lista todos os produtos de balança")
    public ResponseEntity<List<ProdutoDTO_Balanca>> listarTodosProdutosBalanca() {
        List<ProdutoDTO_Balanca> produtos = produtoBalancaService.listarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/balanca/{id}")
    @Operation(summary = "Atualiza um produto de balança")
    public ResponseEntity<ProdutoDTO_Balanca> atualizarProdutoBalanca(
            @PathVariable UUID id,
            @Valid @RequestBody ProdutoDTO_Balanca produtoDTO) {
        ProdutoDTO_Balanca produtoAtualizado = produtoBalancaService.atualizarProduto(id, produtoDTO);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/balanca/{id}")
    @Operation(summary = "Remove um produto de balança")
    public ResponseEntity<Void> excluirProdutoBalanca(@PathVariable UUID id) {

        produtoBalancaService.excluirProduto(id);
        return ResponseEntity.noContent().build();


    }

    //
    // ====== ENDPOINTS PARA PRODUTO SERIAL (ESPELHO DO BALANÇA) ======
    //
    @PostMapping("/serial")
    @Operation(summary = "Cria um novo produto serial")
    public ResponseEntity<ProdutoDTO_Serial> criarProdutoSerial(@Valid @RequestBody ProdutoDTO_Serial produtoDTO) {
        ProdutoDTO_Serial novoProduto = produtoSerialService.criarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping("/serial/{id}")
    @Operation(summary = "Busca um produto serial por ID")
    public ResponseEntity<ProdutoDTO_Serial> buscarProdutoSerialPorId(@PathVariable UUID id) {
        ProdutoDTO_Serial produto = produtoSerialService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/serial")
    @Operation(summary = "Lista todos os produtos seriais")
    public ResponseEntity<List<ProdutoDTO_Serial>> listarTodosProdutosSeriais() {
        List<ProdutoDTO_Serial> produtos = produtoSerialService.listarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/serial/{id}")
    @Operation(summary = "Atualiza um produto serial")
    public ResponseEntity<ProdutoDTO_Serial> atualizarProdutoSerial(
            @PathVariable UUID id,
            @Valid @RequestBody ProdutoDTO_Serial produtoDTO) {
        ProdutoDTO_Serial produtoAtualizado = produtoSerialService.atualizarProduto(id, produtoDTO);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/serial/{id}")
    @Operation(summary = "Remove um produto serial")
    public ResponseEntity<Void> excluirProdutoSerial(@PathVariable UUID id) {
        produtoSerialService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}
