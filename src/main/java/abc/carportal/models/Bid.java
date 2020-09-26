package abc.carportal.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Annotate the Entity Classes: User and Post Put JPA annotations (table and
 * column mappings + relationship mappings) to the entity classes in order to
 * make then ready for persistence in the database through the JPA / Hibernate
 * technology
 *
 */
@Entity
@Table(name = "bid")
public class Bid {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private User bidUser;

  @Column(nullable = false)
  private Date date = new Date();

  @ManyToOne(fetch = FetchType.LAZY)
  private Post item;

  @Column(nullable = false, length = 300)
  private String bidPrice;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the bidUser
   */
  public User getBidUser() {
    return bidUser;
  }

  /**
   * @param bidUser the id to set
   */
  public void setBidUser(User bidUser) {
    this.bidUser = bidUser;
  }

  /**
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @return the id
   */
  public Post getItem() {
    return item;
  }

  /**
   * @param item the id to set
   */
  public void setItem(Post item) {
    this.item = item;
  }

  /**
   * @return the id
   */
  public String getBidPrice() {
    return bidPrice;
  }

  /**
   * @param bidPrice the id to set
   */
  public void setBidPrice(String bidPrice) {
    this.bidPrice = bidPrice;
  }

  public Bid() {
  }

  /**
   * @param id
   * @param bidUser
   * @param date
   * @param item
   * @param bidPrice
   * @param date
   * 
   */
  public Bid(Long id, Post item, String bidPrice, User bidUser, Date date) {
    this.id = id;
    this.item = item;
    this.bidPrice = bidPrice;
    this.bidUser = bidUser;
    this.date = date;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Bid []";
  }

}
