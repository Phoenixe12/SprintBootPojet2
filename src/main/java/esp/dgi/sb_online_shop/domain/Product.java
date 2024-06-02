package esp.dgi.sb_online_shop.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String description;
  private String image;
  private double price;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
  private List<Item> items;

  public Product() {
  }

  public Product(String name, String description, String image, double price) {
    this.name = name;
    this.description = description;
    this.image = image;
    this.price = price;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
}
