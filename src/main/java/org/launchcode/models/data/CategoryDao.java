package org.launchcode.models.data;

import org.launchcode.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vamsee Vennu on 4/12/2019.
 */
@Repository
@Trasanctional
public interface CategoryDao extends CrudRepository<Category,Integer> {
}
