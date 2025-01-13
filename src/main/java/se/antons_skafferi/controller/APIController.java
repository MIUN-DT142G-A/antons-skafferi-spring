package se.antons_skafferi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import se.antons_skafferi.dataClass.*;
import se.antons_skafferi.service.DatabaseService;
import se.antons_skafferi.repository.PersonRepository;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;
import java.sql.Timestamp;

/**
 * <h1>APIController</h1>
 * <p>
 * This class is the controller for the API. It handles all the requests and responses for the API.
 * It is responsible for the communication between the frontend and the backend.
 * </p>
 * <p>
 * The {@code APIController} class provides endpoints for managing {@code Bookings}, {@code Dinner}, {@code DrinkOrder}, {@code Drinks},
 * {@code Employee}, {@code Events}, {@code FoodOrder}, {@code Items}, {@code Lunch}, {@code LunchItems}, {@code Orders}, {@code Person}, {@code Role}, {@code Shift}, {@code Tab},
 * {@code Tables} and {@code WorksShift}.
 * </p>
 * <p>
 * Each endpoint is mapped to a specific URL path and HTTP method.
 * </p>
 * <p>
 * The class uses the {@link DatabaseService} to perform CRUD operations on the database.
 * </p>
 * @see DatabaseService
 *
 * @version 1.0
 * @since 2025-01-12
 */
@RestController
@RequestMapping(path="/api")
public class APIController {

    Logger logger = Logger.getLogger(APIController.class.getName());

    // The DatabaseService is used to perform CRUD operations on the database
    @Autowired
    private DatabaseService databaseService;


    // ##############################################################################################################
    // ##################### PERSONS ################################################################################
    // ##############################################################################################################

    // GET ----------------------------------------------------------------------------------------------------------
    /**
     * This method returns all {@code Person}s in the database.
     * @return {@code List<Person>} – A list of all persons in the database.
     */
    @GetMapping(path="/persons/all")
    public List<Person> getAllPersons() {
        return databaseService.getAllPersons();
    }

    /**
     * This method returns a {@code Person} by their {@code ID}.
     * @param email The Email of the person.
     * @return {@code Person} – The person with the specified ID.
     */
    @GetMapping(path="/persons/email/{email}")
    public Person getPersonByEmail(@PathVariable String email) {
        return databaseService.getPersonByEmail(email);
    }

    /**
     * This method returns a {@code Person} by their {@code phone_number}.
     * @param phoneNumber The phone number of the person.
     * @return {@code Person} – The person with the specified phone number.
     */
    @GetMapping(path="/persons/phone/{phoneNumber}")
    public Person getPersonByPhoneNumber(@PathVariable String phoneNumber) {
        return databaseService.getPersonByPhoneNumber(phoneNumber);
    }

    // POST ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new {@code Person} in the database.
     * @param person The person to be created.
     * @return {@code Person} – The person that was created.
     */
    @PostMapping(path="/persons")
    public Person createPerson(@RequestBody Person person) {
        return databaseService.addPerson(person);
    }

    // DELETE -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes a {@code Person} from the database.
     * @param personId The ID of the person to be deleted.
     */
    @DeleteMapping(path="/persons/{personId}/delete")
    public void deletePerson(@PathVariable int personId) {
        databaseService.deletePerson(personId);
    }


    // ##############################################################################################################
    // ##################### ITEMS ##################################################################################
    // ##############################################################################################################

    // GET ----------------------------------------------------------------------------------------------------------
    /**
     * This method returns all items in the database.
     * @return {@code List<Items>} – A list of all items in the database.
     */
    @GetMapping(path="/items/all")
    public List<Items> getAllItems() {
        return databaseService.getAllItems();
    }

    // POST ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new item in the database.
     * @param item The item to be created.
     * @return {@code Items} The item that was created.
     */
    @PostMapping(path="/items")
    public Items addItem(@RequestBody Items item) {
        return databaseService.addItem(item);
    }

