package com.les.carest.repository;

import com.les.carest.model.Cliente;
import com.les.carest.model.ProdutoBalanca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// ProdutoBalancaRepository.java
@Repository
public interface ProdutoBalancaRepository extends JpaRepository<ProdutoBalanca, UUID> {

    @Query("SELECT c FROM ProdutoBalanca c WHERE EXTRACT(MONTH FROM c.data) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(DAY FROM c.data) = EXTRACT(DAY FROM CURRENT_DATE)")
    List<ProdutoBalanca> findPrecoDia();

    @Query("SELECT p FROM ProdutoBalanca p ORDER BY p.data DESC")
    List<ProdutoBalanca> findMostRecentProdutoBalanca();
}