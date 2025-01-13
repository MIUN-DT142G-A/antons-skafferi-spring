package se.antons_skafferi.dataClass;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * <h1>Orders</h1>
 * This class represents the orders table in the database.
 */
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;
    private Date date;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer employee_id;
    private Integer table_number;
    private Integer tab_id;
    private String note;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FoodOrder> foodOrders;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DrinkOrder> drinkOrders;

    @ManyToOne
    @JoinColumn(name = "table_number", insertable = false, updatable = false)
    private Tables table;

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "tab_id", insertable = false, updatable = false)
    private Tab tab;

    /**
     * Getter to get the order_id.
     * @return order_id
     */
    public Integer getOrder_id() {
        return order_id;
    }

    /**
     * Setter to set the order_id.
     * @param order_id The order_id.
     */
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    /**
     * Getter to get the date.
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter to set the date.
     * @param date The date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter to get the status.
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Setter to set the status.
     * @param status The status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Getter to get the employee_id.
     * @return employee_id
     */
    public Integer getEmployee_id() {
        return employee_id;
    }

    /**
     * Setter to set the employee_id.
     * @param employee_id The employee_id.
     */
    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    /**
     * Getter to get the table_number.
     * @return table_number
     */
    public Integer getTable_number() {
        return table_number;
    }

    /**
     * Setter to set the table_number.
     * @param table_number The table_number.
     */
    public void setTable_number(Integer table_number) {
        this.table_number = table_number;
    }

    /**
     * Getter to get the tab_id.
     * @return tab_id
     */
    public Integer getTab_id() {
        return tab_id;
    }

    /**
     * Setter to set the tab_id.
     * @param tab_id The tab_id.
     */
    public void setTab_id(Integer tab_id) {
        this.tab_id = tab_id;
    }

    /**
     * Getter to get the note.
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * Setter to set the note.
     * @param note The note.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Getter to get the foodOrders.
     * @return foodOrders
     */
    public List<FoodOrder> getFoodOrders() {
        return foodOrders;
    }

    /**
     * Setter to set the foodOrders.
     * @param foodOrders The foodOrders.
     */
    public void setFoodOrders(List<FoodOrder> foodOrders) {
        this.foodOrders = foodOrders;
    }

    /**
     * Getter to get the drinkOrders.
     * @return drinkOrders
     */
    public List<DrinkOrder> getDrinkOrders() {
        return drinkOrders;
    }

    /**
     * Setter to set the drinkOrders.
     * @param drinkOrders The drinkOrders.
     */
    public void setDrinkOrders(List<DrinkOrder> drinkOrders) {
        this.drinkOrders = drinkOrders;
    }

    /**
     * Getter to get the table.
     * @return table
     */
    public Tables getTable() {
        return table;
    }

    /**
     * Setter to set the table.
     * @param table The table.
     */
    public void setTable(Tables table) {
        this.table = table;
    }

    /**
     * Getter to get the employee.
     * @return employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Setter to set the employee.
     * @param employee The employee.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Getter to get the tab.
     * @return tab
     */
    public Tab getTab() {
        return tab;
    }

    /**
     * Setter to set the tab.
     * @param tab The tab.
     */
    public void setTab(Tab tab) {
        this.tab = tab;
    }

    /**
     * Method to get the id.
     * @return order_id
     */
    public Object getId() {
        return order_id;
    }

    /**
     * Enum to represent the status of the order.
     */
    public enum Status {
        Waiting,
        Preparing,
        Done
    }
}