package com.les.carest.repository;

import com.les.carest.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID >{
    //    @Query("SELECT * FROM produto u WHERE u.id=:id")
    //    Produto findByIdTeste(@Param("id") Long id);

}

