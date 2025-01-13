package se.antons_skafferi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.antons_skafferi.dataClass.Lunch;

import java.sql.Date;
import java.util.List;

/**
 * <h1>LunchRepository</h1>
 * This interface is used to communicate with the database regarding lunch.
 */
public interface LunchRepository extends CrudRepository<Lunch, Integer> {
    /**
     * This method is used to find all {@code Lunch}.
     * @return {@code List<Lunch>} List of all lunch.
     */
    @Query("SELECT l FROM Lunch l LEFT JOIN FETCH l.lunchItems")
    List<Lunch> findAllWithItems();

    /**
     * This method is used to find all {@code Lunch} with a specific {@code date}.
     * @param date The date of the lunch.
     * @return {@code List<Lunch>} List of all lunch with the specified date.
     */
    @Query("SELECT l FROM Lunch l LEFT JOIN FETCH l.lunchItems WHERE l.date = :date")
    List<Lunch> findByDate(Date date);

    /**
     * This method is used to find all {@code Lunch} with a specific {@code week} and {@code year}.
     * @param week The week of the lunch.
     * @param year The year of the lunch.
     * @return {@code List<Lunch>} List of all lunch with the specified period.
     */
    @Query("SELECT l FROM Lunch l LEFT JOIN FETCH l.lunchItems WHERE WEEK(l.date) = :week AND YEAR(l.date) = :year")
    List<Lunch> findByWeekAndYear(int week, int year);
}