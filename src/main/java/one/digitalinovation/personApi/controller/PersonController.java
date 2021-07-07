package one.digitalinovation.personApi.controller;

import one.digitalinovation.personApi.dto.MessageRequestDto;
import one.digitalinovation.personApi.dto.PersonDto;
import one.digitalinovation.personApi.entity.Person;
import one.digitalinovation.personApi.exception.PersonNotFoundException;
import one.digitalinovation.personApi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController     //controlador
@RequestMapping("api/v1/people")    //request da api o caminho principal corresponde ao nivel1 restfull
public class PersonController {

//aqui na classe controller vamos validar os dados de entrada
//usando anotations do hibernate em objetos clones das entidades(os objetos dto)
//assim fazemos a validação dos bancos de dados

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
    //vamos refatorar o metodo para usar um dto ao inves da entidade
    @PostMapping()
    @ResponseStatus(value= HttpStatus.CREATED)
    public MessageRequestDto createPerson(@RequestBody @Valid PersonDto pessoadto){
        return this.personService.createPerson(pessoadto);

    }

    @GetMapping()
    public List<PersonDto> listAll(){
        return this.personService.listAll();
    }

    @GetMapping("/{id}")    //a anotation PathVariable permite passar uma variavel para a url
    public PersonDto getById(@PathVariable Long id) throws PersonNotFoundException {return this.personService.getById(id);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  //garante que retorna nada
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        this.personService.deleteById(id);
    }

}
