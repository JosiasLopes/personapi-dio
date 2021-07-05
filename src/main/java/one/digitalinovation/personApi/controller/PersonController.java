package one.digitalinovation.personApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //controlador
@RequestMapping("api/v1/people")    //request da api o caminho principal corresponde ao nivel1 restfull
public class PersonController {

    @GetMapping("/")    //verbo get no browser
    public String test(){
        return "123...testando";
    }

}
