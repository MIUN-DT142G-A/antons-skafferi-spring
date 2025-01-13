package se.antons_skafferi.dataClass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

/**
 * <h1>Items</h1>
 * This class represents the items table in the database.
 */
@Entity
@Table(name = "items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer item_id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<LunchItems> lunchItems;

    /**
     * Getter to get the item_id.
     * @return item_id
     */
    public Integer getItem_id() {
        return item_id;
    }

    /**
     * Setter to set the item_id.
     * @param item_id The item_id.
     */
    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    /**
     * Getter to get the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter to set the name.
     * @param name The name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter to get the description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter to set the description.
     * @param description The description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter to get the lunchItems.
     * @return lunchItems
     */
    public List<LunchItems> getLunchItems() {
        return lunchItems;
    }

    /**
     * Setter to set the lunchItems.
     * @param lunchItems The lunchItems.
     */
    public void setLunchItems(List<LunchItems> lunchItems) {
        this.lunchItems = lunchItems;
    }
}