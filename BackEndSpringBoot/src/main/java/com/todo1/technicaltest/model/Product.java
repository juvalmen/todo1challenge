package com.todo1.technicaltest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3443434222954354233L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idproduct;	
	private String name;
	private String description;
	
	@Column(name = "price", nullable = false, length = 10, columnDefinition="Decimal(10,2)")
	private Double price;
	
	private Long stock;	
	
	@ManyToOne
	@JoinColumn(name="productcategory")
	private Category category;
	
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
	
//	
//	@ManyToOne
//	@JoinColumn(name="idaccount", nullable = false)
//	private Account account;
//
//	@Column(name = "movement_date", nullable = false)
//	private Date movementDate;
//	
//	@Column(name = "movement_type", nullable = false, length = 10)
//    @Enumerated(EnumType.STRING)
//    private MovementEnum movementType; 
//	
//	private String description;
//
//	@Column(name = "value", nullable = false, length = 10, columnDefinition="Decimal(10,2)")
//	private Double movementValue;
//
//	@Column(name = "creation_date", nullable = false, length = 13)
//    private Date creationDate;
	


}