package com.todo1.technicaltest.enums;

public enum CategoryEnum {
	VENDEDOR("1"),
	EMPLEADO("2"),
	CAMISETA("1"),
	VASO("2"),
	JUGUETE("3"),
	ACCESORIO("4");
	
	private String type;	
	private CategoryEnum(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
