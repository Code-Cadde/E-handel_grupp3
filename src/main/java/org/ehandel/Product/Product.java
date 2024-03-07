package org.ehandel.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_product")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String taste;
    private String description;
    private String imageUrl;
    private int quantity;
    private double pricePerItem = 15.90;
    private double totalPrice = pricePerItem;
    
    public Long getId() {
        return id;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double finalprice) {
        this.totalPrice = finalprice;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTaste() {
        return taste;
    }
    public void setTaste(String taste) {
        this.taste = taste;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPricePerItem() {
        return pricePerItem;
    }
    public void setPricePerItem(double price) {
        this.pricePerItem = price;
    }
    
}
