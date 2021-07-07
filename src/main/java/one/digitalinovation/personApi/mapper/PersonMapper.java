package one.digitalinovation.personApi.mapper;

import one.digitalinovation.personApi.dto.PersonDto;
import one.digitalinovation.personApi.dto.PhoneDto;
import one.digitalinovation.personApi.entity.Person;
import one.digitalinovation.personApi.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper()
public interface PersonMapper {

    //a classe que vai mapear
    //o toModel e toDTO são convenções a serem utilizadas
    //o mapping mapeia qual o atributo sera utilizado
    //o mapper necessita de uma constante com a classe e ela vai retornar a classe mapeada
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate",source= "birthDate",dateFormat="dd-MM-yyyy")
    Person toPerson(PersonDto personDto);

    PersonDto toPersonDTO(Person person);
}
