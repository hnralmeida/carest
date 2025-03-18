package com.les.carest.repository;

import com.les.carest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    // Retorna um Optional<Usuario> para lidar com casos em que o usuário não é encontrado
    Optional<Usuario> findByEmail(String email);
}
