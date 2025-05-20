package com.les.carest.DTO;

import com.les.carest.model.Cliente;
import com.les.carest.model.Produto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class NotaFiscalDTO {
    private Cliente cliente;
    private List<Produto> produtos;
    private BigDecimal valorPago;
    private LocalDateTime dataEmissao;

    public NotaFiscalDTO(Cliente cliente, List<Produto> produtos, BigDecimal valorPago) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.valorPago = valorPago;
        this.dataEmissao = LocalDateTime.now();
    }

    // Getters
    public BigDecimal getTotal() {
        return produtos.stream()
                .map(p -> BigDecimal.valueOf(p.getValor()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTroco() {
        return valorPago.subtract(getTotal());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }
}