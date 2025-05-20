package com.les.carest.service;

import com.les.carest.DTO.ItemVendaDTO;
import com.les.carest.DTO.VendaDTO;
import com.les.carest.model.Cliente;
import com.les.carest.model.Venda;
import com.les.carest.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.les.carest.model.ItemVenda;
import com.les.carest.model.Produto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VendaService {

    @Autowired private VendaRepository vendaRepository;


    @Autowired private ClienteRepository clienteRepository;

    @Autowired private ItemVendaRepository itemVendaRepository;

    @Autowired private ProdutoRepository produtoRepository;



    @Transactional
    public Venda criarVenda(VendaDTO vendaDTO) {

        Cliente cliente = clienteRepository.findById(vendaDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // 2. Cria a venda
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDataVenda(new Date());
        venda.setValorTotal(0.0);
        venda = vendaRepository.save(venda);  // Salva para gerar ID

        // 3. Processa itens
        double valorTotal = 0.0;
        for (ItemVendaDTO itemDTO : vendaDTO.getItens()) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ItemVenda item = new ItemVenda();
            item.setVenda(venda);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getValor());  // Usa o preço atual do produto

            valorTotal += item.getPrecoUnitario() * item.getQuantidade();
            itemVendaRepository.save(item);
        }

        // 4. Atualiza valor total
        venda.setValorTotal(valorTotal);
        return vendaRepository.save(venda);
    }


    public Venda buscarPorId(UUID id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    @Transactional
    public void deletarVenda(UUID id) {
        Venda venda = buscarPorId(id);  // Valida existência
        vendaRepository.delete(venda);
        // Itens serão deletados em cascata (orphanRemoval = true)
    }

}