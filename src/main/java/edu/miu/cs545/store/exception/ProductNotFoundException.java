package edu.miu.cs545.store.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productNumber) {
        super("Could not find product " + productNumber);
    }
}
