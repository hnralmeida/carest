package com.les.carest.repository;

import com.les.carest.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID >{
    //    @Query("SELECT * FROM fornecedor u WHERE u.id=:id")
    //    Fornecedor findByIdTeste(@Param("id") Long id);

}

