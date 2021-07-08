package one.digitalinovation.personApi.service;

import one.digitalinovation.personApi.dto.MessageRequestDto;
import one.digitalinovation.personApi.dto.PersonDto;
import one.digitalinovation.personApi.entity.Person;
import one.digitalinovation.personApi.exception.PersonNotFoundException;
import one.digitalinovation.personApi.mapper.PersonMapper;
import one.digitalinovation.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //a anotation service diz que essa classe é um service e ele possui suporte para as transações
public class PersonService {

    private final PersonRepository personRepo;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired  //faz a injeção od PersonRepository é como se ele fizesse um construtor
    public PersonService(PersonRepository personRepo){
        this.personRepo = personRepo;
    }

    public MessageRequestDto createPerson(PersonDto pessoa){
        //a questão com esse metodo foi criar um metodo de converter o personDto em person
        //e pra isso foi feita uma mudança na classe PhoneDto que recebeu um metodo que retorna a lista do phonesdto
        //mapeia os objetos e os coloca numa lista de phones para serem usados
        //é trabalhoso mas funciona
        //agora a idea e usar a lib mapstruct onde atraves de uma interface mapper podemos
        //mapear o dto para uma entity
        //Person p = Person.builder().firstName(pessoa.getFirstName()).cpf(pessoa.getCpf())
         //           .lastName(pessoa.getLastName()).birthDate(LocalDate.now()).
         //               phones(PhoneDto.builder().build().PhoneDtToPhone(pessoa.getPhones()))
         //       .build();//this.personRepo.save(pessoa);
        Person ptosave = personMapper.toPerson(pessoa);
        Person personsaved = this.personRepo.save(ptosave);

        return createMessageRequestDto(personsaved.getId(),"Created Person... ");
       // return new MessageRequestDto("Created Person... "+personsaved.getId());
    }

    public List<PersonDto> listAll(){
        List<Person> pessoas = this.personRepo.findAll();
        return pessoas.stream().map(this.personMapper::toPersonDTO).collect(Collectors.toList());

    }

    public PersonDto getById(Long id) throws PersonNotFoundException {
       /* Optional<Person> opt =this.personRepo.findById(id);
        if(opt.isPresent()){
            return this.personMapper.toPersonDTO(opt.get());
        }else{
            throw new PersonNotFoundException(id);
        }

        */
        Person person = personIsExists(id);
        return this.personMapper.toPersonDTO(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        Person person = personIsExists(id);
        this.personRepo.delete(person);
    }

    //como mandam as boas praticas não devemos ter codigos duplicados( o codigo dos metodos deleteById e getBYId)
    //então vamos concentralo em um unico metodo chamado personIsExists
    public Person personIsExists(Long id) throws PersonNotFoundException {
        Person person= this.personRepo.findById(id).orElseThrow(()->new PersonNotFoundException(id));
        return person;
    }

    public MessageRequestDto updateById(Long id, PersonDto personDto) throws PersonNotFoundException {
        personIsExists(id);
        Person ptoupdate = personMapper.toPerson(personDto);
        Person personupdated = this.personRepo.save(ptoupdate);

        return createMessageRequestDto(personupdated.getId(),"Updated Person... ");
    }

    private MessageRequestDto createMessageRequestDto(Long id, String s) {
        return MessageRequestDto.builder().message(s+ id).build();
    }
}
