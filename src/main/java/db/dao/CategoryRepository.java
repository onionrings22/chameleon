package db.dao;

import db.model.Category;
import db.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
    Category findByActiveTrue();

    List<Category> findAllByLastIsNullOrLastFalse();

    Category findByLastTrue();
}
