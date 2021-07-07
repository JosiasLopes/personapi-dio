package one.digitalinovation.personApi.service;

import one.digitalinovation.personApi.dto.MessageRequestDto;
import one.digitalinovation.personApi.dto.PersonDto;
import one.digitalinovation.personApi.dto.PhoneDto;
import one.digitalinovation.personApi.entity.Person;
import one.digitalinovation.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@Service //a anotation service diz que essa classe é um service e ele possui suporte para as transações
public class PersonService {

    private PersonRepository personRepo;

    @Autowired  //faz a injeção od PersonRepository é como se ele fizesse um construtor
    public PersonService(PersonRepository personRepo){
        this.personRepo = personRepo;
    }

    public MessageRequestDto createPerson(PersonDto pessoa){
        //a questão com esse metodo foi criar um metodo de converter o personDto em person
        //e pra isso foi feita uma mudança na classe PhoneDto que recebeu um metodo que retorna a lista do phonesdto
        //mapeia os objetos e os coloca numa lista de phones para serem usados
        //é trabalhoso mas funciona
        Person p = Person.builder().firstName(pessoa.getFirstName()).cpf(pessoa.getCpf())
                    .lastName(pessoa.getLastName()).birthDate(LocalDate.now()).
                        phones(PhoneDto.builder().build().PhoneDtToPhone(pessoa.getPhones()))
                .build();//this.personRepo.save(pessoa);
        this.personRepo.save(p);
        return MessageRequestDto.builder().message("Created Person... "+p.getId()).build();
    }
}