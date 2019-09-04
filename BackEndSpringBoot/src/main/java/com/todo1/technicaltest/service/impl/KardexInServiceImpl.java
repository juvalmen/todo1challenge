package com.todo1.technicaltest.service.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.todo1.technicaltest.dto.KardexInDto;
import com.todo1.technicaltest.dto.PersonDto;
import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;
import com.todo1.technicaltest.model.KardexIn;
import com.todo1.technicaltest.model.Person;
import com.todo1.technicaltest.repository.jdbc.JdbcKardexInRepository;
import com.todo1.technicaltest.repository.jpa.JpaKardexInRepository;
import com.todo1.technicaltest.service.KardexInDetailService;
import com.todo1.technicaltest.service.KardexInService;
import com.todo1.technicaltest.service.PersonService;
import com.todo1.technicaltest.util.validations.KardexInValidationMessages;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@Service
public class KardexInServiceImpl implements KardexInService {

	private JpaKardexInRepository jpaKardexInRepository;
	private JdbcKardexInRepository jdbcKardexInRepository;
	private DummyFieldMapper dummyFieldMapper;
	private KardexInDetailService kardexInDetailService;
	private PersonService personService;
	private static final Logger LOGGER = LogManager.getLogger(KardexInServiceImpl.class.getName());
	
	// Length fields
	private final int DESCRIPTION_MAX_LENGTH = 250;

	@Autowired
	public KardexInServiceImpl(JpaKardexInRepository jpaKardexInRepository,
			DummyFieldMapper dummyFieldMapper,KardexInDetailService kardexInDetailService,JdbcKardexInRepository jdbcKardexInRepository,
			PersonService personService) {
		this.jpaKardexInRepository = jpaKardexInRepository;
		this.jdbcKardexInRepository = jdbcKardexInRepository;
		this.dummyFieldMapper = dummyFieldMapper;
		this.personService = personService;
		this.kardexInDetailService = kardexInDetailService;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveKardexIn(KardexInDto kardexInDto) throws Todo1CustomException {
		try {

			if (kardexInDto.getSequential() == null) {
				throw new Todo1CustomException(KardexInValidationMessages.SECUENCIAL_NULO);
			}
			if (Strings.isNullOrEmpty(kardexInDto.getDescription())) {
				throw new Todo1CustomException(KardexInValidationMessages.DESCRIPCION_NULO);
			}
			if (kardexInDto.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
				throw new Todo1CustomException(KardexInValidationMessages.DESCRIPCION_LONGITUD);
			}
			if (kardexInDto.getIdperson() == null) {
				throw new Todo1CustomException(KardexInValidationMessages.PERSONA_NULL);
			}			
			if(kardexInDto.getKardexInDetails() == null || kardexInDto.getKardexInDetails().isEmpty()) {
				throw new Todo1CustomException(KardexInValidationMessages.ENTRADA_SIN_PRODUCTOS);
			}
			
			PersonDto personDto = personService.getPersonById(kardexInDto.getIdperson());
			Person person  = dummyFieldMapper.map(personDto, Person.class);			
			
			KardexIn kardexIn = dummyFieldMapper.map(kardexInDto, KardexIn.class);
			kardexIn.setSequential(getLastSequence());
			kardexIn.setPerson(person);
			kardexIn.setCreationdate(new Date());
			jpaKardexInRepository.save(kardexIn);
			
			kardexInDetailService.saveKardexInDetail(kardexIn, kardexInDto.getKardexInDetails());
				
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new Todo1CustomException(e.getMessage());
		}
		return true;
	}
	

	@Override
	public Long getLastSequence() {
		try {
			Long lastSequence = jdbcKardexInRepository.getLastSequence();
			
			return (lastSequence == null ? 0 : lastSequence)+1;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return 1L;
	}

}
