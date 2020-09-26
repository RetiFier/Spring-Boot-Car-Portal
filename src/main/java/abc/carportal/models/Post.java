package abc.carportal.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "posts")
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 300)
  private String title;

  @Column(nullable = false, length = 300)
  private String model;

  @Column(nullable = false, length = 300)
  private String brand;

  @Column(nullable = false, length = 300)
  private String color;

  @Column(nullable = false, length = 300)
  private String enginePower;

  @Column(nullable = false, length = 300)
  private String carLicensePlate;

  @Column(nullable = false, length = 300)
  private String startPrice;

  @Lob
  @Column(nullable = false)
  private String body;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private User author;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  private Bid bid;

  @Column(nullable = false)
  private Date date = new Date();

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
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the title
   */
  public String getModel() {
    return model;
  }

  /**
   * @param model the title to set
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * @return the title
   */
  public String getBrand() {
    return brand;
  }

  /**
   * @param brand the title to set
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * @return the title
   */
  public String getColor() {
    return color;
  }

  /**
   * @param color the title to set
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * @return the title
   */
  public String getEnginePower() {
    return enginePower;
  }

  /**
   * @param enginePower the title to set
   */
  public void setEnginePower(String enginePower) {
    this.enginePower = enginePower;
  }

  /**
   * @return the title
   */
  public String getCarLicensePlate() {
    return carLicensePlate;
  }

  /**
   * @param carLicensePlate the title to set
   */
  public void setCarLicensePlate(String carLicensePlate) {
    this.carLicensePlate = carLicensePlate;
  }

  /**
   * @return the title
   */
  public String getStartPrice() {
    return startPrice;
  }

  /**
   * @param startPrice the title to set
   */
  public void setStartPrice(String startPrice) {
    this.startPrice = startPrice;
  }

  /**
   * @return the body
   */
  public String getBody() {
    return body;
  }

  /**
   * @param body the body to set
   */
  public void setBody(String body) {
    this.body = body;
  }

  /**
   * @return the author
   */
  public Bid getBid() {
    return bid;
  }

  /**
   * @param bid the author to set
   */
  public void setBid(Bid bid) {
    this.bid = bid;
  }

  /**
   * @return the author
   */
  public User getAuthor() {
    return author;
  }

  /**
   * @param author the author to set
   */
  public void setAuthor(User author) {
    this.author = author;
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
   * 
   */
  public Post() {
  }

  /**
   * @param id
   * @param title
   * @param model
   * @param brand
   * @param bid
   * @param color
   * @param enginePower
   * @param carLicensePlate
   * @param startPrice
   * @param body
   * @param author
   * @param date
   */
  public Post(Long id, String title, String model, String brand, String color, String enginePower,
      String carLicensePlate, String startPrice, String body, User author, Bid bid, Date date) {
    this.id = id;
    this.title = title;
    this.model = model;
    this.brand = brand;
    this.color = color;
    this.enginePower = enginePower;
    this.carLicensePlate = carLicensePlate;
    this.startPrice = startPrice;
    this.body = body;
    this.author = author;
    this.date = date;
    this.bid = bid;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Post []";
  }

}