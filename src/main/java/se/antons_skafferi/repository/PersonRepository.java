package se.antons_skafferi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.antons_skafferi.dataClass.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findAll();

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Person p WHERE p.email = :email")
    boolean existsByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Person p WHERE p.phone_number = :phoneNumber")
    boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT p FROM Person p WHERE p.email = :email")
    Optional<Person> findByEmail(@Param("email") String email);

    @Query("SELECT p FROM Person p WHERE p.phone_number = :phoneNumber")
    Optional<Person> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}