package repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import model.User;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    void delete(UUID id);
}
