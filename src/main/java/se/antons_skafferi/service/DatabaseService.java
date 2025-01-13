package se.antons_skafferi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.antons_skafferi.dataClass.*;
import se.antons_skafferi.repository.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * <h1>DatabaseService</h1>
 * <p>
 * This class is used to communicate with the database by calling functions present in the repositories.
 * </p>
 * <p>
 * The class contains methods for getting, adding, updating and deleting data from the database and is the middleman between the repositories and the controller.
 * </p>
 * @see BookingRepository
 * @see DinnerRepository
 * @see DrinkOrderRepository
 * @see DrinksRepository
 * @see EmployeeRepository
 * @see EventsRepository
 * @see FoodOrderRepository
 * @see ItemsRepository
 * @see LunchRepository
 * @see OrderRepository
 * @see PersonRepository
 * @see RoleRepository
 * @see TablesRepository
 * @see TabRepository
 *
 * @version 1.0
 * @since 2025-01-12
 */
@Service
public class DatabaseService {

    @Autowired
    private LunchRepository lunchRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DinnerRepository dinnerRepository;
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private TabRepository tabRepository;
    @Autowired
    private DrinksRepository drinksRepository;
    @Autowired
    private TablesRepository tablesRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FoodOrderRepository foodOrderRepository;
    @Autowired
    private DrinkOrderRepository drinkOrderRepository;
    @Autowired
    private ItemsRepository itemsRepository;


    // ################################################################################################################
    // ################# PERSONS ######################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code persons}.
     * @return {@code List<Person>} List of all persons.
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Method to get a {@code person} by {@code email}.
     * @param email The email of the person.
     * @return {@code Person} The person. Or if the email is invalid, an exception is thrown.
     */
    public Person getPersonByEmail(String email) {
        return personRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
    }

