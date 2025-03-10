package com.les.carest.repository;

import com.les.carest.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface VendaRepository extends JpaRepository<Venda, UUID >{
    //    @Query("SELECT * FROM venda u WHERE u.id=:id")
    //    Venda findByIdTeste(@Param("id") Long id);

}

