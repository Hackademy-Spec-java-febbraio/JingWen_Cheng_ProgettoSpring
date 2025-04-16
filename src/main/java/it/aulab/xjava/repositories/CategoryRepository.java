package it.aulab.xjava.repositories;

import it.aulab.xjava.models.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {
}
