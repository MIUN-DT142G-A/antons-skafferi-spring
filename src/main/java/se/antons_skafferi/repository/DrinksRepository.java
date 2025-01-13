package se.antons_skafferi.repository;

import org.springframework.data.repository.CrudRepository;
import se.antons_skafferi.dataClass.Drinks;

import java.util.List;
import java.util.Optional;

/**
 * <h1>DrinksRepository</h1>
 * This interface is used to communicate with the database regarding drinks.
 */
public interface DrinksRepository extends CrudRepository<Drinks, Integer> {
    /**
     * This method is used to find all drinks.
     * @return A list of all drinks.
     */
    List<Drinks> findAll();

    /**
     * This method is used to find a drink by id.
     * @param id The id of the drink.
     * @return The drink.
     */
    Optional<Drinks> findById(int id);

    /**
     * This method is used to find all drinks with a specific type.
     * @param type The type of drink.
     * @return A list of all drinks with the specified type.
     */
    List<Drinks> findByType(String type);
}