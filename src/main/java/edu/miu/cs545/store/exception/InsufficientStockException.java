package edu.miu.cs545.store.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String productNumber) {
        super("There are not enough items of the product " + productNumber + " in stock");
    }
}
