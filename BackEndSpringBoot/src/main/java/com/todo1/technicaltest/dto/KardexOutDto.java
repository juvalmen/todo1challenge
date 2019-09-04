package com.todo1.technicaltest.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class KardexOutDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1171420681305911004L;
	
	private Long idkardexout;	
	private Long sequential;	
	private String description;

	private Long idperson;
	private String personname;
	
	private Date creationdate;
	
	private List<KardexOutDetailDto> kardexOutDetails;

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

	public Long getIdperson() {
		return idperson;
	}

	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public List<KardexOutDetailDto> getKardexOutDetails() {
		return kardexOutDetails;
	}

	public void setKardexOutDetails(List<KardexOutDetailDto> kardexOutDetails) {
		this.kardexOutDetails = kardexOutDetails;
	}

}