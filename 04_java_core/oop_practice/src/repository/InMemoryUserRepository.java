package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import model.User;

public class InMemoryUserRepository implements UserRepository {
    private Map<UUID, User> users = new HashMap<>();

    @Override
    public void save(User user){
        users.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(UUID id){
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email){
        return users.values().stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst();
    }
    
    @Override
    public List<User> findAll(){
        return new ArrayList<>(users.values());
    }

    @Override
    public void delete(UUID id){
        users.remove(id);
    }
    
}
