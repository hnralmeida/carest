package com.les.carest.repository;

import com.les.carest.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

// VendaRepository
public interface VendaRepository extends JpaRepository<Venda, UUID> {
    List<Venda> findByClienteId(UUID clienteId);  // Busca vendas por cliente
}
