package main.java.com.les.carest.repository;

import com.les.carest.model.Recarga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface RecargaRepository extends JpaRepository<Recarga, UUID >{
    //    @Query("SELECT * FROM recarga u WHERE u.id=:id")
    //    Recarga findByIdTeste(@Param("id") Long id);

}

