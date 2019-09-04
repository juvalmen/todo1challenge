package com.todo1.technicaltest.repository.jdbc;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.todo1.technicaltest.dto.ProductDto;

@Repository
public class JdbcProductRepository {

	private JdbcTemplate jdbcTemplate;
	EntityManager entityManager;

	@Autowired
	public JdbcProductRepository(JdbcTemplate jdbcTemplate,
			EntityManager entityManager) {
		this.jdbcTemplate = jdbcTemplate;
		this.entityManager = entityManager;
	}
	
	/**
	 * 
	 * @author Julian Valencia
	 * 31/08/2019
	 */
	public List<ProductDto> getProductsList() {
				
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT per.idproduct ")
		   .append(" ,per.name ")
		   .append(" ,per.description ")
		   .append(" ,per.price ")		  
		   .append(" ,per.stock ")		  
		   .append(" ,per.creationdate ")		  
		   .append(" ,per.modificationdate ")		  
		   .append(" ,cat.idcategory as idproductcategory ")
		   .append(" ,cat.value as valueproductcategory ")
		   .append(" ,cat.name as descriptionproductcategory ")
		   .append(" FROM product per ")
		   .append(" INNER JOIN category cat ON cat.idcategory = per.productcategory; ");
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ProductDto>(ProductDto.class));
	}
	
	/**
	 * 
	 * @param personString
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<ProductDto> getAllByFilter(String personString, String withStock) throws EmptyResultDataAccessException {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT per.idproduct ")
		   .append(" ,per.name ")
		   .append(" ,per.description ")
		   .append(" ,per.price ")		  
		   .append(" ,per.stock ")		  
		   .append(" ,per.creationdate ")		  
		   .append(" ,per.modificationdate ")		  
		   .append(" ,cat.idcategory as idproductcategory ")
		   .append(" ,cat.value as valueproductcategory ")
		   .append(" ,cat.name as descriptionproductcategory ")
		   .append(" FROM product per ")
		   .append(" INNER JOIN category cat ON cat.idcategory = per.productcategory ")
		   .append(" WHERE per.name like '%").append(personString).append("%'");
		
		if(withStock.equals("1")) {
			sql.append(" AND per.stock > 0 ");
		}
		
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ProductDto>(ProductDto.class));
	}
	
	/**
	 * 
	 * @author Julian Valencia
	 * 31/08/2019
	 */
	public Long getProductsInKardexIn(Long idProduct) {
				
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT  ")
		   .append(" count(1) counter")
		   .append(" FROM kardexindetail karin ")
		   .append(" WHERE karin.idproduct = ").append(idProduct);
		
		Long sequential = (Long) jdbcTemplate.queryForObject(
				sql.toString(), Long.class);
		
		return sequential;
	}
	
}
