package edu.miu.cs545.store.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ProductDTO {
    @NotEmpty
    private String productNumber;

    @NotEmpty
    @Length(max = 100)
    private String name;

    private Double price;

    @Length(max = 500)
    private String description;

    @Min(0l)
    private Integer numberInStock;

    public ProductDTO() {
    }

    public ProductDTO(String productNumber, String name, Double price, String description, Integer numberInStock) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.description = description;
        this.numberInStock = numberInStock;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(Integer numberInStock) {
        this.numberInStock = numberInStock;
    }
}
