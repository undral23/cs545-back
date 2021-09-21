package edu.miu.cs545.store.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Document
public class Order {
    @Id
    private Long id;
    private PersonalInfo personalInfo;
    private PaymentInfo paymentInfo;
    private LocalDateTime dateTime;
    private OrderStatus status;
    private Collection<OrderItem> items;

    public Order(PersonalInfo personalInfo, PaymentInfo paymentInfo) {
        this.personalInfo = personalInfo;
        this.paymentInfo = paymentInfo;
        this.dateTime = LocalDateTime.now();
        this.status = OrderStatus.PLACED;
        this.items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Collection<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }
}
