package com.les.carest.repository;

import com.les.carest.model.ProdutoSerial;
import com.les.carest.model.Recarga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;



@Repository
public interface ProdutoSerialRepository extends JpaRepository<ProdutoSerial, UUID> {

        @Query("SELECT u FROM ProdutoSerial u WHERE u.codigo=:codigo")
        ProdutoSerial buscarByCodigo(@Param("codigo") String codigo);

}
