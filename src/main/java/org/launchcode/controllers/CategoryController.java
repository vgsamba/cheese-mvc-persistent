package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamsee Vennu on 4/13/2019.
 */
//TODO Adding Categories
@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryDao categoryDao;



    //TODO View All Categories
    @RequestMapping(value = "")
    public String index(Model model) {

        Iterable<Category> categories = categoryDao.findAll();
        model.addAttribute("Category", categories); //cheeseDao.findAll());
        model.addAttribute("title", "Categories");

        return "category/index"; //category/index
    }
                                                            //TODO Add Categories
    @RequestMapping(value="add", method= RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Category");
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Category category, Errors errors)
    {
        if(errors.hasErrors())
        {   model.addAttribute("category",category);
            return("category/add");
        }

        categoryDao.save(category);
        return ("redirect:");
    }
}
