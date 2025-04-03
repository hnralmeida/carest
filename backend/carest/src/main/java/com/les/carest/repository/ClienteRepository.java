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

    @Query("SELECT c FROM Cliente c WHERE EXTRACT(MONTH FROM c.nascimento) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(DAY FROM c.nascimento) = EXTRACT(DAY FROM CURRENT_DATE)")
    List<Cliente> findAniversariantesDoDia();

    // Consulta para data específica
    @Query("SELECT c FROM Cliente c WHERE EXTRACT(MONTH FROM c.nascimento) = :mes AND EXTRACT(DAY FROM c.nascimento) = :dia")
    List<Cliente> findAniversariantesPorData(@Param("mes") int mes, @Param("dia") int dia);

    // Consulta para mes
    @Query("SELECT c FROM Cliente c WHERE EXTRACT(MONTH FROM c.nascimento) = :mes")
    List<Cliente> findAniversariantesPorMes(@Param("mes") int mes);
}