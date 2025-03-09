package main.java.com.les.carest.repository;


import com.les.carest.model.ProdutoBalanca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoBalancaRepository extends JpaRepository< ProdutoBalanca, UUID >{
    //    @Query("SELECT * FROM produtoBalanca u WHERE u.id=:id")
    //    ProdutoBalanca findByIdTeste(@Param("id") Long id);

}