package com.example.vitalyevich.onlinesite.model;

import javax.persistence.*;

@Entity
@Table(name = "baskets")
public class Basket {
    @EmbeddedId
    private BasketId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BasketId getId() {
        return id;
    }

    public void setId(BasketId id) {
        this.id = id;
    }

    public Basket() {
    }

    public Basket(User user, Product product, Integer amount) {
        this.user = user;
        this.product = product;
        this.amount = amount;
    }

    public Basket(BasketId id, User user, Product product, Integer amount) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.amount = amount;
    }


}
