package one.digitalinovation.personApi.dto;

import lombok.*;
import one.digitalinovation.personApi.entity.Phone;
import one.digitalinovation.personApi.enums.PhoneType;
import javax.validation.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PhoneDto {


    private Long id;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @NotEmpty
    @Size(min=13,max=14)
    private String number;

    public List<Phone> PhoneDtToPhone(List<PhoneDto> listaDto){

        return listaDto.stream().map(s->{
            Phone p =new Phone();
            p.setNumber(s.getNumber());
            p.setType(s.getType());
            return p;
        }).collect(Collectors.toList());
        
    }
}
