package se.antons_skafferi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.antons_skafferi.dataClass.Bookings;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * <h1>BookingRepository</h1>
 * This interface is used to communicate with the database regarding bookings.
 */
public interface BookingRepository extends CrudRepository<Bookings, Integer> {
    /**
     * This method is used to find all {@code bookings}.
     * @return {@code List<Bookings>} of all bookings.
     */
    List<Bookings> findAll();

    /**
     * This method is used to find a booking by its {@code Date}.
     * @param date The date of the booking.
     * @return {@code List<Bookings>} – The {@code bookings} with the specified date.
     */
    List<Bookings> findByDate(Date date);

    /**
     * This method is used to find a {@code booking} by its {@code week} and {@code year}.
     * @param week The week of the {@code booking}.
     * @param year The year of the {@code booking}.
     * @return {@code List<Bookings>} – The {@code bookings} with the specified status.
     */
    @Query("SELECT b FROM Bookings b WHERE WEEK(b.date) = :week AND YEAR(b.date) = :year")
    List<Bookings> findByWeekAndYear(int week, int year);

    /**
     * This method is used to find a {@code booking} by its {@code status}. It checks if it is flagged as 'Confirmed'.
     * @param personId The person id of the {@code booking}.
     * @param date The date of the {@code booking}.
     * @return {@code List<Bookings>} – The {@code bookings} with the specified status.
     */
    @Query("SELECT b FROM Bookings b WHERE b.person_id = :personId AND b.date = :date AND b.status = 'Confirmed'")
    Optional<Bookings> findConfirmedBookingByPersonIdAndDate(@Param("personId") Integer personId, @Param("date") Date date);

    /**
     * This method is used to find a {@code booking} by its {@code tableId} and {@code dateTime}. It checks if it is flagged as 'Confirmed'.
     * @param tableId The table id of the {@code booking}.
     * @param dateTime The date and time of the {@code booking}.
     * @return {@code List<Bookings>} – The {@code bookings} with the specified status.
     */
    @Query("SELECT b FROM Bookings b WHERE b.table_number = :tableId AND b.date = :dateTime AND b.status = 'CONFIRMED'")
    List<Bookings> findConfirmedBookingsByTableIdAndDateTime(@Param("tableId") Integer tableId, @Param("dateTime") Timestamp dateTime);
}