package web.service;

import db.dao.GameRepository;
import db.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
    private GameRepository gameRepository;
    private final int ID = 2;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public boolean getReady() {
        Game game =  gameRepository.findById(ID);
        return game.isStart();
    }

    @Transactional
    public void makeReady() {
        Game game = gameRepository.findById(ID);
        game.setStart(true);
        gameRepository.save(game);
    }

    @Transactional
    public void endVoting() {
        Game game = gameRepository.findById(ID);
        game.setVotingDone(true);
        gameRepository.save(game);
    }

    public boolean getVotingDone() {
        Game game =  gameRepository.findById(ID);
        return game.isVotingDone();
    }

    @Transactional
    public void resetGame() {
        Game game = gameRepository.findById(ID);
        game.setStart(false);
        game.setReveal(false);
        game.setVotingDone(false);
        gameRepository.save(game);
    }
}
