package com.todo1.technicaltest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo1.technicaltest.dto.CategoryDto;
import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;
import com.todo1.technicaltest.model.Category;
import com.todo1.technicaltest.repository.jdbc.JdbcCategoryRepository;
import com.todo1.technicaltest.repository.jpa.JpaCategoryRepository;
import com.todo1.technicaltest.service.CategoryService;
import com.todo1.technicaltest.util.validations.CategoryValidationMessages;
import com.todo1.technicaltest.util.validations.PersonValidationMessages;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

/**
 * 
 * @author Julian Valencia
 * 20/05/2019
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	private JdbcCategoryRepository jdbcCategoryRepository;
	private JpaCategoryRepository jpaCategoryRepository;
	private DummyFieldMapper dummyFieldMapper;

	/**
	 * 
	 * @author Julian Valencia
	 * 20/05/2019
	 * @param jdbcTypeRepository
	 * @param jpaCategoryRepository
	 * @param jdbcCategoryRepository
	 * @param dummyFieldMapper
	 */
	@Autowired
	public CategoryServiceImpl(JdbcCategoryRepository jdbcTypeRepository, 
			JpaCategoryRepository jpaCategoryRepository,
			JdbcCategoryRepository jdbcCategoryRepository,
			DummyFieldMapper dummyFieldMapper) {
		this.jdbcCategoryRepository = jdbcCategoryRepository;		
		this.jpaCategoryRepository = jpaCategoryRepository;		
		this.dummyFieldMapper = dummyFieldMapper;		
	}

	/**
	 * 
	 */
	@Override
	public List<CategoryDto> getCategoryByKey(String key) throws Todo1CustomException {
		if (key == null || !key.isEmpty()) {
			throw new Todo1CustomException(CategoryValidationMessages.KEY_NO_ENCONTRADA);
		}
		List<Category> list = jdbcCategoryRepository.getCategoryByKey(key);
		List<CategoryDto> newList = new ArrayList<CategoryDto>();
		list.forEach(category->{
			newList.add(dummyFieldMapper.map(category, CategoryDto.class));
		});
		return newList;
	}

	/**
	 * 
	 */
	@Override
	public CategoryDto getCategoryById(Long id) throws Todo1CustomException {
		Category category = jpaCategoryRepository.getOne(id);
		if(category.getIdcategory() == null) {
		   throw new Todo1CustomException(PersonValidationMessages.CATEGORIA_PERSONA_NO_EXISTE);
		}
		return dummyFieldMapper.map(category, CategoryDto.class);
				
	}

}
