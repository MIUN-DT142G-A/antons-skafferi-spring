package se.antons_skafferi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.antons_skafferi.dataClass.Tables;

import java.util.List;
import java.util.Optional;

/**
 * <h1>TablesRepository</h1>
 * This interface is used to communicate with the database regarding tables.
 */
public interface TablesRepository extends CrudRepository<Tables, Integer> {
    /**
     * This method is used to find all {@code Tables}.
     * @return {@code List<Tables>} A list of all tables.
     */
    List<Tables> findAll();

    /**
     * This method is used to find a table by id.
     * @param id The id of the table.
     * @return {@code Optional<Tables>} Returns the table.
     */
    @Query("SELECT t FROM Tables t WHERE t.table_number = :id")
    Optional<Tables> findById(int id);

    /**
     * This method is used to find all tables with a specific {@code room_for_people}.
     * @param room_for_people The room_for_people of the tables.
     * @return {@code List<Tables>} A list of all tables with the specified room_for_people.
     */
    @Query("SELECT t FROM Tables t WHERE t.room_for_people = :room_for_people")
    List<Tables> findByRoom_for_people(int room_for_people);
}