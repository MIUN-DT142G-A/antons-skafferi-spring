package se.antons_skafferi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.antons_skafferi.dataClass.Events;

import java.sql.Date;
import java.util.List;

/**
 * <h1>EventsRepository</h1>
 * This interface is used to communicate with the database regarding events.
 */
public interface EventsRepository extends CrudRepository<Events, Integer> {
    /**
     * This method is used to find all events.
     * @return {@code List<Events>} A list of all events.
     */
    List<Events> findAll();

    /**
     * This method is used to find all events with a specific {@code week} and {@code year}.
     * @param week The week of the events.
     * @param year The year of the events.
     * @return {@code List<Events>} A list of all events with the specified period.
     */
    @Query("SELECT e FROM Events e WHERE WEEK(e.date) = :week AND YEAR(e.date) = :year")
    List<Events> findByWeekAndYear(int week, int year);

    /**
     * This method is used to find all {@code events} with a specific {@code date}.
     * @param date The date of the events.
     * @return {@code List<Events>} A list of all events with the specified date.
     */
    List<Events> findByDate(Date date);
}