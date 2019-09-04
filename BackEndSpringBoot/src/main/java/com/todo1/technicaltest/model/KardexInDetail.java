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
@Table(name = "kardexindetail")
public class KardexInDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3443434222954354233L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idkardexindetail;	
	private Long quantity;	
		
	@ManyToOne
	@JoinColumn(name="idkardexin", nullable = false)
	private KardexIn kardexIn;

	@ManyToOne
	@JoinColumn(name="idproduct", nullable = false)
	private Product product;

	public Long getIdkardexindetail() {
		return idkardexindetail;
	}

	public void setIdkardexindetail(Long idkardexindetail) {
		this.idkardexindetail = idkardexindetail;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public KardexIn getKardexIn() {
		return kardexIn;
	}

	public void setKardexIn(KardexIn kardexIn) {
		this.kardexIn = kardexIn;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}