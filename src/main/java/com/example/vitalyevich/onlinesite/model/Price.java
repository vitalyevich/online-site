package com.example.vitalyevich.onlinesite.model;

import javax.persistence.*;

@Entity
@Table(name = "prices")
public class Price {
    @Id
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product products;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "discount", nullable = false)
    private Integer discount;

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getPrice() {
        if(price % 1 == 0) {
            int number = (int) price.doubleValue();
            return number+"";
        } else {
            return price+"";
        }
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}