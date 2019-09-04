package com.todo1.technicaltest.repository.jdbc;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcKardexInRepository {

	private JdbcTemplate jdbcTemplate;
	EntityManager entityManager;

	@Autowired
	public JdbcKardexInRepository(JdbcTemplate jdbcTemplate,
			EntityManager entityManager) {
		this.jdbcTemplate = jdbcTemplate;
		this.entityManager = entityManager;
	}
	
	/**
	 * 
	 * @author Julian Valencia
	 * 31/08/2019
	 */
	public Long getLastSequence() {
				
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT  ")
		   .append(" MAX(karin.sequential) sequential")
		   .append(" FROM kardexin karin ");
		
		Long sequential = (Long) jdbcTemplate.queryForObject(
				sql.toString(), Long.class);
		
		return sequential;
	}
	
}
