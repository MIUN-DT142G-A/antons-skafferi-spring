package se.antons_skafferi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.antons_skafferi.dataClass.FoodOrder;
import se.antons_skafferi.dataClass.Orders;
import se.antons_skafferi.dataClass.Dinner;

import java.util.Optional;

/**
 * <h1>FoodOrderRepository</h1>
 * This interface is used to communicate with the database regarding food orders.
 */
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer> {
    /**
     * This method is used to find a food order by order and dinner.
     * @param order The order to find.
     * @param dinner The dinner to find.
     * @return {@code Optional<FoodOrder>} Returns the food orders.
     */
    Optional<FoodOrder> findByOrderAndDinner(Orders order, Dinner dinner);
}