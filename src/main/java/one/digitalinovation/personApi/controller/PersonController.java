package one.digitalinovation.personApi.controller;

import one.digitalinovation.personApi.dto.MessageRequestDto;
import one.digitalinovation.personApi.entity.Person;
import one.digitalinovation.personApi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController     //controlador
@RequestMapping("api/v1/people")    //request da api o caminho principal corresponde ao nivel1 restfull
public class PersonController {


    private PersonService personService;

    @Autowired  //faz a injeção od PersonRepository é como se ele fizesse um construtor
    public PersonController(PersonService personService){
    this.personService = personService;
    }

    @GetMapping("/")    //verbo get no browser
    public String test(){
        return "123...testando";
    }

    //usamos requestBody por que precisamos informar de onde vem o objeto pessoa e ele vem da requisição
    //seguindo a arquitetura o controller chama o service que possui o repository
    //por padrão ela retorna 200 então pedimos para ela retornar o status da operação
    //caso tudo ocorra bem
    @PostMapping()
    @ResponseStatus(value= HttpStatus.CREATED)
    public MessageRequestDto createPerson(@RequestBody Person pessoa){
        return this.personService.createPerson(pessoa);

    }

}
