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
import com.todo1.technicaltest.dto.ProductDto;
import com.todo1.technicaltest.service.ProductService;
import com.todo1.technicaltest.util.SystemMessage;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
@Api( value = "Products service.", tags = { "Products" })
public class ProductController {
	
	private ProductService  productService;
	private static final Logger LOGGER = LogManager.getLogger(ProductController.class.getName());
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}	
	
	@ApiOperation(value = "Create a product.", response = BaseResponseDto.class,tags = { "Product" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Product created.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Can't create product. Validation exception", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
	@PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> saveProduct(@RequestBody(required = true) ProductDto productDto) {
		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		try {
			productService.saveProduct(productDto);
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
	
	@ApiOperation(value = "Update a product.", response = BaseResponseDto.class,tags = { "Product" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Product updated.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Can't update a product. Validation exception", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> updateProduct(@RequestBody(required = true) ProductDto productDto) {
		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		try {
			productService.updateProduct(productDto);
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
	
	@ApiOperation(value = "Get all products.", response = BaseResponseDto.class,tags = { "Product" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Sucesfully search.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Validation exception or searching event", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> getAllProduct() {
		
		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		try {
			List<ProductDto> products = productService.getProductsList();
			response.setResponseMessage(SystemMessage.STATUS_OK);
			response.setResponseBody(products);
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
	
	@ApiOperation(value = "Get all products by filter.", response = BaseResponseDto.class,tags = { "Product" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Sucesfully search.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Validation exception or searching event", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
	@GetMapping(value = "/getAllByFilter/{productString}/{withStock}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> getAllByFilter(@PathVariable("productString") String productString
			,@PathVariable("withStock") String withStock) {

		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		try {
			List<ProductDto> citys = productService.getAllByFilter(productString, withStock);
			response.setResponseMessage(SystemMessage.STATUS_OK);
			response.setResponseBody(citys);
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
	
	@ApiOperation(value = "Delete a product", response = BaseResponseDto.class,tags = { "Product" })
	@ApiResponses(value = { 
	@ApiResponse(code = 200, message = "Deleted record.",response = BaseResponseDto.class),
	@ApiResponse(code = 400, message = "Validation exception", response = BaseResponseDto.class),
	@ApiResponse(code = 500, message = "Internal server error.", response = BaseResponseDto.class), 
	})
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDto> deleteProduct(@PathVariable long id) {
		BaseResponseDto response = new BaseResponseDto();
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = null;
		
		try {
			productService.deleteProduct(id);
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
