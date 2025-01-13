package se.antons_skafferi.repository;

import org.springframework.data.repository.CrudRepository;
import se.antons_skafferi.dataClass.Items;

import java.util.List;

/**
 * <h1>ItemsRepository</h1>
 * This interface is used to communicate with the database regarding items.
 */
public interface ItemsRepository extends CrudRepository<Items, Integer> {
    /**
     * This method is used to find all items.
     * @return {@code List<Items>} A list of all items.
     */
    List<Items> findAll();
}