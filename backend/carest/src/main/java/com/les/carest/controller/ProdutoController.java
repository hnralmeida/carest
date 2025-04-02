package main.java.com.les.carest.controller;

import com.les.carest.controller.GenericController;
import com.les.carest.service.ProdutoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;



@Validated
@RestController
//tirar duvida do mapping //@RequestMapping("/cliente")
public class ProdutoController extends GenericController {
    public ProdutoController(ProdutoService produtoService) {
        super(produtoService);
    }

}