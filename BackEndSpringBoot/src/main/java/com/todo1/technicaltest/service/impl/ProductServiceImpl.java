package com.todo1.technicaltest.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.todo1.technicaltest.dto.CategoryDto;
import com.todo1.technicaltest.dto.ProductDto;
import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;
import com.todo1.technicaltest.model.Category;
import com.todo1.technicaltest.model.Product;
import com.todo1.technicaltest.repository.jdbc.JdbcProductRepository;
import com.todo1.technicaltest.repository.jpa.JpaProductRepository;
import com.todo1.technicaltest.service.CategoryService;
import com.todo1.technicaltest.service.ProductService;
import com.todo1.technicaltest.util.validations.ProductValidationMessages;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@Service
public class ProductServiceImpl implements ProductService {

	private JpaProductRepository jpaProductRepository;
	private JdbcProductRepository jdbcProductRepository;
	private DummyFieldMapper dummyFieldMapper;
	private CategoryService categoryService;
	private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class.getName());
	
	// Length fields
	private final int NAME_MAX_LENGTH = 80;
	private final int DESCRIPTION_MAX_LENGTH = 250;

	@Autowired
	public ProductServiceImpl(JpaProductRepository jpaProductRepository, JdbcProductRepository jdbcProductRepository,
			DummyFieldMapper dummyFieldMapper,CategoryService categoriaService) {
		this.jpaProductRepository = jpaProductRepository;
		this.jdbcProductRepository = jdbcProductRepository;
		this.dummyFieldMapper = dummyFieldMapper;
		this.categoryService = categoriaService;
	}
	
	@Override
	public boolean saveProduct(ProductDto productDto) throws Todo1CustomException {
		try {

			if (productDto.getIdProductCategory() == null) {
				throw new Todo1CustomException(ProductValidationMessages.CATEGORIA_PRODUCTO_NULO);
			}

			validations(productDto);			
			
			CategoryDto categoryDto = categoryService.getCategoryById(productDto.getIdProductCategory());
			
			Category categoria = dummyFieldMapper.map(categoryDto, Category.class);
			Product product = dummyFieldMapper.map(productDto, Product.class);
			product.setCategory(categoria);
			product.setCreationdate(new Date());
			
			jpaProductRepository.save(product);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new Todo1CustomException(e.getMessage());
		}
		return true;
	}

	@Override
	public List<ProductDto> getProductsList() throws Todo1CustomException {		
		List<ProductDto> products = jdbcProductRepository.getProductsList();
		return products;
	}
	
	@Override
	public List<ProductDto> getAllByFilter(String productString, String withStock) throws Todo1CustomException {		
		List<ProductDto> products = jdbcProductRepository.getAllByFilter(productString, withStock);
		return products;		
	}

	@Override
	public boolean updateProduct(ProductDto productDto) throws Todo1CustomException {
		try {

			if (productDto.getIdproduct() == null) {
				throw new Todo1CustomException(ProductValidationMessages.ID_PRODUCTO_NO_EXISTE);
			}

			validations(productDto);
						
			ProductDto productValidate = this.getProductById(productDto.getIdproduct());
			if(productValidate == null || productValidate.getIdproduct() == null) {
				throw new Todo1CustomException(ProductValidationMessages.ID_PRODUCTO_NO_EXISTE);
			}

			CategoryDto categoryDto = categoryService.getCategoryById(productDto.getIdProductCategory());
			
			Category category = dummyFieldMapper.map(categoryDto, Category.class);
			Product productUdate = dummyFieldMapper.map(productDto, Product.class);
			productUdate.setCategory(category);
			productUdate.setModificationdate(new Date());
	
			jpaProductRepository.save(productUdate);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new Todo1CustomException(e.getMessage());
		}
		return true;
	}

	@Override
	public boolean deleteProduct(Long id) throws Todo1CustomException {
		try {
			
			getProductsInKardexIn(id);
			jpaProductRepository.deleteById(id);
		} catch (Exception e) {
			throw new Todo1CustomException(e.getMessage());
		}
		return true;
	}

	@Override
	public ProductDto getProductById(Long id) throws Todo1CustomException {
		try {
			Optional<Product> product = jpaProductRepository.findById(id);
			if(product.isPresent()) {
				return dummyFieldMapper.map(product.get(), ProductDto.class);
			}
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new Todo1CustomException(e);
		}
	}
	
	/**
	 * 
	 * @param idProduct
	 * @throws Todo1CustomException
	 */
	public void getProductsInKardexIn(Long idProduct) throws Todo1CustomException {		
		Long productInMovement = jdbcProductRepository.getProductsInKardexIn(idProduct);
		if(productInMovement != null && productInMovement > 0) {
			throw new Todo1CustomException(ProductValidationMessages.ID_PRODUCTO_EN_MOVIMIENTO);
		}		
	}
	
	/**
	 * 
	 * @param productDto
	 * @throws Todo1CustomException
	 */
	public void validations(ProductDto productDto) throws Todo1CustomException {

		if (productDto.getIdProductCategory() == null) {
			throw new Todo1CustomException(ProductValidationMessages.CATEGORIA_PRODUCTO_NULO);
		}
		
		if (Strings.isNullOrEmpty(productDto.getName())) {
			throw new Todo1CustomException(ProductValidationMessages.NOMBRE_NULO);
		}
		if (productDto.getName().length() > NAME_MAX_LENGTH) {
			throw new Todo1CustomException(ProductValidationMessages.NOMBRE_LONGITUD);
		}
		if (Strings.isNullOrEmpty(productDto.getDescription())) {
			throw new Todo1CustomException(ProductValidationMessages.DESCRIPCION_NULO);
		}
		if (productDto.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
			throw new Todo1CustomException(ProductValidationMessages.DESCRIPCION_LONGITUD);
		}
		if (productDto.getPrice() == null) {
			throw new Todo1CustomException(ProductValidationMessages.PRECIO_NULL);
		}
		if (productDto.getPrice() < 0) {
			throw new Todo1CustomException(ProductValidationMessages.PRECIO_NEGATIVO);
		}
		
	}

}
