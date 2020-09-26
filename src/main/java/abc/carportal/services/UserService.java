package abc.carportal.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import abc.carportal.models.User;
import abc.carportal.utils.UserRegistrationValid;

public interface UserService extends UserDetailsService {

  User findByEmail(String email);

  List<User> findAll();

  Page<User> findAll(Pageable pageable);

  User findById(Long id);

  User save(UserRegistrationValid registration);
}