    // DELETE -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes an {@code item} from the database.
     * @param itemId The ID of the item to be deleted.
     */
    @DeleteMapping(path="/items/{itemId}/delete")
    public void deleteItem(@PathVariable int itemId) {
        databaseService.deleteItem(itemId);
    }


    // ##############################################################################################################
    // ##################### LUNCH ##################################################################################
    // ##############################################################################################################

    // GET ----------------------------------------------------------------------------------------------------------
    /**
     * This method returns all {@code lunch} {@code items} in the database.
     * @return {@code List<Lunch>} – A list of all lunch items in the database.
     */
    @GetMapping(path="/menu/lunch/all")
    public List<Lunch> getAllLunchItems() {
        return databaseService.getAllLunchItems();
    }

    /**
     * This method returns all {@code Lunch} {@code Items} by specific {@code Date}.
     * @param date The date of the lunch items.
     * @return {@code List<Lunch>} – A list of all lunch items with the specified date.
     */
    @GetMapping(path="/menu/lunch/date/{date}")
    public List<Lunch> getLunchItemsByDate(@PathVariable Date date) {
        return databaseService.getLunchItemsByDate(date);
    }

    /**
     * This method returns all {@code Lunch} {@code Items} by specific {@code Week} and {@code Year}.
     * @param week The week of the lunch items.
     * @param year The year of the lunch items.
     * @return {@code List<Lunch>} – A list of all lunch items with the specified week and year.
     */
    @GetMapping(path="/menu/lunch/week/{week}/year/{year}")
    public List<Lunch> getLunchItemsByWeekAndYear(@PathVariable int week, @PathVariable int year) {
        return databaseService.getLunchItemsByWeekAndYear(week, year);
    }

    // POST ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new {@code Lunch} in the database.
     * @param lunch The lunch item to be created.
     * @return {@code Lunch} – The lunch item that was created.
     */
    @PostMapping(path="/menu/lunch")
    public Lunch addLunchItem(@RequestBody Lunch lunch) {
        return databaseService.addLunchItem(lunch);
    }

    /**
     * This method adds {@code Items} to a {@code Lunch} object.
     * @param lunchId The ID of the lunch item.
     * @param itemIds The IDs of the items to be added.
     * @return {@code Lunch} – The lunch item with the added items.
     */
    @PostMapping(path="/menu/lunch/{lunchId}/items")
    public Lunch addItemsToLunch(@PathVariable Integer lunchId, @RequestBody List<Integer> itemIds) {
        return databaseService.addItemsToLunch(lunchId, itemIds);
    }

    // DELETE -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes a {@code Lunch} from the database.
     * @param lunchId The ID of the lunch item to be deleted.
     */
    @DeleteMapping(path="/menu/lunch/{lunchId}/delete")
    public void deleteLunchItem(@PathVariable int lunchId) {
        databaseService.deleteLunchItem(lunchId);
    }


    // ##############################################################################################################
    // ##################### DINNER #################################################################################
    // ##############################################################################################################

    // GET ----------------------------------------------------------------------------------------------------------
    /**
     * This method returns all {@code Dinner} items in the database.
     * @return {@code List<Dinner>} – A list of all dinner items in the database.
     */
    @GetMapping(path="/menu/dinner/all")
    public List<Dinner> getAllDinnerItems() {
        return databaseService.getAllDinnerItems();
    }

    /**
     * This method returns all {@code Dinner} items by specific {@code Type}.
     * @param type The type of the dinner items.
     * @return {@code List<Dinner>} – A list of all dinner items with the specified type.
     */
    @GetMapping(path="/menu/dinner/type/{type}")
    public List<Dinner> getDinnerItemsByType(@PathVariable String type) {
        switch (type.toLowerCase()) {
            case "forratt":
                type = "Förrätt";
                break;
            case "varmratt":
                type = "Varmrätt";  // I KNOW THIS IS CURSED BUT WE ARE IN TIME CRUNCH AND ITS IMPLEMETED LIKE THIS
                break;              // IN THE FRONTEND ALREADY
            case "vegetarisk":
                type = "Vegetarisk";
                break;
            case "efterratt":
                type = "Efterrätt";
                break;
            default:
                throw new IllegalArgumentException("Invalid dinner type: " + type);
        }
        return databaseService.getDinnerItemsByType(type);
    }

