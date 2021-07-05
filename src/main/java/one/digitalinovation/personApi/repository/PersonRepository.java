package one.digitalinovation.personApi.repository;

import one.digitalinovation.personApi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
