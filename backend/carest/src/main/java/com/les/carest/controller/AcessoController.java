package com.les.carest.controller;

import com.les.carest.model.Acesso;
import com.les.carest.service.AcessoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

public class AcessoController extends _GenericController<Acesso> {
    private AcessoService acessoService;

    public AcessoController(AcessoService service) {
        super(service);
        this.acessoService = service;
    }



    @PostMapping("/entrada")  // POST /acessos/entrada?codigo=123
    public ResponseEntity<Acesso> entrada(@RequestParam String codigo){
        // Cria um novo registro de acesso com a data/hora atual
        Acesso acesso = new Acesso();

        acesso.setCliente(acessoService.findByCodigo(codigo));

        if(acesso.getCliente().isEm_uso() || acesso.getCliente().getBloqueado()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(acesso);//modificar
        }
        else{


            acessoService.sessao(acesso.getCliente().getId(),true);

            acesso.setEntrada(new Date());// Data/hora atual
            acessoService.criarAcesso(acesso);

            return ResponseEntity.ok(acesso);
        }
    }

    @PutMapping("/saida")
    public ResponseEntity<Acesso> saida(@RequestParam UUID id) {
        Acesso acesso = acessoService.buscarPorId(id);
        acesso.setSaida(new Date());
        acessoService.atualizar(acesso.getId(), acesso);

        acessoService.sessao(acesso.getCliente().getId(),false);

        return ResponseEntity.ok(acesso);
    }

}
