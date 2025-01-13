package se.antons_skafferi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.antons_skafferi.dataClass.DrinkOrder;
import se.antons_skafferi.dataClass.Orders;
import se.antons_skafferi.dataClass.Drinks;

import java.util.Optional;

/**
 * <h1>DrinkOrderRepository</h1>
 * This interface is used to communicate with the database regarding drink orders.
 */
public interface DrinkOrderRepository extends JpaRepository<DrinkOrder, Integer> {
    /**
     * This method is used to find a drink order by order and drink.
     * @param order The order to find.
     * @param drink The drink to find.
     * @return The drink order.
     */
    Optional<DrinkOrder> findByOrderAndDrink(Orders order, Drinks drink);
}