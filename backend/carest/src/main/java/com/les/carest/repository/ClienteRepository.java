package com.les.carest.repository;

import com.les.carest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("select c from Cliente c where c.saldo < 0")
    List<Cliente> findEndividados();//relatorio?

    @Query("select c from Cliente c where c.codigo =:codigo")
    Cliente findByCodigoCliente(@Param("codigo") String codigo);

}
