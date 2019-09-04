package com.todo1.technicaltest.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo1.technicaltest.dto.BaseResponseDto;
import com.todo1.technicaltest.dto.PersonDto;
import com.todo1.technicaltest.service.PersonService;
import com.todo1.technicaltest.util.SystemMessage;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/person")
@Api( value = "Persons service.", tags = { "Persons" })
public class PersonController {
	
	private PersonService  personService;
	private static final Logger LOGGER = LogManager.getLogger(PersonController.class.getName());
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}	
	
	@ApiOperation(value = "Create a person.", response = BaseResponseDto.class,tags = { "Person" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Person created.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Can't create person. Validation exception", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
	@PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> savePerson(@RequestBody(required = true) PersonDto personDto) {
		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		try {
			personService.savePerson(personDto);
			response.setResponseMessage(SystemMessage.STATUS_OK);
			httpStatus = HttpStatus.OK;
		} catch (Todo1CustomException e) {
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setResponseMessage(e.getLocalizedMessage());
			httpStatus = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponseMessage(SystemMessage.UNCONTROLED_ERROR);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error(e.getMessage());
		}
		response.setStatusCode(httpStatus.value());
		return new ResponseEntity<>(response, headers, httpStatus);
	}
	
	@ApiOperation(value = "Update a person.", response = BaseResponseDto.class,tags = { "Person" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Person updated.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Can't update a person. Validation exception", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> updatePerson(@RequestBody(required = true) PersonDto personDto) {
		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		try {
			personService.updatePerson(personDto);
			response.setResponseMessage(SystemMessage.STATUS_OK);
			httpStatus = HttpStatus.OK;
		} catch (Todo1CustomException e) {
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setResponseMessage(e.getLocalizedMessage());
			httpStatus = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponseMessage(SystemMessage.UNCONTROLED_ERROR);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error(e.getMessage());
		}
		response.setStatusCode(httpStatus.value());
		return new ResponseEntity<>(response, headers, httpStatus);
	}
	
	@ApiOperation(value = "Get all persons.", response = BaseResponseDto.class,tags = { "Person" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Sucesfully search.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Validation exception or searching event", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> getAllPerson() {
		
		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		try {
			List<PersonDto> persons = personService.getPersonsList();
			response.setResponseMessage(SystemMessage.STATUS_OK);
			response.setResponseBody(persons);
			httpStatus = HttpStatus.OK;
		} catch (Todo1CustomException e) {
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setResponseMessage(e.getLocalizedMessage());
			httpStatus = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponseMessage(SystemMessage.UNCONTROLED_ERROR);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error(e.getMessage());
		}
		response.setStatusCode(httpStatus.value());
		return new ResponseEntity<>(response,headers,httpStatus);
	}
	
	@ApiOperation(value = "Get all persons by filter.", response = BaseResponseDto.class,tags = { "Person" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Sucesfully search.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Validation exception or searching event", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
	@GetMapping(value = "/getAllByFilter/{personString}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> getAllByFilter(@PathVariable("personString") String personString) {

		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		try {
			List<PersonDto> persons = personService.getAllByFilter(personString);
			response.setResponseMessage(SystemMessage.STATUS_OK);
			response.setResponseBody(persons);
			httpStatus = HttpStatus.OK;
		} catch (Todo1CustomException e) {
			response.setResponseMessage(e.getLocalizedMessage());
			httpStatus = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			response.setResponseMessage(SystemMessage.UNCONTROLED_ERROR);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.setStatusCode(httpStatus.value());
		return new ResponseEntity<>(response, headers, httpStatus);
	}
	
	@ApiOperation(value = "Delete a person", response = BaseResponseDto.class,tags = { "Person" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Deleted record.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Validation exception", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> deletePerson(@PathVariable long id) {
		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		
		try {
			personService.deletePerson(id);
			response.setResponseMessage(SystemMessage.STATUS_OK);
			httpStatus = HttpStatus.OK;
		} catch (Todo1CustomException e) {
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setResponseMessage(e.getLocalizedMessage());
			httpStatus = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponseMessage(SystemMessage.UNCONTROLED_ERROR);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error(e.getMessage());
		}
		response.setStatusCode(httpStatus.value());
		return new ResponseEntity<>(response,headers,httpStatus);
	} 

}
