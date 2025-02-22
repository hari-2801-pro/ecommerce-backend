package com.online.shoppingcart.ecommerce_backend.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Rating {
    private Double stars;
    private Integer count;

    public Rating() {}

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
