package com.les.carest.repository;

import com.les.carest.model.Cliente;
import com.les.carest.model.Recarga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface RecargaRepository extends JpaRepository<Recarga, UUID> {

    // Correção: Retorna Cliente (entidade), não DTO
    @Query("SELECT c FROM Cliente c WHERE c.codigo = :codigo")
    Cliente findClienteByCodigo(@Param("codigo") String codigo);

    // Correção: Retorna Cliente (entidade), não DTO
    @Query("SELECT c FROM Cliente c WHERE c.id = :idCliente")
    Cliente findClienteById(@Param("idCliente") UUID idCliente);

    // Correção: Retorna Recarga (entidade) e ajuste do parâmetro
    @Query("SELECT r FROM Recarga r WHERE r.data = :data AND r.cliente.id = :idCliente")
    List<Recarga> findByDataAndClienteId(@Param("data") Date data, @Param("idCliente") UUID idCliente);

}




