package com.les.carest.repository;

import com.les.carest.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// VendaRepository
public interface VendaRepository extends JpaRepository<Venda, UUID> {
    List<Venda> findByClienteId(UUID clienteId);  // Busca vendas por cliente

    @Query("UPDATE Cliente c SET c.saldo = c.saldo - :desconto WHERE c.id = :clienteId")
    void diminuirSaldo(@Param("clienteId") UUID clienteId, @Param("desconto") double desconto);

    List<Venda> findByDataVendaBetween(Date inicio, Date fim);

    List<Venda> findByDataVendaBefore(Date data);
}
