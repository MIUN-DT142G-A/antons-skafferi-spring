package se.antons_skafferi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.antons_skafferi.dataClass.Bookings;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Bookings, Integer> {
    List<Bookings> findAll();

    List<Bookings> findByDate(Date date);

    @Query("SELECT b FROM Bookings b WHERE WEEK(b.date) = :week AND YEAR(b.date) = :year")
    List<Bookings> findByWeekAndYear(int week, int year);

    @Query("SELECT b FROM Bookings b WHERE b.person_id = :personId AND b.date = :date AND b.status = 'Confirmed'")
    Optional<Bookings> findConfirmedBookingByPersonIdAndDate(@Param("personId") Integer personId, @Param("date") Date date);

    @Query("SELECT b FROM Bookings b WHERE b.table_number = :tableId AND b.date = :dateTime AND b.status = 'CONFIRMED'")
    List<Bookings> findConfirmedBookingsByTableIdAndDateTime(@Param("tableId") Integer tableId, @Param("dateTime") Timestamp dateTime);
}