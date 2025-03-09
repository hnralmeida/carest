package main.java.com.les.carest.controller;

import com.les.carest.domain.Recarga;
import com.les.carest.service.RecargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Validated
@RestController
//tirar duvida do mapping //@RequestMapping("/cliente")
public class RecargaController extends GenericController {
    public RecargaController(RecargaService recargaService) {
        super(recargaService);
    }

}