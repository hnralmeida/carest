package com.les.carest.repository;

import com.les.carest.model.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface TelaRepository extends JpaRepository<Tela, UUID >{
    //    @Query("SELECT * FROM tela u WHERE u.id=:id")
    //    Tela findByIdTeste(@Param("id") Long id);

}

