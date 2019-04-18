package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamsee Vennu on 4/17/2019.
 */
@Entity
public class Menu {

    //TODO The id and name fields should get the same annotations as the corresponding
    // fields in the Cheese class.
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @ManyToMany
    private List<Cheese> cheeses=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public List<Cheese> getCheeses() {
        return cheeses;
    }

     public String getName()
     {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Menu() {
    }
    public Menu(String name) {
        this.name = name;
    }

    public void addItem(Cheese item)
    {
        cheeses.add(item);
    }

}




