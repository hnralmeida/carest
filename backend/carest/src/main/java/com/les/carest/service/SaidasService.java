package com.les.carest.service;

import com.les.carest.model.Saidas;
import com.les.carest.repository.SaidasRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SaidasService extends _GenericService<Saidas, SaidasRepository> {

    private final SaidasRepository saidasRepository;

    public SaidasService(SaidasRepository saidasRepository) {
        super(saidasRepository);
        this.saidasRepository = saidasRepository;
    }

}