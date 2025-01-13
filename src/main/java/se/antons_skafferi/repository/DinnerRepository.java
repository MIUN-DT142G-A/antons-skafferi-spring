package se.antons_skafferi.repository;

import org.springframework.data.repository.CrudRepository;
import se.antons_skafferi.dataClass.Dinner;

import java.util.List;

/**
 * <h1>DinnerRepository</h1>
 * This interface is used to communicate with the database regarding dinner.
 */
public interface DinnerRepository extends CrudRepository<Dinner, Integer> {
    /**
     * This method is used to find all {@code Dinner}.
     * @return {@code List<Dinner>} List of all dinner.
     */
    List<Dinner> findAll();

    /**
     * This method is used to find all {@code Dinner} with a specific type.
     * @param type The type of dinner.
     * @return {@code List<Dinner>} List of all dinner with the specified type.
     */
    List<Dinner> findByType(String type);
}