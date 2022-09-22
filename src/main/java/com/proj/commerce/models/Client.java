package com.proj.commerce.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "client", uniqueConstraints = @UniqueConstraint(name = "username_unique", columnNames = "username"))
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // @Column(name = "username", nullable = false, columnDefinition = "TEXT")
  private String username;

  // @Column(name = "password", nullable = false, columnDefinition = "TEXT")
  private String password;

  @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Product> products = new ArrayList<>();

  public Client(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Client() {
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void addProduct(Product product) {
    if (!this.products.contains(product)) {
      this.products.add(product);
      product.setClient(this);
    }
  }

  public void removeProduct(Product product) {
    if (!this.products.contains(product)) {
      this.products.remove(product);
    }
  }

  public List<Product> getProduct() {
    return products;
  }

  @Override
  public String toString() {
    return String.format("id: %d\nusername: %s\npassword: %s", id, username, password);
  }
}
