package com.sarakhman.Entity;

import java.time.LocalDateTime;

public class Product {
    private int id;
    private String name;
    private double price;
    private LocalDateTime creationDate;


    public int getId() {
        return id;
    }

    public Product() {
    }

    public Product(int id, String name, double price, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String toString() {
        return String.format("ID: %s | Товар: %s | Цена: %s | Время создания: %s",
                this.id, this.name, this.price, this.creationDate);
    }
}
