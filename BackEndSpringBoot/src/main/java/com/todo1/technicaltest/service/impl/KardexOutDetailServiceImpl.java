package com.todo1.technicaltest.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.technicaltest.dto.KardexOutDetailDto;
import com.todo1.technicaltest.dto.ProductDto;
import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;
import com.todo1.technicaltest.model.KardexOut;
import com.todo1.technicaltest.model.KardexOutDetail;
import com.todo1.technicaltest.model.Product;
import com.todo1.technicaltest.repository.jpa.JpaKardexOutDetailRepository;
import com.todo1.technicaltest.repository.jpa.JpaProductRepository;
import com.todo1.technicaltest.service.KardexOutDetailService;
import com.todo1.technicaltest.service.ProductService;
import com.todo1.technicaltest.util.validations.KardexOutValidationMessages;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@Service
public class KardexOutDetailServiceImpl implements KardexOutDetailService {

	private JpaKardexOutDetailRepository jpaKardexOutDetailRepository;
	private DummyFieldMapper dummyFieldMapper;
	private JpaProductRepository jpaProductRepository;
	private ProductService productService;
	private static final Logger LOGGER = LogManager.getLogger(KardexOutDetailServiceImpl.class.getName());


	@Autowired
	public KardexOutDetailServiceImpl(JpaKardexOutDetailRepository jpaKardexOutDetailRepository,
			DummyFieldMapper dummyFieldMapper,ProductService productService,
			JpaProductRepository jpaProductRepository) {
		this.jpaKardexOutDetailRepository = jpaKardexOutDetailRepository;
		this.dummyFieldMapper = dummyFieldMapper;
		this.jpaProductRepository = jpaProductRepository;
		this.productService = productService;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveKardexOutDetail(KardexOut kardexOut, List<KardexOutDetailDto> kardexOutDetailsDto) throws Todo1CustomException {
		try {

			StringBuilder stringBuilder = new StringBuilder();
			
			for (KardexOutDetailDto kardexOutDetailDto : kardexOutDetailsDto) {
				ProductDto productValidate = productService.getProductById(kardexOutDetailDto.getIdproduct());
				if(productValidate.getStock().equals(0) || productValidate.getStock() < kardexOutDetailDto.getStockmovement()) {
					stringBuilder.append(KardexOutValidationMessages.STOCK_INSUFICIENTE);
					stringBuilder.append(productValidate.getName());
					throw new Todo1CustomException(stringBuilder.toString());
				}
			}

			for (KardexOutDetailDto kardexOutDetailDto : kardexOutDetailsDto) {
				if(kardexOutDetailDto.getStockmovement() < 0) {
					throw new Todo1CustomException(KardexOutValidationMessages.STOCK_NEGATIVO);
				}
			}			

			for (KardexOutDetailDto kardexOutDetailDto : kardexOutDetailsDto) {
				KardexOutDetail kardexOutDetail = dummyFieldMapper.map(kardexOutDetailDto, KardexOutDetail.class);
				Optional<Product> productExist = jpaProductRepository.findById(kardexOutDetailDto.getIdproduct());
				ProductDto productDto = productService.getProductById(kardexOutDetailDto.getIdproduct());
				if(productExist.isPresent()) {
					productDto.setIdProductCategory(productExist.get().getCategory().getIdcategory());
				}
				productDto.setStock(productDto.getStock()-kardexOutDetailDto.getStockmovement());
				productService.updateProduct(productDto);
				Product product = dummyFieldMapper.map(productDto, Product.class);				
				kardexOutDetail.setKardexOut(kardexOut);
				kardexOutDetail.setProduct(product);
				kardexOutDetail.setQuantity(kardexOutDetailDto.getStockmovement());
				jpaKardexOutDetailRepository.save(kardexOutDetail);
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new Todo1CustomException(e.getMessage());
		}
		return true;
	}
	
}
