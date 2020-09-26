package abc.carportal.services;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import abc.carportal.models.Bid;
import abc.carportal.repository.BidRepository;

@Service
@Primary
public class BidServiceImpl implements BidService {

  @Autowired
  private BidRepository bidRepository;

  @Override
  public Bid findById(Long id) {
    return this.bidRepository.getOne(id);
    // return this.postRepository.getOne(id);
  }

  @Override
  public List<Bid> findAll() {
    return this.bidRepository.findAll();
  }

  @Override
  public Bid findByItem(Long item) {
    return this.bidRepository.findByItem(item);
  }

  @Override
  public Page<Bid> findAll(Pageable pageable) {
    return this.bidRepository.findAll(pageable);
  }

  @Override
  public Bid create(Bid bid) {
    return this.bidRepository.save(bid);
  }

  @Override
  public Bid edit(Bid bid) {
    return this.bidRepository.save(bid);
  }

  @Override
  public void deleteById(Long id) {
    this.bidRepository.deleteById(id);
  }
}
