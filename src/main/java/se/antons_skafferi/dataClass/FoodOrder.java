package se.antons_skafferi.dataClass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "food_order")
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "dinner_id")
    private Dinner dinner;

    private String note;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dinner getDinner() {
        return dinner;
    }

    public void setDinner(Dinner dinner) {
        this.dinner = dinner;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getNote() {return note;}

    public void setNote(String note) {this.note = note;}
}