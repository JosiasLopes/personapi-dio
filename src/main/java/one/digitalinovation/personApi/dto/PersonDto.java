package one.digitalinovation.personApi.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data               //automaticamente insere getter e setter
@Builder            //auxilia na contrução de objetos para o constructor
@AllArgsConstructor //gera um construtor com todos os argumentos
@NoArgsConstructor  //

public class PersonDto {


    private Long id;

    @NotEmpty
    @Size(min=2,max=30)
    private String firstName;

    @NotEmpty
    @Size(min=4,max=50)
    private String lastName;

    @NotEmpty
    private String cpf;


    private String birthDate;

    @Valid //essa anotation faz a validação dos telefoes(os que obedecem as validaões colocadas no telefone
    @NotEmpty
    private List<PhoneDto> phones;

}
