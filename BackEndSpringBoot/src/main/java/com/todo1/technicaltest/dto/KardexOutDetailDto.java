package com.todo1.technicaltest.dto;

import java.io.Serializable;

public class KardexOutDetailDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3443434222954354233L;

	private Long idkardexoutdetail;	
	private Long quantity;	

	private Long idkardexout;

	private Long idproduct;
	private String productname;
	private Long stock;
	private Long stockmovement;
	
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
	public Long getIdkardexout() {
		return idkardexout;
	}
	public void setIdkardexout(Long idkardexout) {
		this.idkardexout = idkardexout;
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