package com.todo1.technicaltest.repository.jdbc;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.todo1.technicaltest.dto.PersonDto;

@Repository
public class JdbcPersonRepository {

	private JdbcTemplate jdbcTemplate;
	EntityManager entityManager;

	@Autowired
	public JdbcPersonRepository(JdbcTemplate jdbcTemplate,
			EntityManager entityManager) {
		this.jdbcTemplate = jdbcTemplate;
		this.entityManager = entityManager;
	}
		
	/**
	 * 
	 * @author Julian Valencia
	 * 31/08/2019
	 */
	public List<PersonDto> getPersonByIdentification(String identificationNumber) {
		
		return jdbcTemplate.query("SELECT * from person WHERE identificationnumber = ?", 
				new Object[] { identificationNumber },
				new BeanPropertyRowMapper<PersonDto>(PersonDto.class));
	}
	
	
	/**
	 * 
	 * @author Julian Valencia
	 * 31/08/2019
	 */
	public List<PersonDto> getPersonsList() {
				
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT per.idperson ")
		   .append(" ,per.name ")
		   .append(" ,per.lastname ")
		   .append(" ,per.identificationnumber ")		  
		   .append(" ,per.creationdate ")		  
		   .append(" ,per.modificationdate ")		  
		   .append(" ,cat.idcategory as idPersonCategory ")
		   .append(" ,cat.value as valuePersonCategory ")
		   .append(" ,cat.name as descriptionPersonCategory ")
		   .append(" FROM person per ")
		   .append(" INNER JOIN category cat ON cat.idcategory = per.personcategory; ");
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<PersonDto>(PersonDto.class));
	}
	
	/**
	 * 
	 * @param cityString
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<PersonDto> getAllByFilter(String personString) throws EmptyResultDataAccessException {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT per.idperson ")
		   .append(" ,per.name ")
		   .append(" ,per.lastname ")
		   .append(" ,per.identificationnumber ")		  
		   .append(" ,cat.idcategory as idpersoncategory ")
		   .append(" ,cat.value as valuepersoncategory ")
		   .append(" ,cat.name as descriptionpersoncategory ")
		   .append(" FROM person per ")
		   .append(" INNER JOIN category cat ON cat.idcategory = per.personcategory ")
		   .append(" WHERE per.name like '%").append(personString).append("%'")
		   .append(" OR per.lastname like '%").append(personString).append("%'");
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<PersonDto>(PersonDto.class));
	}
	
	/**
	 * 
	 * @author Julian Valencia
	 * 31/08/2019
	 */
	public Long getPersonsInKardexIn(Long idPerson) {
				
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT  ")
		   .append(" count(1) counter")
		   .append(" FROM kardexin karin ")
		   .append(" WHERE karin.idperson = ").append(idPerson);
		
		Long sequential = (Long) jdbcTemplate.queryForObject(
				sql.toString(), Long.class);
		
		return sequential;
	}
	
}
