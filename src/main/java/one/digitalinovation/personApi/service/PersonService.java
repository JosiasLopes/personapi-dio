package one.digitalinovation.personApi.service;

import one.digitalinovation.personApi.dto.MessageRequestDto;
import one.digitalinovation.personApi.entity.Person;
import one.digitalinovation.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service //a anotation service diz que essa classe é um service e ele possui suporte para as transações
public class PersonService {

    private PersonRepository personRepo;

    @Autowired  //faz a injeção od PersonRepository é como se ele fizesse um construtor
    public PersonService(PersonRepository personRepo){
        this.personRepo = personRepo;
    }

    public MessageRequestDto createPerson(Person pessoa){
        Person p = this.personRepo.save(pessoa);
        return MessageRequestDto.builder().message("Created Person... "+p.getId()).build();
    }
}
