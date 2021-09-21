package edu.miu.cs545.store.domain;

public enum OrderStatus {
    PLACED("PLACED"), SHIPPED("SHIPPED"), DELIVERED("DELIVERED");

    final String value;

    OrderStatus(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
