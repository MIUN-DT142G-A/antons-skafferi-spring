package se.antons_skafferi.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.antons_skafferi.dataClass.Bookings;
import se.antons_skafferi.dataClass.Orders;

import java.sql.Date;
import java.util.List;

/**
 * <h1>OrderRepository</h1>
 * This interface is used to communicate with the database regarding orders.
 */
public interface OrderRepository extends CrudRepository<Orders, Integer> {
    /**
     * This method is used to find all {@code Orders}.
     * @return {@code List<Orders>} A list of all orders.
     */
    List<Orders> findAll();

    /**
     * This method is used to find all {@code Orders} with a specific {@code date}.
     * @param date The date of the orders.
     * @return {@code List<Orders>} A list of all orders with the specified date.
     */
    List<Orders> findByDate(Date date);

    /**
     * This method is used to find all orders with a specific {@code week} and {@code year}.
     * @param status The status of the orders.
     * @return {@code List<Orders>} A list of all orders with the specified status.
     */
    List<Orders> findByStatus(Orders.Status status);
}
