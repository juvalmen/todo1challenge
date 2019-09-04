package com.todo1.technicaltest.service;

import java.util.List;

import com.todo1.technicaltest.dto.CategoryDto;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

public interface CategoryService {
	
	List<CategoryDto> getCategoryByKey(String name) throws Todo1CustomException;
	
	CategoryDto getCategoryById(Long id) throws Todo1CustomException;
	
}
