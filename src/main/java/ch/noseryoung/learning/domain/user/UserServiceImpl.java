package ch.noseryoung.learning.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByUsername(username);

        if (optionalUser.isPresent()) {
            return new UserDetailsImpl(optionalUser.get());
        } else {
            throw new UsernameNotFoundException("Not authenticated");
        }
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
