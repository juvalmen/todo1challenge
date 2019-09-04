package com.todo1.technicaltest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kardexout")
public class KardexOut implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3443434222954354233L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idkardexout;	
	private Long sequential;	
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name="idperson", nullable = false)
	private Person person;
	
	private Date creationdate;

	public Long getIdkardexout() {
		return idkardexout;
	}

	public void setIdkardexout(Long idkardexout) {
		this.idkardexout = idkardexout;
	}

	public Long getSequential() {
		return sequential;
	}

	public void setSequential(Long sequential) {
		this.sequential = sequential;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	

}