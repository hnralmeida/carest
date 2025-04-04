package com.les.carest.controller;

import com.les.carest.DTO.ProdutoDTO_Balanca;
import com.les.carest.service.ProdutoBalancaService;
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

    public ProdutoController(ProdutoBalancaService produtoBalancaService) {
        this.produtoBalancaService = produtoBalancaService;
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

    // Endpoints para Produto Serial serão adicionados depois...
}