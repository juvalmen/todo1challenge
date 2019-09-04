package com.todo1.technicaltest.util.validations;

/**
 * 
 * @author Julian Valencia
 * 31/08/2019
 */
public class PersonValidationMessages {
	
	// Persona física
	public static final String NOMBRE_NULO = "El nombre de la persona es obligatorio";
	public static final String NOMBRE_LONGITUD = "La longitud del nombre debe ser de máximo 80 caracteres";
	public static final String APELLIDO_NULO = "El apellido es obligatorio";
	public static final String APELLIDO_LONGITUD = "La longitud del apellido debe ser de máximo 250 caracteres";	
	public static final String CATEGORIA_PERSONA_NULO = "La categoría es obligatoria";
	public static final String CATEGORIA_PERSONA_NO_EXISTE = "No se encontro el tipo de persona";

	public static final String ID_PERSONA_NO_EXISTE = "No se encontro el id del titular";
	public static final String CUIT_NULO = "El campo identificación no puede ser nulo o vacio";
	public static final String CUIT_LONGITUD = "La longitud del campo identificación debe ser de máximo 45 caracteres";
	public static final String CUIT_EXISTE ="Ya existe una persona con el número de identificación ingresado";
	public static final String ID_PERSONA_EN_MOVIMIENTO = "No es posible borrar esta persona ya que existen movimientos asociados a ella.";

	
	// Categoria
	public static final String KEY_NO_ENCONTRADA = "No se encontro la llave de la categoría";
}
