package com.les.carest.repository;

import com.les.carest.model.Saidas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.Date;

@Repository
public interface SaidasRepository extends JpaRepository<Saidas, UUID> {
    List<Saidas> findByDataPagamentoBetween(Date inicio, Date fim);

    List<Saidas> findByDataPagamentoBefore(Date data);
}

