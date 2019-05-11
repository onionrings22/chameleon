package db.dao;

import db.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    Game findById(int id);
}
