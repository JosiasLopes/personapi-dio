package one.digitalinovation.personApi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {

    HOME("Home"),
    Mobile("Mobile"),
    Comercial("Comercial");

    private final String description;

  //  PhoneType(String description) {
        //this.description = description;
   // }
}
