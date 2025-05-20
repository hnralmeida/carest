package com.les.carest.service;

import com.les.carest.DTO.ProdutoDTO_Balanca;
import com.les.carest.model.ProdutoBalanca;
import com.les.carest.repository.ProdutoBalancaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoBalancaService extends _GenericService<ProdutoBalanca, ProdutoBalancaRepository> {

    public ProdutoBalancaService(ProdutoBalancaRepository repository) {
        super(repository);
    }

    // Métodos de conversão
    private ProdutoBalanca toEntity(ProdutoDTO_Balanca dto) {
        ProdutoBalanca produto = new ProdutoBalanca();
        produto.setId(dto.getId());
        produto.setValor(dto.getValor());
        produto.setData(dto.getData());
        return produto;
    }

    private ProdutoDTO_Balanca toDTO(ProdutoBalanca produto) {
        ProdutoDTO_Balanca dto = new ProdutoDTO_Balanca(produto.getId(), produto.getValor());
        dto.setData(produto.getData());
        return dto;
    }

    // Métodos específicos para DTO
    public ProdutoDTO_Balanca criarProduto(ProdutoDTO_Balanca produtoDTO) {
        ProdutoBalanca produto = new ProdutoBalanca();
        produto.setData(produtoDTO.getData());//Adicionar tipo
        produto.setNome("BALANCA");//Adicionar tipo
        produto.setValor(produtoDTO.getValor());//Adicionar tipo
        return toDTO(super.criar(produto));
    }

    public ProdutoDTO_Balanca buscarProdutoPorId(UUID id) {
        return toDTO(super.buscarPorId(id));
    }

    public List<ProdutoDTO_Balanca> listarTodosProdutos() {
        return super.listar().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO_Balanca atualizarProduto(UUID id, ProdutoDTO_Balanca produtoDTO) {
        produtoDTO.setId(id); // Garante que o ID seja o mesmo
        ProdutoBalanca produto = toEntity(produtoDTO);
        return toDTO(super.atualizar(id, produto));
    }

    public void excluirProduto(UUID id) {
        super.excluir(id);
    }

    public ProdutoBalanca precoBalanca(){
        return this.repositoryGenerics.findMostRecentProdutoBalanca().getFirst();
    }


}