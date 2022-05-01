package com.example.vitalyevich.onlinesite.model;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @Column(name = "order_date", nullable = false)
    private Instant orderDate;

    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "access_id", nullable = false)
    private Access access;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(name = "comment")
    private String comment;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "order")
    private Set<OrderProduct> orderProducts = new LinkedHashSet<>();

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return String.format("%.2f",price);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public String getEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")
                .withZone(ZoneId.systemDefault());
        return formatter.format(endDate);
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getOrderDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")
                .withZone(ZoneId.systemDefault());
        return formatter.format(orderDate);
    }

    public String getOrder() {

        List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
        orderProductList.addAll(orderProducts);

        String text = getType() + ": "+address.getCity().getCityName() + ", " + address.getStreet() + ", " + address.getHome()
                +"\n"+getAccess().getPhone()+"\n"+getAccess().getUser().getFirstName()+"\nДата: " +getEndDate()+"\n";

        int i = 1;
        for (OrderProduct product: orderProductList) {
            text+= " "+i+". " + product.getProduct().getProductName();
            text+= " " + product.getAmount() + " шт.";
            i++;
        }
        return text;

    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }


}