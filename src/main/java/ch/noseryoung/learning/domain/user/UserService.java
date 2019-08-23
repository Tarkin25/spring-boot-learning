package ch.noseryoung.learning.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public void create(User user) {
        user.setId(null);
        repository.save(user);
    }

    public void update(Long id, User user) {
        if(repository.existsById(id)) {
            user.setId(id);
            repository.save(user);
        } else {
            throw new NoSuchElementException(String.format("User with ID '%d' could not be found", id));
        }
    }
}
