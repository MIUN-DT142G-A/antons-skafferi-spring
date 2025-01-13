package se.antons_skafferi.dataClass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * <h1>LunchItems</h1>
 * This class represents the lunch_items table in the database.
 */
@Entity
@Table(name = "lunch_items")
public class LunchItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "lunch_id", nullable = false)
    @JsonBackReference
    private Lunch lunch;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items item;

    /**
     * Getter to get the id.
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter to set the id.
     * @param id The id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter to get the lunch.
     * @return lunch
     */
    public Lunch getLunch() {
        return lunch;
    }

    /**
     * Setter to set the lunch.
     * @param lunch The lunch.
     */
    public void setLunch(Lunch lunch) {
        this.lunch = lunch;
    }

    /**
     * Getter to get the item.
     * @return item
     */
    public Items getItem() {
        return item;
    }

    /**
     * Setter to set the item.
     * @param item The item.
     */
    public void setItem(Items item) {
        this.item = item;
    }
}