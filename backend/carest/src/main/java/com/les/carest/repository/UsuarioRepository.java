//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.les.carest.repository;

import com.les.carest.model.Usuario;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UsuarioRepository extends JpaRepository <Usuario, UUID> {

    //@Query("SELECT * FROM usuario u WHERE u.id=:id")
    Usuario findByEmail(String email);

}
    