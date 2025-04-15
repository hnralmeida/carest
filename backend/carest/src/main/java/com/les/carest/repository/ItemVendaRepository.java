package com.les.carest.repository;

import com.les.carest.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// ItemVendaRepository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, UUID> {
}