package com.les.carest.service;

import com.les.carest.DTO.ProdutoDTO_Serial;
import com.les.carest.model.ProdutoBalanca;
import com.les.carest.model.ProdutoSerial;
import com.les.carest.repository.ProdutoSerialRepository;
import com.les.carest.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoSerialService extends _GenericService<ProdutoSerial, ProdutoSerialRepository> {

    public ProdutoSerialService(ProdutoSerialRepository repository) {
        super(repository);
    }

    // Métodos de conversão
    private ProdutoSerial toEntity(ProdutoDTO_Serial dto) {
        ProdutoSerial produto = new ProdutoSerial();
        produto.setId(dto.getId());
        produto.setValor(dto.getValor());
        produto.setCusto(dto.getCusto());
        produto.setCodigo(dto.getCodigo()); // Campo específico do Serial (equivalente ao "data" da balança)
        produto.setNome(dto.getNome()); // Definindo o tipo fixo (como no Balança)
        return produto;
    }

    private ProdutoDTO_Serial toDTO(ProdutoSerial produto) {
        ProdutoDTO_Serial dto = new ProdutoDTO_Serial();
        dto.setId(produto.getId());
        dto.setValor(produto.getValor());
        dto.setCusto(produto.getCusto());
        dto.setNome(produto.getNome());
        dto.setCodigo(produto.getCodigo()); // Campo específico
        return dto;
    }

    // Métodos CRUD (idênticos ao Balança)
    public ProdutoDTO_Serial criarProduto(ProdutoDTO_Serial produtoDTO) {
        ProdutoSerial produto = new ProdutoSerial();
        produto.setCodigo(produtoDTO.getCodigo());//Adicionar tipo
        produto.setNome(produtoDTO.getNome());//Adicionar tipo
        produto.setValor(produtoDTO.getValor());//Adicionar tipo
        produto.setCusto(produtoDTO.getCusto());//Adicionar tipo
        return toDTO(super.criar(produto));
    }

    public ProdutoDTO_Serial buscarProdutoPorId(UUID id) {
        return toDTO(super.buscarPorId(id));
    }

    public ProdutoDTO_Serial buscarProdutoPorCodigo(String codigo) {
        Optional<ProdutoSerial> produto = Optional.ofNullable(((ProdutoSerialRepository) this.repositoryGenerics).buscarByCodigo(codigo));
        return produto.map(this::toDTO).orElse(null);
    }

    public List<ProdutoDTO_Serial> listarTodosProdutos() {
        return super.listar().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO_Serial atualizarProduto(UUID id, ProdutoDTO_Serial produtoDTO) {
        produtoDTO.setId(id); // Garante que o ID seja o mesmo
        ProdutoSerial produto = toEntity(produtoDTO);
        return toDTO(super.atualizar(id, produto));
    }

    public void excluirProduto(UUID id) {
        super.excluir(id);
    }
}