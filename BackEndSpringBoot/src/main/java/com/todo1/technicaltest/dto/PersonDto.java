package com.todo1.technicaltest.dto;

import java.io.Serializable;
import java.util.Date;

public class PersonDto implements Serializable {

	private static final long serialVersionUID = 34343534604695006L;
	
	private Long idperson;
	private String name;
	private String lastname;
	private String identificationnumber;	
	private Long idPersonCategory;
	private String valuePersonCategory;
    private String descriptionPersonCategory;
    private Date creationdate;
    private Date modificationdate;
	public Long getIdperson() {
		return idperson;
	}
	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getIdentificationnumber() {
		return identificationnumber;
	}
	public void setIdentificationnumber(String identificationnumber) {
		this.identificationnumber = identificationnumber;
	}
	public Long getIdPersonCategory() {
		return idPersonCategory;
	}
	public void setIdPersonCategory(Long idPersonCategory) {
		this.idPersonCategory = idPersonCategory;
	}
	public String getValuePersonCategory() {
		return valuePersonCategory;
	}
	public void setValuePersonCategory(String valuePersonCategory) {
		this.valuePersonCategory = valuePersonCategory;
	}
	public String getDescriptionPersonCategory() {
		return descriptionPersonCategory;
	}
	public void setDescriptionPersonCategory(String descriptionPersonCategory) {
		this.descriptionPersonCategory = descriptionPersonCategory;
	}
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	public Date getModificationdate() {
		return modificationdate;
	}
	public void setModificationdate(Date modificationdate) {
		this.modificationdate = modificationdate;
	}
    
	
    
      
}