package com.todo1.technicaltest.util.validations;

/**
 * 
 * @author Julian Valencia
 * 31/08/2019
 */
public class ProductValidationMessages {
	
	public static final String NOMBRE_NULO = "El nombre del producto es obligatorio";
	public static final String NOMBRE_LONGITUD = "La longitud del nombre debe ser de máximo 80 caracteres";
	public static final String DESCRIPCION_NULO = "La descripción es obligatoria";
	public static final String DESCRIPCION_LONGITUD = "La longitud de la descripción debe ser de máximo 250 caracteres";	
	public static final String CATEGORIA_PRODUCTO_NULO = "El tipo de producto es obligatorio";
	public static final String PRECIO_NULL = "El precio del producto no debe ser nulo";
	public static final String PRECIO_NEGATIVO = "El precio del producto no debe ser negativo";
	public static final String STOCK_VALOR_INCORRECTO = "Cantidad incorrecta para salida de producto. Por favor revisar stock.";
	public static final String ID_PRODUCTO_NO_EXISTE = "El id de producto ingresado no existe.";
	public static final String ID_PRODUCTO_EN_MOVIMIENTO = "No es posible borrar este producto ya que existen movimientos asociados a el.";
	
	// Categoria
	public static final String KEY_NO_ENCONTRADA = "No se encontro la llave de la categoría";
}
