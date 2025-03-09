package main.java.com.les.carest.repository;

import com.les.carest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, UUID> {

    //    @Query("SELECT * FROM usuario u WHERE u.id=:id")
    //    Usuario findByIdTeste(@Param("id") Long id);

}
