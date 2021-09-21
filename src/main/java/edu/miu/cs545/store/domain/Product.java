package edu.miu.cs545.store.domain;

import edu.miu.cs545.store.exception.InsufficientStockException;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Document
public class Product {
    @Id
    private String productNumber;

    @NotEmpty
    @Length(max = 100)
    private String name;

    private double price;
    @Length(max = 500)
    private String description;

    @Min(0l)
    private int numberInStock;

    public Product() {
    }

    public Product(String productNumber, String name, double price, String description, int numberInStock) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }

    // business logics
    public void reduceNumberInStock(int quantity) {
        if (numberInStock < quantity) {
            throw new InsufficientStockException(productNumber);
        }

        numberInStock -= quantity;
    }

}
