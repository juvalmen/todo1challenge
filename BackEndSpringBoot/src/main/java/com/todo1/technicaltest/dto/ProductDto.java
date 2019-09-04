package com.todo1.technicaltest.dto;

import java.io.Serializable;
import java.util.Date;

public class ProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3443434222954354233L;

	private Long idproduct;	
	private String name;
	private String description;
	
	private Double price;
	
	private Long stock;	
	
	private Long idProductCategory;
	private String valueProductCategory;
    private String descriptionProductCategory;
	
	private Date creationdate;
	private Date modificationdate;
	public Long getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(Long idproduct) {
		this.idproduct = idproduct;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Long getIdProductCategory() {
		return idProductCategory;
	}
	public void setIdProductCategory(Long idProductCategory) {
		this.idProductCategory = idProductCategory;
	}
	public String getValueProductCategory() {
		return valueProductCategory;
	}
	public void setValueProductCategory(String valueProductCategory) {
		this.valueProductCategory = valueProductCategory;
	}
	public String getDescriptionProductCategory() {
		return descriptionProductCategory;
	}
	public void setDescriptionProductCategory(String descriptionProductCategory) {
		this.descriptionProductCategory = descriptionProductCategory;
	}
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	public Date getModificationdate() {
		return modificationdate;
	}
	public void setModificationdate(Date modificationdate) {
		this.modificationdate = modificationdate;
	}

}