    /**
     * Method to get a {@code person} by {@code phoneNumber}.
     * @param phoneNumber The phone number of the person.
     * @return {@code Person} The person. Or if the phone number is invalid, an exception is thrown.
     */
    public Person getPersonByPhoneNumber(String phoneNumber) {
        return personRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phone number"));
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add a {@code person}.
     * Checks both email and phone number to ensure they are unique and give the user a proper error message.
     * @param person The person to be added.
     * @return {@code Person} The person. Or if the email or phone number already exists, an exception is thrown.
     */
    public Person addPerson(Person person) {
        if (personRepository.existsByEmail(person.getEmail())) {
            throw new IllegalArgumentException("Invalid email: Email already exists");
        }
        if (personRepository.existsByPhoneNumber(person.getPhone_number())) {
            throw new IllegalArgumentException("Invalid phone number: Phone number already exists");
        }
        return personRepository.save(person);
    }

    //DELETE ----------------------------------------------------------------------------------------------------------
    /**
     * Method to delete a {@code person}. Has to check if the person exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param personId The ID of the person to be deleted.
     */
    public void deletePerson(int personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person ID"));
        personRepository.delete(person);
    }


    // ################################################################################################################
    // ################# ITEMS ########################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code items}.
     * @return {@code List<Items>} List of all items.
     */
    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add an {@code item}.
     * @param item The item to be added.
     * @return {@code Items} The item.
     */
    public Items addItem(Items item) {
        return itemsRepository.save(item);
    }

    // DELETE ---------------------------------------------------------------------------------------------------------
    /**
     * Method to delete an {@code item}. Has to check if the item exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param itemId The ID of the item to be deleted.
     */
    public void deleteItem(int itemId) {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));
        itemsRepository.delete(item);
    }


    // ################################################################################################################
    // ################# LUNCH ########################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code lunch} items.
     * @return {@code List<Lunch>} List of all lunch items.
     */
    public List<Lunch> getAllLunchItems() {
        return lunchRepository.findAllWithItems();
    }

    /**
     * Method to get all {@code lunch} items by {@code date}.
     * @param date The date of the lunch items.
     * @return {@code List<Lunch>} List of all lunch items.
     */
    public List<Lunch> getLunchItemsByDate(Date date) {
        return lunchRepository.findByDate(date);
    }

    /**
     * Method to get all {@code lunch} items by {@code week} and {@code year}.
     * @param week The week of the lunch items.
     * @param year The year of the lunch items.
     * @return {@code List<Lunch>} List of all lunch items.
     */
    public List<Lunch> getLunchItemsByWeekAndYear(int week, int year) {
        return lunchRepository.findByWeekAndYear(week, year);
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add a {@code lunch} item.
     * @param lunch The lunch item to be added.
     * @return {@code Lunch} The lunch item.
     */
    public Lunch addLunchItem(Lunch lunch) {
        return lunchRepository.save(lunch);
    }

    /**
     * Method to add items to a {@code lunch} item. Checks if the lunch item exists before adding items.
     * Else an exception is thrown with a proper error message.
     * @param lunchId The ID of the lunch item.
     * @param itemIds The IDs of the items to be added.
     * @return {@code Lunch} The lunch item.
     */
    public Lunch addItemsToLunch(Integer lunchId, List<Integer> itemIds) {
        Lunch lunch = lunchRepository.findById(lunchId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lunch ID"));

        for (Integer itemId : itemIds) {
            Items item = itemsRepository.findById(itemId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));
            LunchItems lunchItem = new LunchItems();
            lunchItem.setLunch(lunch);
            lunchItem.setItem(item);
            lunch.getLunchItems().add(lunchItem);
        }

        return lunchRepository.save(lunch);
    }

    // DELETE ---------------------------------------------------------------------------------------------------------
    /**
     * Method to delete a {@code lunch} item. Has to check if the lunch item exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param lunchId The ID of the lunch item to be deleted.
     */
    public void deleteLunchItem(int lunchId) {
        Lunch lunch = lunchRepository.findById(lunchId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lunch ID"));
        lunchRepository.delete(lunch);
    }


    // ################################################################################################################
    // ################# DINNER #######################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code dinner} items.
     * @return {@code List<Dinner>} List of all dinner items.
     */
    public List<Dinner> getAllDinnerItems() {
        return dinnerRepository.findAll();
    }

    /**
     * Method to get all {@code dinner} items by {@code type}.
     * @param type The type of the dinner items.
     * @return {@code List<Dinner>} List of all dinner items.
     */
    public List<Dinner> getDinnerItemsByType(String type) {
        return dinnerRepository.findByType(type);
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add a {@code dinner} item.
     * @param dinner The dinner item to be added.
     * @return {@code Dinner} The dinner item.
     */
    public Dinner addDinnerItem(Dinner dinner) {
        return dinnerRepository.save(dinner);
    }

    // DELETE ---------------------------------------------------------------------------------------------------------
    /**
     * Method to delete a {@code dinner} item. Has to check if the dinner item exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param dinnerId The ID of the dinner item to be deleted.
     */
    public void deleteDinnerItem(int dinnerId) {
        Dinner dinner = dinnerRepository.findById(dinnerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dinner ID"));
        dinnerRepository.delete(dinner);
    }


    // ################################################################################################################
    // ################# BOOKINGS #####################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code bookings}.
     * @return {@code List<Bookings>} List of all bookings.
     */
    public List<Bookings> getBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Method to get a {@code booking} by {@code Date}.
     * @param date The Date of the booking.
     * @return {@code List<Bookings>} The bookings with the specified date.
     */
    public List<Bookings> getBookingsByDate(Date date) {
        return bookingRepository.findByDate(date);
    }

    /**
     * Method to get a {@code booking} by {@code week} and {@code year}.
     * @param week The week of the booking.
     * @param year The year of the booking.
     * @return {@code List<Bookings>} The bookings with the specified status.
     */
    public List<Bookings> getBookingsByWeekAndYear(int week, int year) {
        return bookingRepository.findByWeekAndYear(week, year);
    }

    /**
     * Method to get a {@code booking} by {@code status}. It checks if it is flagged as 'Confirmed'.
     * @param tableId The table id the person is booking at.
     * @param dateTime The date and time of the booking.
     * @return {@code List<Bookings>} The bookings with the specified status.
     */
    public List<Bookings> getConfirmedBookingsByTableIdAndDateTime(Integer tableId, Timestamp dateTime) {
        return bookingRepository.findConfirmedBookingsByTableIdAndDateTime(tableId, dateTime);
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add a {@code booking}.
     * @param booking The booking to be added.
     * @return {@code Bookings} The booking.
     */
    public Bookings addBooking(Bookings booking) {
        return bookingRepository.save(booking);
    }

    /**
     * Method to update the status of a {@code booking}. It checks if the booking exists before updating.
     * Else an exception is thrown with a proper error message.
     * @param id The ID of the booking.
     * @param status The status of the booking.
     * @return {@code Bookings} The booking.
     */
    public Bookings updateBookingStatus(Integer id, Bookings.Status status) {
        Bookings booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    /**
     * Method to get a {@code booking} by {@code personId} and {@code date}. It checks if it is flagged as 'Confirmed'.
     * @param personId The person id of the booking.
     * @param date The date of the booking.
     * @return {@code Bookings} The booking.
     */
    public Bookings getConfirmedBookingByPersonIdAndDate(Integer personId, Timestamp date) {
        Date sqlDate = new Date(date.getTime());
        return bookingRepository.findConfirmedBookingByPersonIdAndDate(personId, sqlDate)
                .orElseThrow(() -> new IllegalArgumentException("No confirmed booking found for the given person ID and date"));
    }

    // DELETE ---------------------------------------------------------------------------------------------------------
    /**
     * Method to delete a {@code booking}. Has to check if the booking exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param bookingId The ID of the booking to be deleted.
     */
    public void deleteBooking(int bookingId) {
        Bookings booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));
        bookingRepository.delete(booking);
    }


    // ################################################################################################################
    // ################# EVENTS #######################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code events}.
     * @return {@code List<Events>} List of all events.
     */
    public List<Events> getAllEvents() {
        return eventsRepository.findAll();
    }

    /**
     * Method to get a {@code event} by {@code week} and {@code year}.
     * @param week The week of the event.
     * @param year The year of the event.
     * @return {@code List<Events>} The events with the specified week and year.
     */
    public List<Events> getEventsByWeekAndYear(int week, int year) {
        return eventsRepository.findByWeekAndYear(week, year);
    }

    /**
     * Method to get a {@code event} by {@code date}.
     * @param date The date of the event.
     * @return {@code List<Events>} The events with the specified date.
     */
    public List<Events> getEventsByDate(Date date) {
        return eventsRepository.findByDate(date);
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add an {@code event}.
     * @param event The event to be added.
     * @return {@code Events} The event.
     */
    public Events addEvent(Events event) {
        return eventsRepository.save(event);
    }

    // DELETE ---------------------------------------------------------------------------------------------------------
    /**
     * Method to delete an {@code event}. Has to check if the event exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param eventId The ID of the event to be deleted.
     */
    public void deleteEvent(int eventId) {
        Events event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
        eventsRepository.delete(event);
    }

    // ################################################################################################################
    // ################# ORDERS #######################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code orders}.
     * @return {@code List<Orders>} List of all orders.
     */
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Method to get a {@code order} by {@code id}.
     * If the ID is invalid, an exception is thrown.
     * @param id The ID of the order.
     * @return {@code Orders} The order.
     */
    public Orders getOrdersById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));
    }

    /**
     * Method to get a {@code order} by {@code date}.
     * @param date The date of the order.
     * @return {@code List<Orders>} The orders with the specified date.
     */
    public List<Orders> getOrdersByDate(Date date) {
        return orderRepository.findByDate(date);
    }

    /**
     * Method to get a {@code order} by {@code status}.
     * @param status The status of the order.
     * @return {@code List<Orders>} The orders with the specified status.
     */
    public List<Orders> getOrdersByStatus(Orders.Status status) {
        return orderRepository.findByStatus(status);
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add an {@code order}.
     * @param order The order to be added.
     * @return {@code Orders} The order.
     */
    public Orders addOrder(Orders order) {
        return orderRepository.save(order);
    }

    /**
     * Method to add a {@code menu item} to an {@code order}.
     * If the order ID or dinner ID is invalid, an exception is thrown.
     * @param orderId The ID of the order.
     * @param dinnerId The ID of the dinner item.
     * @return {@code Orders} The order.
     */
    public Orders addMenuItemToOrder(int orderId, Integer dinnerId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));
        Dinner dinner = dinnerRepository.findById(dinnerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dinner ID"));

        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setOrder(order);
        foodOrder.setDinner(dinner);

        foodOrderRepository.save(foodOrder);

        return orderRepository.save(order);
    }

    /**
     * Method to add a {@code drink} to an {@code order}.
     * If the order ID or drink ID is invalid, an exception is thrown.
     * Adds onto the quantity if the drink is already in the order.
     * @param orderId The ID of the order.
     * @param drinkId The ID of the drink.
     * @return {@code Orders} The order.
     */
    public Orders addDrinkToOrder(int orderId, Integer drinkId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));
        Drinks drink = drinksRepository.findById(drinkId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid drink ID"));

        Optional<DrinkOrder> optionalDrinkOrder = drinkOrderRepository.findByOrderAndDrink(order, drink);
        DrinkOrder drinkOrder = optionalDrinkOrder.orElseGet(() -> {
            DrinkOrder newDrinkOrder = new DrinkOrder();
            newDrinkOrder.setOrder(order);
            newDrinkOrder.setDrink(drink);
            newDrinkOrder.setQuantity(0);
            return newDrinkOrder;
        });

        drinkOrder.setQuantity(drinkOrder.getQuantity() + 1);
        drinkOrderRepository.save(drinkOrder);

        return orderRepository.save(order);
    }

    /**
     * Method to add a {@code note} to an {@code order}.
     * If the order ID is invalid, an exception is thrown.
     * @param orderId The ID of the order.
     * @param status The status of the order.
     * @return {@code Orders} The order.
     */
    public Orders updateOrderStatus(int orderId, Orders.Status status) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    /**
     * Method to add a {@code note} to an {@code order}.
     * If the order ID is invalid, an exception is thrown.
     * @param orderId The ID of the order.
     * @param note The note to be added.
     * @return {@code Orders} The order.
     */
    public Orders updateOrderNote(int orderId, String note) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));
        order.setNote(note);
        return orderRepository.save(order);
    }

    // DELETE ---------------------------------------------------------------------------------------------------------
    /**
     * Method to delete an {@code order}. Has to check if the order exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param orderId The ID of the order to be deleted.
     */
    public void deleteOrder(int orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));
        orderRepository.delete(order);
    }

    /**
     * Method to delete a {@code menu item} from an {@code order}.
     * If the order ID is invalid, an exception is thrown.
     * If the food order does not belong to the order, an exception is thrown.
     * @param orderId The ID of the order.
     * @param foodOrderId The ID of the food order.
     */
    public void deleteMenuItemFromOrder(int orderId, int foodOrderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));
        FoodOrder foodOrder = foodOrderRepository.findById(foodOrderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid food order ID"));

        if (foodOrder.getOrder().getId() != order.getId()) {
            throw new IllegalArgumentException("Food order does not belong to the specified order");
        }

        foodOrderRepository.delete(foodOrder);
    }

    /**
     * Method to delete a {@code drink} from an {@code order}.
     * If the order ID is invalid, an exception is thrown.
     * If the drink is the last one in the order, the drink order is deleted.
     * Else the quantity is decreased by one.
     * @param orderId The ID of the order.
     * @param drinkId The ID of the drink.
     */
    public void deleteDrinkFromOrder(int orderId, Integer drinkId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));
        Drinks drink = drinksRepository.findById(drinkId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid drink ID"));

        DrinkOrder drinkOrder = drinkOrderRepository.findByOrderAndDrink(order, drink)
                .orElseThrow(() -> new IllegalArgumentException("Invalid drink order"));

        if (drinkOrder.getQuantity() == 1) {
            drinkOrderRepository.delete(drinkOrder);
        } else {
            drinkOrder.setQuantity(drinkOrder.getQuantity() - 1);
            drinkOrderRepository.save(drinkOrder);
        }
    }


    // ################################################################################################################
    // ################# TABS #########################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code tabs}.
     * @return {@code List<Tab>} List of all tabs.
     */
    public List<Tab> getAllTabs() {
        return tabRepository.findAll();
    }

    /**
     * Method to get a {@code tab} by {@code id}.
     * If the ID is invalid, an exception is thrown.
     * @param id The ID of the tab.
     * @return {@code Tab} The tab.
     */
    public Tab getTabById(Integer id) {
        return tabRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tab ID"));
    }

    /**
     * Method to get a {@code tab} by {@code date}.
     * @param date The date of the tab.
     * @return {@code List<Tab>} The tabs with the specified date.
     */
    public List<Tab> getTabByDate(Timestamp date) {
        return tabRepository.findByOpened_at(date);
    }

    /**
     * Method to get a {@code tab} by {@code status}.
     * @param status The status of the tab.
     * @return {@code List<Tab>} The tabs with the specified status.
     */
    public List<Tab> getTabByStatus(Tab.Status status) {
        return tabRepository.findByStatus(status);
    }

    /**
     * Method to get a {@code tab} by {@code table}.
     * @param table The table of the tab.
     * @return {@code List<Tab>} The tabs with the specified table.
     */
    public List<Tab> getTabByTable(Integer table) {
        return tabRepository.findByTable_number(table);
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add a {@code tab}.
     * @param tab The tab to be added.
     * @return {@code Tab} The tab.
     */
    public Tab addTab(Tab tab) {
        return tabRepository.save(tab);
    }

    /**
     * Method to update the status of a {@code tab}.
     * If the tab ID is invalid, an exception is thrown.
     * @param tabId The ID of the tab.
     * @param status The status of the tab.
     * @return {@code Tab} The tab.
     */
    public Tab updateTabStatus(int tabId, Tab.Status status) {
        Tab tab = tabRepository.findById(tabId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tab ID"));
        tab.setStatus(status);
        return tabRepository.save(tab);
    }

    // DELETE ---------------------------------------------------------------------------------------------------------
    /**
     * Method to delete a {@code tab}. Has to check if the tab exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param tabId The ID of the tab to be deleted.
     */
    public void deleteTab(int tabId) {
        Tab tab = tabRepository.findById(tabId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tab ID"));
        tabRepository.delete(tab);
    }


    // ################################################################################################################
    // ################# DRINKS #######################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code drinks}.
     * @return {@code List<Drinks>} List of all drinks.
     */
    public List<Drinks> getAllDrinks() {
        return drinksRepository.findAll();
    }

    /**
     * Method to get a {@code drink} by {@code id}.
     * If the ID is invalid, an exception is thrown.
     * @param id The ID of the drink.
     * @return {@code Drinks} The drink.
     */
    public Drinks getDrinkById(int id) {
        return drinksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid drink ID"));
    }

    /**
     * Method to get a {@code drink} by {@code type}.
     * @param type The type of the drink.
     * @return {@code List<Drinks>} The drinks with the specified type.
     */
    public List<Drinks> getDrinksByType(String type) {
        return drinksRepository.findByType(type);
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add a {@code drink}.
     * @param drink The drink to be added.
     * @return {@code Drinks} The drink.
     */
    public Drinks addDrink(Drinks drink) {
        return drinksRepository.save(drink);
    }

    /**
     * Method to update the price of a {@code drink}.
     * If the drink ID is invalid, an exception is thrown.
     * @param drinkId The ID of the drink.
     * @param price The price of the drink.
     * @return {@code Drinks} The drink.
     */
    public Drinks updateDrinkPrice(int drinkId, Integer price) {
        Drinks drink = drinksRepository.findById(drinkId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid drink ID"));
        drink.setPrice(price);
        return drinksRepository.save(drink);
    }

    // DELETE ---------------------------------------------------------------------------------------------------------
    /**
     * Method to delete a {@code drink}. Has to check if the drink exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param drinkId The ID of the drink to be deleted.
     */
    public void deleteDrink(int drinkId) {
        Drinks drink = drinksRepository.findById(drinkId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid drink ID"));
        drinksRepository.delete(drink);
    }


    // ################################################################################################################
    // ################# TABLES #######################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code tables}.
     * @return {@code List<Tables>} List of all tables.
     */
    public List<Tables> getAllTables() {
        return tablesRepository.findAll();
    }

    /**
     * Method to get a {@code table} by {@code id}.
     * If the ID is invalid, an exception is thrown.
     * @param id The ID of the table.
     * @return {@code Tables} The table.
     */
    public Tables getTableById(int id) {
        return tablesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid table ID"));
    }

    /**
     * Method to get a {@code table} by {@code room_for_people}.
     * @param room_for_people The room_for_people of the table.
     * @return {@code List<Tables>} The tables with the specified room_for_people.
     */
    public List<Tables> getTablesByNumberOfSeats(int room_for_people) {
        return tablesRepository.findByRoom_for_people(room_for_people);
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add a {@code table}.
     * @param table The table to be added.
     * @return {@code Tables} The table.
     */
    public Tables addTable(Tables table) {
        return tablesRepository.save(table);
    }

    // DELETE ---------------------------------------------------------------------------------------------------------
    /**
     * Method to delete a {@code table}. Has to check if the table exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param tableId The ID of the table to be deleted.
     */
    public void deleteTable(int tableId) {
        Tables table = tablesRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid table ID"));
        tablesRepository.delete(table);
    }


    // ################################################################################################################
    // ################# EMPLOYEES ####################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code employees}.
     * @return {@code List<Employee>} List of all employees.
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Method to get a {@code employee} by {@code id}.
     * If the ID is invalid, an exception is thrown.
     * @param id The ID of the employee.
     * @return {@code Employee} The employee.
     */
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
    }

    /**
     * Method to get a {@code employee} by {@code name}.
     * @param name The name of the employee.
     * @return {@code List<Employee>} The employees with the specified name.
     */
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByName(name);
    }

    /**
     * Method to get the password of the {@code Employee} by asking using {@code email}.
     * @param email The email of the employee.
     * @return {@code String} The password string of the employee.
     */
    public String getEmployeePasswordByEmail(String email) {
        return employeeRepository.findPasswordByEmail(email);
    }

    /**
     * Method to get a {@code Employee}s {@code Role}s by asking with {@code employee_id}.
     * @param employee_id The Employee ID of the employee.
     * @return {@code List<Role>} The roles of the employee.
     */
    public List<Role> getHierarchyByEmployeeId(int employee_id) {
        return employeeRepository.findHierarchyByEmployeeId(employee_id);
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add an {@code employee}.
     * Checks if the person exists and if the person ID is already associated with an existing employee.
     * If the person or role ID is invalid, an exception is thrown.
     * @param employee The employee to be added.
     * @return {@code Employee} The employee.
     */
    public Employee addEmployee(Employee employee) {
        Person person = employee.getPerson(); // Check if person exists
        if (person.getPerson_id() == null || !personRepository.existsById(person.getPerson_id())) {
            throw new IllegalArgumentException("Invalid person ID");
        }

        // Check if person ID is already associated with an existing employee
        if (employeeRepository.existsByPersonId(person.getPerson_id())) {
            throw new IllegalArgumentException("Person ID is already in use");
        }

        Role role = employee.getRole(); // Check if role exists
        if (role.getRole_id() == null || !roleRepository.existsById(role.getRole_id())) {
            throw new IllegalArgumentException("Invalid role ID");
        }

        return employeeRepository.save(employee);
    }

    /**
     * Method to update the password of an {@code employee}.
     * If the employee ID is invalid, an exception is thrown.
     * @param employeeId The ID of the employee.
     * @param password The new password string to set as the password.
     * @return {@code Employee} The employee.
     */
    public Employee updateEmployeePassword(int employeeId, String password) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
        employee.setPassword(password);
        return employeeRepository.save(employee);
    }

    /**
     * Method to change the role of an {@code employee}.
     * If the employee ID or role ID is invalid, an exception is thrown.
     * @param employeeId The ID of the employee.
     * @param roleId The ID of the role.
     * @return {@code Employee} The employee.
     */
    public Employee changeEmployeeRole(int employeeId, int roleId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));

        employee.setRole(role);
        return employeeRepository.save(employee);
    }

    // DELETE ---------------------------------------------------------------------------------------------------------
    /**
     * Method to delete an {@code employee}. Has to check if the employee exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param employeeId The ID of the employee to be deleted.
     */
    public void deleteEmployee(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
        employeeRepository.delete(employee);
    }


    // ################################################################################################################
    // ################# ROLES ########################################################################################
    // ################################################################################################################

    // GET ------------------------------------------------------------------------------------------------------------
    /**
     * Method to get all {@code roles}.
     * @return {@code List<Role>} List of all roles.
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Method to get a {@code role} by {@code id}.
     * If the ID is invalid, an exception is thrown.
     * @param id The ID of the role.
     * @return {@code Role} The role.
     */
    public Role getRoleById(int id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));
    }

    // POST -----------------------------------------------------------------------------------------------------------
    /**
     * Method to add a {@code role}.
     * @param role The role to be added.
     * @return {@code Role} The role.
     */
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Method to update the hierarchy level of a {@code role}.
     * If the role ID is invalid, an exception is thrown.
     * @param roleId The ID of the role.
     * @param hierarchyLevel The new hierarchy level.
     * @return {@code Role} The role.
     */
    public Role updateRoleHierarchy(int roleId, Integer hierarchyLevel) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));
        role.setHierarchy_level(hierarchyLevel);
        return roleRepository.save(role);
    }

    // DELETE ---------------------------------------------------------------------------------------------------------
    /**
     * Method to delete a {@code role}. Has to check if the role exists before deleting.
     * Else an exception is thrown with a proper error message.
     * @param roleId The ID of the role to be deleted.
     */
    public void deleteRole(int roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));
        roleRepository.delete(role);
    }
}