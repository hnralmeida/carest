package com.les.carest.repository;

import com.les.carest.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, UUID> {

    // Consulta para encontrar o último acesso ativo de um cliente
    @Query("SELECT a FROM Acesso a WHERE a.cliente.codigo = :codigoCliente AND a.saida IS NULL ORDER BY a.entrada DESC")
    Acesso findUltimoAcessoAtivo(@Param("codigoCliente") String codigoCliente);


}