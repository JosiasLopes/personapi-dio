package one.digitalinovation.personApi.controller;

import one.digitalinovation.personApi.dto.MessageRequestDto;
import one.digitalinovation.personApi.entity.Person;
import one.digitalinovation.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController     //controlador
@RequestMapping("api/v1/people")    //request da api o caminho principal corresponde ao nivel1 restfull
public class PersonController {


    private PersonRepository personRepo;

    @Autowired  //faz a injeção od PersonRepository é como se ele fizesse um construtor
    public PersonController(PersonRepository personRepo){
    this.personRepo = personRepo;
    }

    @GetMapping("/")    //verbo get no browser
    public String test(){
        return "123...testando";
    }

    //usamos requestBody por que precisamos informar de onde vem o objeto pessoa e ele vem da requisição
    @PostMapping()
    public MessageRequestDto createPerson(@RequestBody Person pessoa){
        Person p = this.personRepo.save(pessoa);
        return MessageRequestDto.builder().message("Created Person... "+p.getId()).build();
    }

}
