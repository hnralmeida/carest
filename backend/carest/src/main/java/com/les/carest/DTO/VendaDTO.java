package com.les.carest.DTO;

import java.util.List;
import java.util.UUID;

public class VendaDTO {
    private UUID clienteId;
    private List<ItemVendaDTO> itens;

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemVendaDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaDTO> itens) {
        this.itens = itens;
    }
}