package main.java.com.les.carest.controller;

import com.les.carest.domain.ProdutoBalanca;
import com.les.carest.service.ProdutoBalancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Validated
@RestController
//tirar duvida do mapping //@RequestMapping("/cliente")
public class ProdutoBalancaController extends GenericController {
    public ProdutoBalancaController(ProdutoBalancaService produtoBalancaService) {
        super(produtoBalancaService);
    }

}