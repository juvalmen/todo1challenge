package com.todo1.technicaltest.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class KardexInDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1171420681305911004L;
	
	private Long idkardexin;	
	private Long sequential;	
	private String description;

	private Long idperson;
	private String personname;
	
	private Date creationdate;
	
	private List<KardexInDetailDto> kardexInDetails;

	public Long getIdkardexin() {
		return idkardexin;
	}

	public void setIdkardexin(Long idkardexin) {
		this.idkardexin = idkardexin;
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

	public List<KardexInDetailDto> getKardexInDetails() {
		return kardexInDetails;
	}

	public void setKardexInDetails(List<KardexInDetailDto> kardexInDetails) {
		this.kardexInDetails = kardexInDetails;
	}


	
}