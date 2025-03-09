package main.java.com.les.carest.controller;

import com.les.carest.domain.Venda;
import com.les.carest.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Validated
@RestController
//tirar duvida do mapping //@RequestMapping("/cliente")
public class VendaController extends GenericController {
    public VendaController(VendaService vendaService) {
        super(vendaService);
    }

}