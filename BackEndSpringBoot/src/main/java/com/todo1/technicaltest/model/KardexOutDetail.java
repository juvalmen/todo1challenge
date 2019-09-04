package com.todo1.technicaltest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kardexoutdetail")
public class KardexOutDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3443434222954354233L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idkardexoutdetail;	
	private Long quantity;	
		
	@ManyToOne
	@JoinColumn(name="idkardexout", nullable = false)
	private KardexOut kardexOut;

	@ManyToOne
	@JoinColumn(name="idproduct", nullable = false)
	private Product product;

	public Long getIdkardexoutdetail() {
		return idkardexoutdetail;
	}

	public void setIdkardexoutdetail(Long idkardexoutdetail) {
		this.idkardexoutdetail = idkardexoutdetail;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public KardexOut getKardexOut() {
		return kardexOut;
	}

	public void setKardexOut(KardexOut kardexOut) {
		this.kardexOut = kardexOut;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}