package com.les.carest.service;

import com.les.carest.model.CompraFornecedor;
import com.les.carest.repository.CompraFornecedorRepository;
import org.springframework.stereotype.Service;


@Service
public class CompraFornecedorService extends _GenericService<CompraFornecedor, CompraFornecedorRepository> {

    public CompraFornecedorService(CompraFornecedorRepository repository) {
        super(repository);
    }



}
