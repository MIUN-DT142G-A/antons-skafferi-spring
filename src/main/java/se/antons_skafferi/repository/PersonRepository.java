package se.antons_skafferi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.antons_skafferi.dataClass.Person;

import java.util.List;
import java.util.Optional;

/**
 * <h1>PersonRepository</h1>
 * This interface is used to communicate with the database regarding persons.
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
    /**
     * This method is used to find all {@code persons}.
     * @return {@code List<Person>} A list of all persons.
     */
    List<Person> findAll();

    /**
     * This method is used to find a if a {@code person} exists with that email.
     * @param email The email of the person.
     * @return {@code boolean} True or false depending if a person exists with the specified email.
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Person p WHERE p.email = :email")
    boolean existsByEmail(String email);

    /**
     * This method is used to check if a {@code person} has that phone number already or not.
     * @param phoneNumber The phone number of the person.
     * @return {@code boolean} True or false depending if a person exists with the specified phone number.
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Person p WHERE p.phone_number = :phoneNumber")
    boolean existsByPhoneNumber(String phoneNumber);

    /**
     * This method is used to find a {@code person} by {@code email}.
     * @param email The email of the person.
     * @return {@code Optional<Person>} The person.
     */
    @Query("SELECT p FROM Person p WHERE p.email = :email")
    Optional<Person> findByEmail(@Param("email") String email);

    /**
     * This method is used to find a {@code person} by {@code phoneNumber}.
     * @param phoneNumber The phone number of the person.
     * @return {@code Optional<Person>} A list of persons.
     */
    @Query("SELECT p FROM Person p WHERE p.phone_number = :phoneNumber")
    Optional<Person> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}