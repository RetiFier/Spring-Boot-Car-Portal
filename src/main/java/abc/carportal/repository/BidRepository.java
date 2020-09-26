package abc.carportal.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import abc.carportal.models.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

  @Override
  default Optional<Bid> findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  Bid findByItem(Long item);

}
