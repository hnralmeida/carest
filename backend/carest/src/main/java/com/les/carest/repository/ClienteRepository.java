package com.les.carest.repository;

import com.les.carest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("SELECT c FROM Cliente c WHERE FUNCTION('MONTH', c.nascimento) = FUNCTION('MONTH', CURRENT_DATE) AND FUNCTION('DAY', c.nascimento) = FUNCTION('DAY', CURRENT_DATE)")
    List<Cliente> findAniversariantesDoDia();

    @Query("SELECT c FROM Cliente c WHERE FUNCTION('MONTH', c.nascimento) = :mes AND FUNCTION('DAY', c.nascimento) = :dia")
    List<Cliente> findAniversariantesPorData(@Param("mes") int mes, @Param("dia") int dia);
}