package org.launchcode.models.data;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vamsee Vennu on 4/17/2019.
 *allow us to access Menu objects via the data
 * layer from within our controllers.**/
@Repository
@Trasanctional
public interface MenuDao extends CrudRepository<Menu, Integer>
//we need to enable Spring Data to store and retrieve instances of the Menu class.
{
}
