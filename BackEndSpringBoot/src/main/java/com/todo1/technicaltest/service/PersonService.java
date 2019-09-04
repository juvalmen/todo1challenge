package com.todo1.technicaltest.service;

import java.util.List;

import com.todo1.technicaltest.dto.PersonDto;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

public interface PersonService {
	List<PersonDto> getPersonsList() throws Todo1CustomException;
	boolean savePerson(PersonDto personDto) throws Todo1CustomException;
	boolean updatePerson(PersonDto personDto) throws Todo1CustomException;
    boolean deletePerson(Long id) throws Todo1CustomException;
	PersonDto getPersonById(Long id) throws Todo1CustomException;
	boolean checkIdentificationNumber(String identificationNumber) throws Todo1CustomException;
	List<PersonDto> getAllByFilter(String personString) throws Todo1CustomException;
}
