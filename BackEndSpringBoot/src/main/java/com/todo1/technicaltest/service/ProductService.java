package com.todo1.technicaltest.service;

import java.util.List;

import com.todo1.technicaltest.dto.ProductDto;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

public interface ProductService {
	List<ProductDto> getProductsList() throws Todo1CustomException;
	boolean saveProduct(ProductDto productDto) throws Todo1CustomException;
	boolean updateProduct(ProductDto productDto) throws Todo1CustomException;
    boolean deleteProduct(Long id) throws Todo1CustomException;
	ProductDto getProductById(Long id) throws Todo1CustomException;
	List<ProductDto> getAllByFilter(String productString, String withStock) throws Todo1CustomException;
}
