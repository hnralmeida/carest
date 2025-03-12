package com.les.carest.service;

import com.les.carest.model.Recarga;
import com.les.carest.repository.RecargaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Tag(name = "RecargaService", description = "Acesso aos métodos da Recarga")
public class RecargaService extends _GenericService<Recarga, RecargaRepository> {

    protected RecargaService(RecargaRepository RecargaRepository) {
        super(RecargaRepository);
    }

}
