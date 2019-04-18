package org.launchcode.models;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    //private CheeseType type;
    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "cheeses")//tell Hibernate how to store and populate objects from the list,
    // we specify that the field should be mappedBy the cheeses field of the Menu class.
    private List<Menu> menus;//It represents the list of Menu objects that a
                            // given cheese is contained in


    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category obj  )//this function assigns
    {
        this.category=obj;
    }
    public Cheese() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   /*e public CheeseType getTyp() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }*/
}
