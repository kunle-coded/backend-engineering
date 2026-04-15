package service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import exception.ValidationException;
import model.Role;
import model.User;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerUser(String email, String password, Role role) throws RuntimeException {

        validateEmail(email);

        if(role != Role.GUEST){
            validatePassword(password);
        }

        if(role == Role.GUEST){
            password = null;
        }

        User newUser = new User(email, password, role);
        userRepository.save(newUser);
        return newUser;

    }

    public Optional<User> findUserById(UUID id){
        return userRepository.findById(id);
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(UUID id){
        userRepository.delete(id);
    }

    private void validateEmail(String email){
        if(!email.contains("@")){
            throw new ValidationException("Invalid email!");
        }

        if (this.userRepository.findByEmail(email).isPresent()) {
            throw new ValidationException("Email is already registered!");
        }
    }

    private void validatePassword(String password){
        if(password == null || password.isBlank()){
                throw new ValidationException("Error: Password cannot be empty.");
            }

        if(password.length() < 4){
            throw new ValidationException("Password must be at least 4 characters.");
        }
    }
    
}
