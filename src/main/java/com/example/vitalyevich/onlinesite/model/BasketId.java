package com.example.vitalyevich.onlinesite.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BasketId implements Serializable {
    private static final long serialVersionUID = -8519895320801033405L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BasketId entity = (BasketId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.userId, entity.userId);
    }

    public BasketId(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public BasketId() {
    }
}