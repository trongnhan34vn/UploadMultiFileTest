package com.example.upload_multi_file.model;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private String description;
    private List<Image> listImgs;

    public Product(int id, String name, String description, List<Image> listImgs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.listImgs = listImgs;
    }

    public Product() {
    }

    public int getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getListImgs() {
        return listImgs;
    }

    public void setListImgs(List<Image> listImgs) {
        this.listImgs = listImgs;
    }
}
