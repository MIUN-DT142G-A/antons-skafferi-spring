package se.antons_skafferi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.antons_skafferi.dataClass.Tab;

import java.sql.Timestamp;
import java.util.List;

/**
 * <h1>TabRepository</h1>
 * This interface is used to communicate with the database regarding tabs.
 */
public interface TabRepository extends JpaRepository<Tab, Integer> {
    /**
     * This method is used to find all {@code Tab}.
     * @return {@code List<Tab>} A list of all tabs.
     */
    @Query("SELECT t FROM Tab t WHERE DATE(t.opened_at) = DATE(:opened_at)")
    List<Tab> findByOpened_at(Timestamp opened_at);

    /**
     * This method is used to find all {@code Tab} with a specific {@code status}.
     * @param status The status of the tabs.
     * @return {@code List<Tab>} A list of all tabs with the specified status.
     */
    List<Tab> findByStatus(Tab.Status status);

    /**
     * This method is used to find all {@code Tab} with a specific {@code table_number}.
     * @param table_number The table_number of the tabs.
     * @return {@code List<Tab>} A list of all tabs with the specified table_number.
     */
    @Query("SELECT t FROM Tab t WHERE t.table_number = :table_number")
    List<Tab> findByTable_number(Integer table_number);
}