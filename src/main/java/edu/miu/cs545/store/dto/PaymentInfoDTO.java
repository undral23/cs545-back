package edu.miu.cs545.store.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PaymentInfoDTO {
    @NotEmpty
    private String cartType;

    @NotEmpty
    @Length(min = 16, max = 20)
    private String number;

    @NotNull
    @Future
    private LocalDate dateValid;

    @NotEmpty
    @Length(min = 3, max = 3)
    private String validationCode;

    public PaymentInfoDTO() {
    }

    public PaymentInfoDTO(String cartType, String number, LocalDate dateValid, String validationCode) {
        this.cartType = cartType;
        this.number = number;
        this.dateValid = dateValid;
        this.validationCode = validationCode;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDateValid() {
        return dateValid;
    }

    public void setDateValid(LocalDate dateValid) {
        this.dateValid = dateValid;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}
