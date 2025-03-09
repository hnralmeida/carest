package main.java.com.les.carest.repository;

import com.les.carest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository  extends JpaRepository <Cliente , UUID>{
    //    @Query("SELECT * FROM cliente u WHERE u.id=:id")
    //    Cliente findByIdTeste(@Param("id") Long id);

}
