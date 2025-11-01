package webappdev.login;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
@Service
public class LoginService {
    private final UserRepository userRepository;

    @Autowired
    public LoginService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public boolean registerUser(String username, String password) {
        // Check if user already exists
        Optional<User> existingUser = userRepository.findUserByUsername(username);
        if (existingUser.isPresent()) {
            // User already exists, cannot register
            return false;
        }

        // Create a new user with the given username and password
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        // Save the new user to the database
        userRepository.save(newUser);

        // Return true indicating successful registration
        return true;
    }

    public boolean addNewUser(User user) {
        Optional<User> existingUser = userRepository.findUserByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return false; // Username is taken
        }
        // Encrypt the password before saving
        user.setPassword(user.getPassword()); // Assuming you have a password encoder
        userRepository.save(user);
        return true; // Username is not taken, user registered
    }

    public void deleteUserInfo(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("user with id "+userId+"does not exist.");
        }
        userRepository.deleteById(userId);

    }

    public User findUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        return user.orElse(null);
    }

    public User findUserById(Long id) {
        Optional<User> user = userRepository.findUserById(id);
        return user.orElse(null);
    }

    @Transactional
    public void updateUserInfo (Long userId, String userName, String email) {
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException(
                "user with id "+userId+"does not exist."
        ));
        if (userName != null && !userName.isEmpty() && !Objects.equals(user.getUsername(), userName)) {
            user.setUsername(userName);
        }
    }
}
