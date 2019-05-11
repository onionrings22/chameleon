package web.service;

import db.dao.UserRepository;
import db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //get all logged in users
    public List<User> getAllOnline() {
        return userRepository.findAllByOnlineTrue();
    }

    //get the chameleon user
    public User getChameleon() {
        return userRepository.findByChameleonTrue();
    }

    //unset the chameleon flag
    @Transactional
    public void resetChameleon() {
        User cham = userRepository.findByChameleonTrue();
        cham.setChameleon(false);
        userRepository.save(cham);
    }

    //sets one user as the chameleon and returns that user
    @Transactional
    public User setChameleon() {
        List<User> users = userRepository.findAllByOnlineTrue();
        Random rand = new Random();
        User cham = users.get(rand.nextInt(users.size()));
        cham.setChameleon(true);
        userRepository.save(cham);
        return cham;
    }

    //get votes from online users who have voted
    public List<String> getVotes() {
        List<User> users = userRepository.findAllByOnlineTrue();
        List<String> votes = new ArrayList<>();
        for(User user: users) {
            if (user.getVote()!= null && !user.getVote().isEmpty()) {
                votes.add(user.getVote());
            }
        }
        return votes;
    }

    //set a users's vote
    @Transactional
    public void vote(String username, String vote) {
        User user = userRepository.findByUsername(username);
        user.setVote(vote);
        userRepository.save(user);
    }

    //clear all votes from online users
    @Transactional
    public void resetVotes() {
        List<User> users = userRepository.findAllByOnlineTrue();
        for(User user: users) {
            user.setVote(null);
        }
        userRepository.saveAll(users);
    }

    //set all users to offline
    @Transactional
    public void setAllOffline() {
        List<User> users = userRepository.findAllByOnlineTrue();
        for(User user: users) {
            user.setOnline(false);
        }
        userRepository.saveAll(users);
    }
}
