package controller;

import repository.UserRepository;
import users.UserOfTV;

public class UserController {
    private UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepository();
    }
    public String createUser(String username, String password,String email) {
        UserOfTV user = this.userRepository.getByName(username);
        if (user != null) {
            throw new IllegalArgumentException("Username is taken!");
        }

        user = new UserOfTV( username, password,email);
        this.userRepository.add(user);
        return String.format("Successfully registered user: %s", username);

    }
    public void deleteClient(String username){
        UserOfTV user = this.userRepository.getByName(username);
        if (user==null){
            throw new IllegalArgumentException("Missing user!");
        }
        this.userRepository.remove(user);
    }
}
