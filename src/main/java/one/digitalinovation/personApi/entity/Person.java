package one.digitalinovation.personApi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data               //automaticamente insere getter e setter
@Builder            //auxilia na contrução de objetos para o constructor
@AllArgsConstructor //gera um construtor com todos os argumentos
@NoArgsConstructor  //
@Getter     //abstrai getters e setters usando o @data o @getter e setter ficam opcionais
@Setter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstname",length = 30,nullable = false)
    private String firstName;

    @Column(name="lastname",length = 50,nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String cpf;

    @Column(name="birthdate")
    private LocalDate birthDate;

    //usamos onetomany por que pessoa tem mais de um telefone
    //porem não conhece os dados do telefone e sim de uma tabela intermediaria(gerado pelo banco)
    //essa tabela tem os atributos do phone, usaremos cascade pra que ao sarvarmos a passoa ela seja amarrada
    //ao phone e quando excluirmos ele sera tambem excluido
    @Column
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Phone> phones;

}
