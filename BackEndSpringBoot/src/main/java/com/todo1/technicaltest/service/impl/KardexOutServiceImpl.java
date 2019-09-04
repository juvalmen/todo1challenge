package com.todo1.technicaltest.service.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.todo1.technicaltest.dto.KardexOutDto;
import com.todo1.technicaltest.dto.PersonDto;
import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;
import com.todo1.technicaltest.model.KardexOut;
import com.todo1.technicaltest.model.Person;
import com.todo1.technicaltest.repository.jdbc.JdbcKardexOutRepository;
import com.todo1.technicaltest.repository.jpa.JpaKardexOutRepository;
import com.todo1.technicaltest.service.KardexOutDetailService;
import com.todo1.technicaltest.service.KardexOutService;
import com.todo1.technicaltest.service.PersonService;
import com.todo1.technicaltest.util.validations.KardexOutValidationMessages;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@Service
public class KardexOutServiceImpl implements KardexOutService {

	private JpaKardexOutRepository jpaKardexOutRepository;
	private JdbcKardexOutRepository jdbcKardexOutRepository;
	private DummyFieldMapper dummyFieldMapper;
	private KardexOutDetailService kardexOutDetailService;
	private PersonService personService;
	private static final Logger LOGGER = LogManager.getLogger(KardexOutServiceImpl.class.getName());
	
	// Length fields
	private final int DESCRIPTION_MAX_LENGTH = 250;

	@Autowired
	public KardexOutServiceImpl(JpaKardexOutRepository jpaKardexOutRepository,
			DummyFieldMapper dummyFieldMapper,KardexOutDetailService kardexOutDetailService,JdbcKardexOutRepository jdbcKardexOutRepository,
			PersonService personService) {
		this.jpaKardexOutRepository = jpaKardexOutRepository;
		this.jdbcKardexOutRepository = jdbcKardexOutRepository;
		this.dummyFieldMapper = dummyFieldMapper;
		this.personService = personService;
		this.kardexOutDetailService = kardexOutDetailService;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveKardexOut(KardexOutDto kardexOutDto) throws Todo1CustomException {
		try {

			if (kardexOutDto.getSequential() == null) {
				throw new Todo1CustomException(KardexOutValidationMessages.SECUENCIAL_NULO);
			}
			if (Strings.isNullOrEmpty(kardexOutDto.getDescription())) {
				throw new Todo1CustomException(KardexOutValidationMessages.DESCRIPCION_NULO);
			}
			if (kardexOutDto.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
				throw new Todo1CustomException(KardexOutValidationMessages.DESCRIPCION_LONGITUD);
			}
			if (kardexOutDto.getIdperson() == null) {
				throw new Todo1CustomException(KardexOutValidationMessages.PERSONA_NULL);
			}			
			if(kardexOutDto.getKardexOutDetails() == null || kardexOutDto.getKardexOutDetails().isEmpty()) {
				throw new Todo1CustomException(KardexOutValidationMessages.ENTRADA_SIN_PRODUCTOS);
			}
			
			PersonDto personDto = personService.getPersonById(kardexOutDto.getIdperson());
			Person person  = dummyFieldMapper.map(personDto, Person.class);		
			
			KardexOut kardexOut = dummyFieldMapper.map(kardexOutDto, KardexOut.class);
			kardexOut.setSequential(getLastSequence());
			kardexOut.setPerson(person);
			kardexOut.setCreationdate(new Date());
			jpaKardexOutRepository.save(kardexOut);
			
			kardexOutDetailService.saveKardexOutDetail(kardexOut, kardexOutDto.getKardexOutDetails());
				
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new Todo1CustomException(e.getMessage());
		}
		return true;
	}

	@Override
	public Long getLastSequence() {
		try {
			Long lastSequence = jdbcKardexOutRepository.getLastSequence();
			
			return (lastSequence == null ? 0 : lastSequence)+1;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return 1L;
	}

}
