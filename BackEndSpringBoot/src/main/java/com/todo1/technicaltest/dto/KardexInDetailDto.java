package com.todo1.technicaltest.dto;

import java.io.Serializable;

public class KardexInDetailDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3443434222954354233L;

	private Long idkardexindetail;	
	private Long quantity;	

	private Long idkardexin;

	private Long idproduct;
	private String productname;
	private Long stock;
	private Long stockmovement;
	
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
	public Long getIdkardexin() {
		return idkardexin;
	}
	public void setIdkardexin(Long idkardexin) {
		this.idkardexin = idkardexin;
	}
	public Long getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(Long idproduct) {
		this.idproduct = idproduct;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public Long getStockmovement() {
		return stockmovement;
	}
	public void setStockmovement(Long stockmovement) {
		this.stockmovement = stockmovement;
	}
	
	
	
	
}