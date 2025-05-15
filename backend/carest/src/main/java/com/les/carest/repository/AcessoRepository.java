package com.les.carest.repository;

import com.les.carest.model.Acesso;
import com.les.carest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, UUID> {


    @Query("SELECT c FROM Cliente c WHERE c.codigo = :codigo")
    Cliente findClienteByCodigo(@Param("codigo") String codigo);

    @Query("SELECT a FROM Acesso a WHERE a.cliente.codigo = :codigoCliente and a.cliente.em_uso = true")
    Acesso findUltimoAcesso(@Param("codigoCliente") String codigoCliente);


    @Query("UPDATE Cliente c SET c.em_uso = :uso WHERE c.id = :clienteId")
    void estadoDeAcesso(@Param("clienteId") UUID clienteId,@Param("uso") boolean uso);
}
