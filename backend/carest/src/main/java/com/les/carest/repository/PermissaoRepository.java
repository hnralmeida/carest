package main.java.com.les.carest.repository;

import com.les.carest.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, UUID >{
    //    @Query("SELECT * FROM permissao u WHERE u.id=:id")
    //    Permissao findByIdTeste(@Param("id") Long id);

}