    // POST ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new {@code Dinner} in the database.
     * @param dinner The dinner item to be created.
     * @return {@code Dinner} – The dinner item that was created.
     */
    @PostMapping(path="/menu/dinner")
    public Dinner addDinnerItem(@RequestBody Dinner dinner) {
        return databaseService.addDinnerItem(dinner);
    }

    // DELETE -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes a {@code Dinner} from the database.
     * @param dinnerId The ID of the dinner item to be deleted.
     */
    @DeleteMapping(path="/menu/dinner/{dinnerId}/delete")
    public void deleteDinnerItem(@PathVariable int dinnerId) {
        databaseService.deleteDinnerItem(dinnerId);
    }


    // ##############################################################################################################
    // ##################### BOOKINGS ###############################################################################
    // ##############################################################################################################

    // GET ----------------------------------------------------------------------------------------------------------
    /**
     * This method returns all {@code Bookings} in the database with a specific {@code type}.
     * @param type The type of booking.
     * @return {@code List<?>} – A list of all bookings in the database.
     */
    @GetMapping(path="/calendar/bookings/{type}")
    public List<?> getBookings(@PathVariable String type) {
        return switch (type.toLowerCase()) {
            case "all" -> databaseService.getBookings();
            default -> throw new IllegalArgumentException("Invalid booking type: " + type);
        };
    }

    /**
     * This method returns a {@code Booking} by specific {@code Date}.
     * @param date The date of the booking.
     * @return {@code List<Bookings>} – A list of all bookings with the specified date.
     */
    @GetMapping(path="/calendar/bookings/date/{date}")
    public List<Bookings> getBookingsByDate(@PathVariable Date date) {
        return databaseService.getBookingsByDate(date);
    }

    /**
     * This method returns a {@code Booking} by specific {@code Week} and {@code Year}.
     * @param week The week of the booking.
     * @param year The year of the booking.
     * @return {@code List<Bookings>} – A list of all bookings with the specified week and year.
     */
    @GetMapping(path="/calendar/bookings/week/{week}/year/{year}")
    public List<Bookings> getBookingsByWeekAndYear(@PathVariable int week, @PathVariable int year) {
        return databaseService.getBookingsByWeekAndYear(week, year);
    }

