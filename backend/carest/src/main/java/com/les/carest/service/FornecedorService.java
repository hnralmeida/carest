package com.les.carest.service;

import com.les.carest.model.Fornecedor;
import com.les.carest.repository.FornecedorRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Tag(name = "FornecedorService", description = "Acesso aos métodos de Fornecedor")
public class FornecedorService extends _GenericService<Fornecedor, FornecedorRepository> {

    protected FornecedorRepository(FornecedorRepository FornecedorRepository) {
        super(FornecedorRepository);
    }


}
