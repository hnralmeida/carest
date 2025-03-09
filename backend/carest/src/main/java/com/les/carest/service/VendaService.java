package com.les.carest.service;

import com.les.carest.model.Venda;
import com.les.carest.repository.VendaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Tag(name = "VendaService", description = "Acesso aos métodos da Venda")
public class VendaService extends _GenericService<Venda, VendaRepository> {

    protected void VendaRepository(VendaRepository VendaRepository) {
        super(VendaRepository);
    }
}
