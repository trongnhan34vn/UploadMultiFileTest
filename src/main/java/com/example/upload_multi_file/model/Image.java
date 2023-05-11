package com.example.upload_multi_file.model;

public class Image {
    private int id;
    private String url;
    private int productId;

    public Image(int id, String url, int productId) {
        this.id = id;
        this.url = url;
        this.productId = productId;
    }

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
