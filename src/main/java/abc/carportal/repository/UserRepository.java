package abc.carportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import abc.carportal.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

}