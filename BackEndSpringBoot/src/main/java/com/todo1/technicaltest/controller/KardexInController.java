package com.todo1.technicaltest.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo1.technicaltest.dto.BaseResponseDto;
import com.todo1.technicaltest.dto.KardexInDto;
import com.todo1.technicaltest.service.KardexInService;
import com.todo1.technicaltest.util.SystemMessage;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/kardexin")
@Api( value = "Products entrys service.", tags = { "Products entrys" })
public class KardexInController {
	
	private KardexInService kardexInService;
	private static final Logger LOGGER = LogManager.getLogger(KardexInController.class.getName());
	
	public KardexInController(KardexInService kardexInService) {
		this.kardexInService = kardexInService;
	}	
	
	@ApiOperation(value = "Create a entry.", response = BaseResponseDto.class,tags = { "Products entrys" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Product entry created.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Can't create product entry. Validation exception", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
	@PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> saveProduct(@RequestBody(required = true) KardexInDto kardexInDto) {
		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		try {
			kardexInService.saveKardexIn(kardexInDto);
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
	
	@ApiOperation(value = "Get secuence.", response = BaseResponseDto.class,tags = { "KardexIn Secuence" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Secuence OK.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Validation exception", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
	@GetMapping(value = "/secuence",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> getLastSequence() {
		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		try {
			response.setResponseBody(kardexInService.getLastSequence());
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

}
