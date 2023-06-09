package com.example.crudtestapplication.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private long id;
    private String name;
    private int price;

    public Product(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @NonNull
    @Override
    public String toString() {
        return "id: " + getId() + ", Name: " + getName() + ", Price: " + getPrice();
    }
}
