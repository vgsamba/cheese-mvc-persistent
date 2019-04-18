package org.launchcode.controllers;

import javassist.compiler.ast.Variable;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Vamsee Vennu on 4/17/2019.
 */
@Controller
@RequestMapping(value = "menu")

public class MenuController {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    //TODO display the list of all menus to the view using the interface menuDao
    @RequestMapping(value="")//when user is in index.html page and clicks on Menu link this method/ request handler is called  (loacalhost/menu)
    public String index(Model model)
    {
        model.addAttribute("title","Menus" );
        model.addAttribute("menus",menuDao.findAll());
        return "menu/index";
    }

    //TODO This handler should also pass in a new Menu object created by calling that class' default constructor.
    // TODO We'll use this object to help render the form.
    @RequestMapping(value = "add", method = RequestMethod.GET) //when user is in index.html(http://localhost:8080/menu)
                                                                //n user clicks on 'Add Menu' Button this handler called
                                                                // user is will be sent to add.html("menu/add")

    public String add(Model model)
    {
        model.addAttribute("title","Add Menus");
        model.addAttribute(new Menu());
        return "menu/add"; //will display this
    }


    @RequestMapping(value = "add", method = RequestMethod.POST) //in add.html("menu/add") page user clicks on "create menu"
                                                                    // then this handler is called
    public String add(Model model, @ModelAttribute @Valid Menu menu,
                                       Errors errors ) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menus");
            return "menu/add";
        }
        menuDao.save(menu);//when we save the menu to db hybernate will givw it a value n we get a menu id
        return "redirect:view/"  + menu.getId();//if no error then add.html form is processed and
                                        // user sent to view.html(localhost:menu/view/id number)
    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)//in add.html("menu/add")user click on a particular
                                                                        //menu item then this handler is called and it takes user view.html(localhost:menu/view)
    public String viewMenu(Model model, @PathVariable int menuId )
    {

        Menu menu=menuDao.findOne(menuId);
        model.addAttribute("title",menu.getName());
        model.addAttribute("cheeses", menu.getCheeses());
        model.addAttribute("menuId", menu.getId());
        model.addAttribute("menu",menu);
        return "menu/view";

    }


    //TODO create an instance of AddMenuItemForm with the given Menu object,
    // as well as the list of all Cheese items in the database.
    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model,@PathVariable int menuId)
    {
        Menu menu=menuDao.findOne(menuId);//fetch menu from db

        AddMenuItemForm form=new AddMenuItemForm(menu, cheeseDao.findAll());
        model.addAttribute("title","Add Item to Menu:" +menu.getName());
        //TODO Pass this form object into the view with the name "form", along with a title that reads
        // TODO "Add item to menu: MENU NAME" (using the actual menu name).
        model.addAttribute("form",form);
        return "menu/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model,@ModelAttribute @Valid AddMenuItemForm form, Errors errors)
    {
        if(errors.hasErrors())
        {
            model.addAttribute("form", form);
            return "menu/add-item";//sending form object to form(Addmenuitemform)
        }
        //If no error find the given Cheese and Menu by ID,
        // using the respective DAO objects, and add the item to the menu.
        Cheese theCheese=cheeseDao.findOne(form.getCheeseId());
        Menu theMenu=menuDao.findOne(form.getMenuId());

        theMenu.addItem(theCheese);

        //Use menuDao to save the menu:
        menuDao.save(theMenu);

        //TODO redirect to the URL corresponding to the full menu view for this menu.
        return "redirect:/menu/view/" +theMenu.getId();


    }
}