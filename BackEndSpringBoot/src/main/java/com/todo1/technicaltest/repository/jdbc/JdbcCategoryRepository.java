package com.todo1.technicaltest.repository.jdbc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.todo1.technicaltest.dto.CategoryDto;
import com.todo1.technicaltest.model.Category;

/**
 * 
 * @author Julian Valencia
 * 31/08/2019
 */
@Repository
public class JdbcCategoryRepository {

	private JdbcTemplate jdbcTemplate;
	EntityManager entityManager;

	@Autowired
	public JdbcCategoryRepository(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
		this.jdbcTemplate = jdbcTemplate;
		this.entityManager = entityManager;
	}

	/**
	 * 
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<Category> getCategoriaEntityList() throws EmptyResultDataAccessException {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Category> q = cb.createQuery(Category.class);
		Root<Category> c = q.from(Category.class);
		q.select(c);

		return entityManager.createQuery(q).getResultList();

	}

	/**
	 * 
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<CategoryDto> getCategoriasList() throws EmptyResultDataAccessException {
		return jdbcTemplate.query("SELECT idcategory, name, value FROM category cat", 
				new BeanPropertyRowMapper<CategoryDto>(CategoryDto.class));
	}

	/**
	 * 
	 * @param key
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<Category> getCategoryByKey(String key) throws EmptyResultDataAccessException {
		

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();

			CriteriaQuery<Category> q = cb.createQuery(Category.class);
			Root<Category> c = q.from(Category.class);
			q.select(c).where(cb.equal(c.get("key"), key));

			return entityManager.createQuery(q).getResultList();

	}

}
