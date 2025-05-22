package com.les.carest.repository;

import com.les.carest.model.CompraFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CompraFornecedorRepository extends JpaRepository<CompraFornecedor, UUID>{
}

