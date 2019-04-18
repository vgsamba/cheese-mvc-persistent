package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamsee Vennu on 4/12/2019.
 */
@Entity //his annotation will ensure that the class is mapped
// to a relational database table and also creates category table(here) in case it does not exists.
public class Category {
    @Id
    @GeneratedValue
    private int id;


    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Cheese> cheeses=new ArrayList<>();

    @NotNull
    @Size(min=3, max=15)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(List<Cheese> cheeses) {
        this.cheeses = cheeses;
    }



    public Category() {
    }

    public Category(String name1) {
        this.name = name1;
    }
}
