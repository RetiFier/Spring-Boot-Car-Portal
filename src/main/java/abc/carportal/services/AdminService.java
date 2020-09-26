package abc.carportal.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import abc.carportal.models.User;
import abc.carportal.utils.UserRegistrationValid;

public interface AdminService extends UserDetailsService {

  User findByEmail(String email);

  User save(UserRegistrationValid registration);
}
