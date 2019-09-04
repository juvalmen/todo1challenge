package com.todo1.technicaltest.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.todo1.technicaltest.dto.CategoryDto;
import com.todo1.technicaltest.dto.PersonDto;
import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;
import com.todo1.technicaltest.model.Category;
import com.todo1.technicaltest.model.Person;
import com.todo1.technicaltest.repository.jdbc.JdbcPersonRepository;
import com.todo1.technicaltest.repository.jpa.JpaPersonRepository;
import com.todo1.technicaltest.service.CategoryService;
import com.todo1.technicaltest.service.PersonService;
import com.todo1.technicaltest.util.validations.PersonValidationMessages;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@Service
public class PersonServiceImpl implements PersonService {

	private JpaPersonRepository jpaPersonRepository;
	private JdbcPersonRepository jdbcPersonRepository;
	private DummyFieldMapper dummyFieldMapper;
	private CategoryService categoryService;
	private static final Logger LOGGER = LogManager.getLogger(PersonServiceImpl.class.getName());
	
	// Length fields
	private final int NAME_MAX_LENGTH = 80;
	private final int CUIT_MAX_LENGTH = 45;
	private final int LAST_NAME_MAX_LENGTH = 250;

	@Autowired
	public PersonServiceImpl(JpaPersonRepository jpaPersonRepository, JdbcPersonRepository jdbcPersonRepository,
			DummyFieldMapper dummyFieldMapper,CategoryService categoriaService) {
		this.jpaPersonRepository = jpaPersonRepository;
		this.jdbcPersonRepository = jdbcPersonRepository;
		this.dummyFieldMapper = dummyFieldMapper;
		this.categoryService = categoriaService;
	}
	
	@Override
	public boolean savePerson(PersonDto personDto) throws Todo1CustomException {
		try {

			validations(personDto);
			
			if (checkIdentificationNumber(personDto.getIdentificationnumber())) {
				throw new Todo1CustomException(PersonValidationMessages.CUIT_EXISTE);
			}
			
			CategoryDto categoryDto = categoryService.getCategoryById(personDto.getIdPersonCategory());
			
			Category categoria = dummyFieldMapper.map(categoryDto, Category.class);
			Person person = dummyFieldMapper.map(personDto, Person.class);
			person.setCategory(categoria);
			person.setCreationdate(new Date());
			
			jpaPersonRepository.save(person);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new Todo1CustomException(e.getMessage());
		}
		return true;
	}

	@Override
	public List<PersonDto> getPersonsList() throws Todo1CustomException {		
		List<PersonDto> persons = jdbcPersonRepository.getPersonsList();
		return persons;
	}
	
	@Override
	public List<PersonDto> getAllByFilter(String personString) throws Todo1CustomException {		
		List<PersonDto> persons = jdbcPersonRepository.getAllByFilter(personString);
		return persons;		
	}

	@Override
	public boolean updatePerson(PersonDto personDto) throws Todo1CustomException {
		try {

			if (personDto.getIdperson() == null) {
				throw new Todo1CustomException(PersonValidationMessages.ID_PERSONA_NO_EXISTE);
			}

			validations(personDto);
						
			PersonDto personValidate = this.getPersonById(personDto.getIdperson());
			if(personValidate == null || personValidate.getIdperson() == null) {
				throw new Todo1CustomException(PersonValidationMessages.ID_PERSONA_NO_EXISTE);
			}
			
			if(!personValidate.getIdentificationnumber().equalsIgnoreCase(personDto.getIdentificationnumber()) && (checkIdentificationNumber(personDto.getIdentificationnumber()))) {
			   throw new Todo1CustomException(PersonValidationMessages.CUIT_EXISTE);
			}

			CategoryDto categoryDto = categoryService.getCategoryById(personDto.getIdPersonCategory());
			
			Category category = dummyFieldMapper.map(categoryDto, Category.class);
			Person personUdate = dummyFieldMapper.map(personDto, Person.class);
			personUdate.setCategory(category);
			personUdate.setModificationdate(new Date());
	
			jpaPersonRepository.save(personUdate);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new Todo1CustomException(e.getMessage());
		}
		return true;
	}

	@Override
	public boolean deletePerson(Long id) throws Todo1CustomException {
		try {
			getProductsInKardexIn(id);
			jpaPersonRepository.deleteById(id);
		} catch (Exception e) {
			throw new Todo1CustomException(e.getMessage());
		}
		return true;
	}

	@Override
	public PersonDto getPersonById(Long id) throws Todo1CustomException {
		try {
			Optional<Person> person = jpaPersonRepository.findById(id);
			if(person.isPresent()) {
				return dummyFieldMapper.map(person.get(), PersonDto.class);
			}
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new Todo1CustomException(e);
		}
	}

	@Override
	public boolean checkIdentificationNumber(String identificationNumber) throws Todo1CustomException {
		try {
			boolean cuitExist = false;
			List<PersonDto> person = jdbcPersonRepository.getPersonByIdentification(identificationNumber);
			if (person != null && !person.isEmpty()) {
				cuitExist = true;
			}
			return cuitExist;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new Todo1CustomException(e);
		}
	}
	
	/**
	 * 
	 * @param idProduct
	 * @throws Todo1CustomException
	 */
	public void getProductsInKardexIn(Long idPerson) throws Todo1CustomException {		
		Long personInMovement = jdbcPersonRepository.getPersonsInKardexIn(idPerson);
		if(personInMovement != null && personInMovement > 0) {
			throw new Todo1CustomException(PersonValidationMessages.ID_PERSONA_EN_MOVIMIENTO);
		}		
	}
	
	/**
	 * 
	 * @param personDto
	 * @throws Todo1CustomException
	 */
	public void validations(PersonDto personDto) throws Todo1CustomException {
		
		if (personDto.getIdentificationnumber() == null || personDto.getIdentificationnumber().isEmpty()) {
			throw new Todo1CustomException(PersonValidationMessages.CUIT_NULO);
		}
		
		if (personDto.getIdentificationnumber().length() > CUIT_MAX_LENGTH) {
			throw new Todo1CustomException(PersonValidationMessages.CUIT_LONGITUD);
		}
		
		if (personDto.getIdPersonCategory() == null) {
			throw new Todo1CustomException(PersonValidationMessages.CATEGORIA_PERSONA_NULO);
		}

		if (Strings.isNullOrEmpty(personDto.getName())) {
			throw new Todo1CustomException(PersonValidationMessages.NOMBRE_NULO);
		}
		if (personDto.getName().length() > NAME_MAX_LENGTH) {
			throw new Todo1CustomException(PersonValidationMessages.NOMBRE_LONGITUD);
		}
		if (Strings.isNullOrEmpty(personDto.getLastname())) {
			throw new Todo1CustomException(PersonValidationMessages.APELLIDO_NULO);
		}
		if (personDto.getLastname().length() > LAST_NAME_MAX_LENGTH) {
			throw new Todo1CustomException(PersonValidationMessages.APELLIDO_LONGITUD);
		}
	
	}

}
