package edu.miu.cs545.store.dto;

import edu.miu.cs545.store.domain.OrderStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;

@Document
public class OrderDTO {
    private Long id;
    private PersonalInfoDTO personalInfo;
    private PaymentInfoDTO paymentInfo;
    private LocalDateTime dateTime;
    private OrderStatus status;
    private Collection<OrderItemDTO> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalInfoDTO getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfoDTO personalInfo) {
        this.personalInfo = personalInfo;
    }

    public PaymentInfoDTO getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfoDTO paymentInfo) {
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

    public Collection<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(Collection<OrderItemDTO> items) {
        this.items = items;
    }
}
