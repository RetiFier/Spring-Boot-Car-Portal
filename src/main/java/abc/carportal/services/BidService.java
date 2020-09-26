package abc.carportal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import abc.carportal.models.Bid;

public interface BidService {

  Bid findById(Long id);

  Page<Bid> findAll(Pageable pageable);

  List<Bid> findAll();

  Bid create(Bid bid);

  Bid edit(Bid bid);

  Bid findByItem(Long item);

  void deleteById(Long id);
}
