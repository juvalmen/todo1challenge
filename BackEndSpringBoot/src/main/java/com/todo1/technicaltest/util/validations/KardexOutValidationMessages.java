package com.todo1.technicaltest.util.validations;

/**
 * 
 * @author Julian Valencia
 * 31/08/2019
 */
public class KardexOutValidationMessages {
	
	public static final String NOMBRE_NULO = "El nombre del producto es obligatorio";
	public static final String NOMBRE_LONGITUD = "La longitud del nombre debe ser de máximo 80 caracteres";
	public static final String DESCRIPCION_NULO = "La descripción es obligatoria";
	public static final String DESCRIPCION_LONGITUD = "La longitud de la descripción debe ser de máximo 250 caracteres";	
	public static final String CATEGORIA_PRODUCTO_NULO = "El tipo de producto es obligatorio";
	public static final String PRECIO_NULL = "El precio del producto no debe ser nulo";
	public static final String PRECIO_NEGATIVO = "El precio del producto no debe ser negativo";
	public static final String STOCK_VALOR_INCORRECTO = "Cantidad incorrecta para salida de producto. Por favor revisar stock.";
	public static final String STOCK_NEGATIVO = "Los valores para las salidas de productos deben ser positivos.";
	public static final String SECUENCIAL_NULO = "El valor de secuencia para la salida no debe ser nulo.";
	public static final String PERSONA_NULL = "Se debe de ingresar la persona a cargo.";
	public static final String ENTRADA_SIN_PRODUCTOS = "Se debe de ingresar al menos un producto en la salida.";
	public static final String STOCK_INSUFICIENTE = "El valor ingresado en la salida es mayor al stock del siguiente producto: ";
	public static final String ID_KARDEX_NULO = "El id del Kardex no debe de ser nulo.";
	
	// Categoria
	public static final String KEY_NO_ENCONTRADA = "No se encontro la llave de la categoría";
}
