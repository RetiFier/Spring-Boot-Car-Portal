package abc.carportal.repository;

import java.util.List;
import java.util.Optional;

import abc.carportal.models.Post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  /**
   * 
   * 
   * @param pageable
   * @return
   */
  @Query(value = "SELECT p.* FROM posts p ORDER BY p.date DESC", nativeQuery = true)
  List<Post> findLates5Posts(Pageable pageable);

  // @Override
  // default Optional<Post> findById(Long id) {
  // // TODO Auto-generated method stub
  // return null;
  // }

}