    /**
     * This method returns a {@code Booking} by specific {@code Table ID} and {@code Date}.
     * @param tableId The ID of the table.
     * @param dateTime The date of the booking.
     * @return {@code List<Bookings>} – A list of all bookings with the specified table ID and date.
     */
    @GetMapping(path="/bookings/confirmed/table/{tableId}/datetime/{dateTime}")
    public ResponseEntity<?> getConfirmedBookingsByTableIdAndDateTime(@PathVariable Integer tableId, @PathVariable String dateTime) {
        try {
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            List<Bookings> bookings = databaseService.getConfirmedBookingsByTableIdAndDateTime(tableId, timestamp);
            return ResponseEntity.ok(bookings);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * This method returns a {@code Booking} by specific {@code Person ID} and {@code Date}.
     * @param personId The ID of the person.
     * @param date The date of the booking.
     * @return {@code Bookings} – The booking with the specified person ID and date.
     */
    @GetMapping(path="/bookings/confirmed/{personId}/{date}")
    public ResponseEntity<?> getConfirmedBookingByPersonIdAndDate(@PathVariable Integer personId, @PathVariable String date) {
        try {
            Timestamp timestamp = Timestamp.valueOf(date + " 00:00:00");
            Bookings booking = databaseService.getConfirmedBookingByPersonIdAndDate(personId, timestamp);
            return ResponseEntity.ok(booking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // POST ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new {@code Booking} in the database.
     * @param booking The booking parameters to be used to create the object.
     * @return {@code bookings} – The booking that was created.
     */
    @PostMapping(path="/calendar/bookings")
    public Bookings addBooking(@RequestBody Bookings booking) {
        return databaseService.addBooking(booking);
    }

    /**
     * This method updates the status of a {@code Booking}.
     * @param id The ID of the booking.
     * @param status The status of the booking.
     * @return {@code Bookings} – The booking with the updated status.
     */
    @PostMapping(path="/calendar/bookings/{id}/status")
    public Bookings updateBookingStatus(@PathVariable Integer id, @RequestBody Bookings.Status status) {
        return databaseService.updateBookingStatus(id, status);
    }

    // DELETE -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes a {@code Booking} from the database.
     * @param bookingId The ID of the booking to be deleted.
     */
    @DeleteMapping(path="/calendar/bookings/{bookingId}/delete")
    public void deleteBooking(@PathVariable int bookingId) {
        databaseService.deleteBooking(bookingId);
    }


    // ##############################################################################################################
    // ##################### EVENTS #################################################################################
    // ##############################################################################################################

    // GET ----------------------------------------------------------------------------------------------------------
    /**
     * This method returns all {@code Events} in the database.
     * @return {@code List<Events>} – A list of all events in the database.
     */
    @GetMapping(path="/events/all")
    public List<Events> getAllEvents() {
        return databaseService.getAllEvents();
    }

    /**
     * This method returns an {@code Event} by specified {@code Week} and {@code Year}.
     * @param week The week of the event.
     * @param year The year of the event.
     * @return {@code List<Events>} – The events with the specified ID.
     */
    @GetMapping("/events/week/{week}/year/{year}")
    public List<Events> getEventsByWeekAndYear(@PathVariable int week, @PathVariable int year) {
        return databaseService.getEventsByWeekAndYear(week, year);
    }

    /**
     * This method returns an {@code Event} by specified {@code Date}.
     * @param date The date of the event.
     * @return {@code List<Events>} – The events with the specified date.
     */
    @GetMapping("/events/date/{date}")
    public List<Events> getEventsByDate(@PathVariable Date date) {
        return databaseService.getEventsByDate(date);
    }

    // POST ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new {@code Event} in the database.
     * @param event The event parameters to use to create the object.
     * @return {@code Events} – The event that was created.
     */
    @PostMapping(path="/events")
    public Events addEvent(@RequestBody Events event) {
        return databaseService.addEvent(event);
    }

    // DELETE -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes an {@code Event} from the database.
     * @param eventId The ID of the event to be deleted.
     */
    @DeleteMapping(path="/events/{eventId}/delete")
    public void deleteEvent(@PathVariable int eventId) {
        databaseService.deleteEvent(eventId);
    }


    // ##############################################################################################################
    // ##################### ORDERS #################################################################################
    // ##############################################################################################################

    // GET  ---------------------------------------------------------------------------------------------------------
    /**
     * This method returns all {@code Orders} in the database.
     * @return {@code List<Orders>} – A list of all orders in the database.
     */
    @GetMapping(path="/orders/all")
    public List<Orders> getAllOrders() {
        return databaseService.getAllOrders();
    }

    /**
     * This method returns an {@code Orders} by their {@code ID}.
     * @param id The ID of the order.
     * @return {@code Orders} – The order with the specified ID.
     */
    @GetMapping(path="/orders/id/{id}")
    public Orders getOrdersById(@PathVariable Integer id) {
        return databaseService.getOrdersById(id);
    }

    /**
     * This method returns an {@code Orders} by specific {@code Date}.
     * @param date The date of the order.
     * @return {@code List<Orders>} – A list of all orders with the specified date.
     */
    @GetMapping(path="/orders/date/{date}")
    public List<Orders> getOrdersByDate(@PathVariable Date date) {
        return databaseService.getOrdersByDate(date);
    }

    /**
     * This method returns an {@code Orders} by specific {@code Status}.
     * @param status The status of the order.
     * @return {@code List<Orders>} – A list of all {@code orders} with the specified {@code status}.
     */
    @GetMapping(path="/orders/status/{status}")
    public List<Orders> getOrdersByStatus(@PathVariable Orders.Status status) {
        return databaseService.getOrdersByStatus(status);
    }

    // POST ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new {@code Orders} in the database.
     * @param order The order parameters to create the object.
     * @return {@code Orders} – The {@code order} that was created.
     */
    @PostMapping(path="/orders")
    public Orders createOrder(@RequestBody Orders order) {
        return databaseService.addOrder(order);
    }

    /**
     * This method adds a {@code Dinner} item to an {@code Order}.
     * @param orderId The ID of the order.
     * @param dinnerId The ID of the {@code dinner} item.
     * @return {@code Orders} – The  {@code order} with the added menu item.
     */
    @PostMapping(path="/orders/{orderId}/menu-item")
    public Orders addMenuItemToOrder(@PathVariable int orderId, @RequestBody Integer dinnerId) {
        return databaseService.addMenuItemToOrder(orderId, dinnerId);
    }

    /**
     * This method adds a {@code Drink} to an {@code Order}.
     * @param orderId The ID of the order.
     * @param drinkId The ID of the drink.
     * @return {@code Orders} – The {@code order} with the added {@code drink}.
     */
    @PostMapping(path="/orders/{orderId}/drink")
    public Orders addDrinkToOrder(@PathVariable int orderId, @RequestBody Integer drinkId) {
        return databaseService.addDrinkToOrder(orderId, drinkId);
    }

    /**
     * This method updates the status of an {@code Order}.
     * @param orderId The ID of the order.
     * @param status The status of the order that it should be updated to.
     * @return {@code Orders} – The {@code order} with the updated status.
     */
    @PostMapping(path="/orders/{orderId}/status")
    public Orders addStatusToOrder(@PathVariable int orderId, @RequestBody Orders.Status status) {
        return databaseService.updateOrderStatus(orderId, status);
    }

    /**
     * This method updates the note of an {@code Order}.
     * @param orderId The ID of the order.
     * @param note The note that should be added to the order.
     * @return {@code Orders} – The {@code order} with the updated note.
     */
    @PostMapping(path="/orders/{orderId}/note")
    public Orders updateOrderNote(@PathVariable int orderId, @RequestBody String note) {
        return databaseService.updateOrderNote(orderId, note);
    }

    // DELETE -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes an {@code Order} from the database.
     * @param orderId The ID of the order to be deleted.
     */
    @DeleteMapping(path="/orders/{orderId}/delete")
    public void deleteOrder(@PathVariable int orderId) {
        databaseService.deleteOrder(orderId);
    }

    /**
     * This method deletes a {@code Dinner} from an {@code Order}.
     * @param foodOrderId The ID of the order.
     */
    @DeleteMapping(path="/orders/{orderId}/menu-item/{foodOrderId}/delete")
    public void deleteMenuItemFromOrder(@PathVariable int orderId, @PathVariable int foodOrderId) {
        databaseService.deleteMenuItemFromOrder(orderId, foodOrderId);
    }

    /**
     * This method deletes a {@code Drink} from an {@code Order}.
     * @param drinkId The ID of the order.
     */
    @DeleteMapping(path="/orders/{orderId}/drink/{drinkId}/delete")
    public Orders deleteDrinkFromOrder(@PathVariable int orderId, @PathVariable int drinkId) {
        databaseService.deleteDrinkFromOrder(orderId, drinkId);
        return databaseService.getOrdersById(orderId);
    }


    // ##############################################################################################################
    // ##################### TABS ###################################################################################
    // ##############################################################################################################

    // GET ----------------------------------------------------------------------------------------------------------
    /**
     * This method returns all {@code Tab}s in the database.
     * @return {@code List<Tab>} – A list of all tabs in the database.
     */
    @GetMapping(path="/tab/all")
    public List<Tab> getAllTabs() {
        return databaseService.getAllTabs();
    }

    /**
     * This method returns a {@code Tab} by their {@code ID}.
     * @param id The ID of the tab.
     * @return {@code Tab} – The tab with the specified ID.
     */
    @GetMapping(path="/tab/id/{id}")
    public Tab getTabById(@PathVariable Integer id) {
        return databaseService.getTabById(id);
    }

    /**
     * This method returns {@code Tab}s by specific {@code Date}.
     * @param date The date of the tab.
     * @return {@code List<Tab>} – A list of all tabs with the specified date.
     */
    @GetMapping(path="/tab/date/{date}")
    public List<Tab> getTabByDate(@PathVariable String date) {
        Timestamp timestamp = Timestamp.valueOf(date + " 00:00:00");
        return databaseService.getTabByDate(timestamp);
    }

    /**
     * This method returns {@code Tab}s by specific {@code Status}.
     * @param status The status of the tab.
     * @return {@code List<Tab>} – A list of all tabs with the specified status.
     */
    @GetMapping(path="/tab/status/{status}")
    public List<Tab> getTabByStatus(@PathVariable Tab.Status status) {
        return databaseService.getTabByStatus(status);
    }

    /**
     * This method returns {@code Tab}s by specific {@code Table}.
     * @param table The table of the tab.
     * @return {@code List<Tab>} – A list of all tabs with the specified table.
     */
    @GetMapping(path="/tab/table/{table}")
    public List<Tab> getTabByTable(@PathVariable Integer table) {
        return databaseService.getTabByTable(table);
    }

    // POST ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new {@code Tab} in the database.
     * @param tab The tab parameters to create the object.
     * @return {@code Tab} – The tab that was created.
     */
    @PostMapping(path="/tab")
    public Tab createTab(@RequestBody Tab tab) {
        return databaseService.addTab(tab);
    }

    /**
     * This method updates the status of a {@code Tab}.
     * @param tabId The ID of the tab.
     * @param status The status of the tab.
     * @return {@code Tab} – The tab with the updated status.
     */
    @PostMapping(path="/tab/{tabId}/status")
    public Tab updateTabStatus(@PathVariable int tabId, @RequestBody Tab.Status status) {
        return databaseService.updateTabStatus(tabId, status);
    }

    // DELETE -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes a {@code Tab} from the database.
     * @param tabId The ID of the tab to be deleted.
     */
    @DeleteMapping(path="/tab/{tabId}/delete")
    public void deleteTab(@PathVariable int tabId) {
        databaseService.deleteTab(tabId);
    }


    // ##############################################################################################################
    // ##################### DRINKS #################################################################################
    // ##############################################################################################################

    // GET ----------------------------------------------------------------------------------------------------------
    /**
     * This method returns all {@code Drinks} in the database.
     * @return {@code List<Drinks>} – A list of all drinks in the database.
     */
    @GetMapping(path="/menu/drinks/all")
    public List<Drinks> getAllDrinks() {
        return databaseService.getAllDrinks();
    }

    /**
     * This method returns a {@code Drinks} by their {@code ID}.
     * @param id The ID of the drink.
     * @return {@code Drinks} – The drink with the specified ID.
     */
    @GetMapping(path="/menu/drinks/id/{id}")
    public Drinks getDrinkById(@PathVariable int id) {
        return databaseService.getDrinkById(id);
    }

    /**
     * This method returns a {@code Drinks} by specific {@code Type}.
     * @param type The type of the drink.
     * @return {@code List<Drinks>} – A list of all drinks with the specified type.
     */
    @GetMapping(path="/menu/drinks/type/{type}")
    public List<Drinks> getDrinksByType(@PathVariable String type) {
        return databaseService.getDrinksByType(type);
    }
    // POST ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new {@code Drinks} in the database.
     * @param drink The drink parameters to create the object.
     * @return {@code Drinks} – The drink that was created.
     */
    @PostMapping(path="/menu/drinks")
    public Drinks addDrink(@RequestBody Drinks drink) {
        return databaseService.addDrink(drink);
    }

    /**
     * This method updates the price of a {@code Drink}.
     * @param drinkId The ID of the drink.
     * @param price The price of the drink.
     * @return {@code Drinks} – The drink with the updated price.
     */
    @PostMapping(path="/menu/drinks/{drinkId}/price")
    public Drinks updateDrinkPrice(@PathVariable int drinkId, @RequestBody Integer price) {
        return databaseService.updateDrinkPrice(drinkId, price);
    }

    // DELETE -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes a {@code Drink} from the database.
     * @param drinkId The ID of the drink to be deleted.
     */
    @DeleteMapping(path="/menu/drinks/{drinkId}/delete")
    public void deleteDrink(@PathVariable int drinkId) {
        databaseService.deleteDrink(drinkId);
    }


    // ##############################################################################################################
    // ##################### TABLES #################################################################################
    // ##############################################################################################################

    // GET ----------------------------------------------------------------------------------------------------------
    /**
     * This method returns all {@code Tables} in the database.
     * @return {@code List<Tables>} – A list of all tables in the database.
     */
    @GetMapping(path="/tables/all")
    public List<Tables> getAllTables() {
        return databaseService.getAllTables();
    }

    /**
     * This method returns a {@code Tables} by their {@code ID}.
     * @param id The ID of the table.
     * @return {@code Tables} – The table with the specified ID.
     */
    @GetMapping(path="/tables/id/{id}")
    public Tables getTableById(@PathVariable int id) {
        return databaseService.getTableById(id);
    }

    /**
     * This method returns {@code Tables} by specific seating capacity {@code room_for_people}.
     * @param room_for_people The number of seats at the table.
     * @return {@code Tables} – The table with the specified capacity.
     */
    @GetMapping(path="/tables/seats/{room_for_people}")
    public List<Tables> getTablesByNumberOfSeats(@PathVariable int room_for_people) {
        return databaseService.getTablesByNumberOfSeats(room_for_people);
    }

    // Post ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new {@code Tables} in the database.
     * @param table The table parameters to create the object.
     * @return {@code Tables} – The table that was created.
     */
    @PostMapping(path="/tables")
    public Tables addTable(@RequestBody Tables table) {
        return databaseService.addTable(table);
    }

    // Delete -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes a {@code Table} from the database.
     * @param tableId The ID of the table to be deleted.
     */
    @DeleteMapping(path="/tables/{tableId}/delete")
    public void deleteTable(@PathVariable int tableId) {
        databaseService.deleteTable(tableId);
    }


    // ##############################################################################################################
    // ##################### EMPLOYEES ##############################################################################
    // ##############################################################################################################

    // GET ----------------------------------------------------------------------------------------------------------
    /**
     * This method returns all {@code Employee}s in the database.
     * @return {@code List<Employee>} – A list of all employees in the database.
     */
    @CrossOrigin(origins = "http://localhost:63343")
    @GetMapping(path="/employees/all")
    public List<Employee> getAllEmployees() {
        return databaseService.getAllEmployees();
    }

    /**
     * This method returns an {@code Employee} by their {@code ID}.
     * @param id The ID of the employee.
     * @return {@code Employee} – The employee with the specified ID.
     */
    @GetMapping(path="/employees/id/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return databaseService.getEmployeeById(id);
    }

    /**
     * This method returns {@code Employee}s by their {@code name}.
     * @param name The name of the employees.
     * @return {@code List<Employee>} – The employees with the specified name.
     */
    @GetMapping(path="/employees/name/{name}")
    public List<Employee> getEmployeesByName(@PathVariable String name) {
        return databaseService.getEmployeesByName(name);
    }

    /**
     * This method returns an {@code Employee} by their {@code email}.
     * @param email The {@code email} of the {@code employee}.
     * @return {@code Employee} – The employee with the specified {@code email}.
     */
    @GetMapping(path="/employees/email/{email}/password")
    public String getEmployeePasswordByEmail(@PathVariable String email) {
        return databaseService.getEmployeePasswordByEmail(email);
    }

    /**
     * This method returns an {@code Employee}s by their {@code id}.
     * @param id The {@code id} of the {@code employee}.
     * @return {@code List<Role>} – The hierarchy of the employee with the specified {@code id}.
     */
    @GetMapping(path="/employees/{id}/hierarchy")
    public List<Role> getHierarchyByEmployeeId(@PathVariable int id) {
        return databaseService.getHierarchyByEmployeeId(id);
    }

    // Post ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new {@code Employee} in the database.
     * @param employee The employee parameters to create the object.
     * @return {@code Employee} – The employee that was created.
     */
    @PostMapping(path="/employees")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            Employee createdEmployee = databaseService.addEmployee(employee);
            return ResponseEntity.ok(createdEmployee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * This method changes the password of an {@code Employee}.
     * @param employeeId The ID of the employee.
     * @param password The new password of the employee.
     * @return {@code Employee} – The employee with the updated password.
     */
    @PostMapping(path="/employees/{employeeId}/password")
    public Employee changeEmployeePassword(@PathVariable int employeeId, @RequestBody String password) {
        return databaseService.updateEmployeePassword(employeeId, password);
    }

    /**
     * This method changes the role of an {@code Employee}.
     * @param employeeId The ID of the employee.
     * @param roleId The ID of the role.
     * @return {@code Employee} – The employee with the updated role.
     */
    @PostMapping(path="/employees/{employeeId}/role/{roleId}")
    public Employee changeEmployeeRole(@PathVariable int employeeId, @PathVariable int roleId) {
        return databaseService.changeEmployeeRole(employeeId, roleId);
    }

    // Delete -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes an {@code Employee} from the database.
     * @param employeeId The ID of the employee to be deleted.
     */
    @DeleteMapping(path="/employees/{employeeId}/delete")
    public void deleteEmployee(@PathVariable int employeeId) {
        databaseService.deleteEmployee(employeeId);
    }


    // ##############################################################################################################
    // ##################### ROLES ##################################################################################
    // ##############################################################################################################

    // GET ----------------------------------------------------------------------------------------------------------
    /**
     * This method returns all {@code Role}s in the database.
     * @return {@code List<Role>} – A list of all roles in the database.
     */
    @GetMapping(path="/roles/all")
    public List<Role> getAllRoles() {
        return databaseService.getAllRoles();
    }

    /**
     * This method returns a {@code Role} by their {@code ID}.
     * @param id The ID of the role.
     * @return {@code Role} – The role with the specified ID.
     */
    @GetMapping(path="/roles/id/{id}")
    public Role getRoleById(@PathVariable int id) {
        return databaseService.getRoleById(id);
    }

    // Post ---------------------------------------------------------------------------------------------------------
    /**
     * This method creates a new {@code Role} in the database.
     * @param role The role parameters to create the object.
     * @return {@code Role} – The role that was created.
     */
    @PostMapping(path="/roles")
    public Role createRole(@RequestBody Role role) {
        return databaseService.addRole(role);
    }

    /**
     * This method changes the hierarchy of a {@code Role}.
     * @param roleId The ID of the role.
     * @param hierarchyLevel The new hierarchy level of the role.
     * @return {@code Role} – The role with the updated hierarchy.
     */
    @PostMapping(path="/roles/{roleId}/hierarchy")
    public Role changeRoleHierarchy(@PathVariable int roleId, @RequestBody Integer hierarchyLevel) {
        return databaseService.updateRoleHierarchy(roleId, hierarchyLevel);
    }

    // Delete -------------------------------------------------------------------------------------------------------
    /**
     * This method deletes a {@code Role} from the database.
     * @param roleId The ID of the role to be deleted.
     */
    @DeleteMapping(path="/roles/{roleId}/delete")
    public void deleteRole(@PathVariable int roleId) {
        databaseService.deleteRole(roleId);
    }
}