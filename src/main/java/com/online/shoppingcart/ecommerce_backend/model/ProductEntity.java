package com.online.shoppingcart.ecommerce_backend.model;

import jakarta.persistence.*;


import java.util.List;

@Entity

@Table(name = "products")
public class ProductEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "image_url")
    private String imageUrl;
    
    @Embedded
    private Rating rating;

    private Double price;

    private String type;  // Added type for product category like clothing, appliance etc.
    private String sizeChartLink; // Added sizeChartLink for clothing products

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	  public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getSizeChartLink() {
	        return sizeChartLink;
	    }

	    public void setSizeChartLink(String sizeChartLink) {
	        this.sizeChartLink = sizeChartLink;
	    }

	@ElementCollection
    @CollectionTable(name = "product_keywords", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "keyword")
    private List<String> keywords;

	
}
