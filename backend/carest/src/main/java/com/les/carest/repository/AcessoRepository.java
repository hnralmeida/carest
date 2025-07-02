package com.les.carest.repository;

import com.les.carest.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, UUID> {

    @Query("SELECT a FROM Acesso a WHERE a.cliente.codigo = :codigoCliente AND a.saida IS NULL ORDER BY a.entrada DESC")
    List<Acesso> findUltimoAcessoAtivo(@Param("codigoCliente") String codigoCliente, Pageable pageable);

}