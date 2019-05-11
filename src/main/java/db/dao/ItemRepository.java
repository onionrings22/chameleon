package db.dao;

import db.model.Item;
import db.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findByCategoryOrderById(String category);
}
