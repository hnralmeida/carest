package com.les.carest.relatoriosDTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class UltimaVendaDTO {
    private UUID vendaId;
    private UUID clienteId;
    private String clienteNome;
    private LocalDateTime dataVenda;
    private Double valor;

    // Construtor para consultas nativas
    public UltimaVendaDTO(Object[] resultado) {
        if (resultado == null || resultado.length < 5) {
            throw new IllegalArgumentException("Resultado inválido: array deve ter 5 elementos");
        }

        try {
            this.vendaId = (UUID) Objects.requireNonNull(resultado[0], "ID da venda não pode ser nulo");
            this.clienteId = (UUID) Objects.requireNonNull(resultado[1], "ID do cliente não pode ser nulo");
            this.clienteNome = (String) Objects.requireNonNull(resultado[2], "Nome do cliente não pode ser nulo");

            // Conversão segura de Timestamp para LocalDateTime
            this.dataVenda = resultado[3] != null
                    ? ((Timestamp) resultado[3]).toLocalDateTime()
                    : null;

            this.valor = resultado[4] != null
                    ? ((Number) resultado[4]).doubleValue()
                    : null;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Tipo de dado inválido no resultado", e);
        }
    }

    // Getters (omiti os setters por brevidade)
    public UUID getVendaId() { return vendaId; }
    public UUID getClienteId() { return clienteId; }
    public String getClienteNome() { return clienteNome; }
    public LocalDateTime getDataVenda() { return dataVenda; }
    public Double getValor() { return valor; }
}