package com.les.carest.service;

import com.les.carest.DTO.ClienteDTO;
import com.les.carest.model.Cliente;
import com.les.carest.model.Saidas;
import com.les.carest.relatoriosDTO.ClienteDiarioDTO;
import com.les.carest.repository.SaidasRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class SaidasService extends _GenericService<Saidas, SaidasRepository> {

    private final SaidasRepository saidasRepository;

    public SaidasService(SaidasRepository saidasRepository) {
        super(saidasRepository);
        this.saidasRepository = saidasRepository;
    }

}