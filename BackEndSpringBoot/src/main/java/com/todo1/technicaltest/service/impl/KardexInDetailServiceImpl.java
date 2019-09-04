package com.todo1.technicaltest.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.technicaltest.dto.KardexInDetailDto;
import com.todo1.technicaltest.dto.ProductDto;
import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;
import com.todo1.technicaltest.model.KardexIn;
import com.todo1.technicaltest.model.KardexInDetail;
import com.todo1.technicaltest.model.Product;
import com.todo1.technicaltest.repository.jpa.JpaKardexInDetailRepository;
import com.todo1.technicaltest.repository.jpa.JpaProductRepository;
import com.todo1.technicaltest.service.KardexInDetailService;
import com.todo1.technicaltest.service.ProductService;
import com.todo1.technicaltest.util.validations.KardexInValidationMessages;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@Service
public class KardexInDetailServiceImpl implements KardexInDetailService {

	private JpaKardexInDetailRepository jpaKardexInDetailRepository;
	private DummyFieldMapper dummyFieldMapper;
	private JpaProductRepository jpaProductRepository;
	private ProductService productService;
	private static final Logger LOGGER = LogManager.getLogger(KardexInDetailServiceImpl.class.getName());


	@Autowired
	public KardexInDetailServiceImpl(JpaKardexInDetailRepository jpaKardexInDetailRepository,
			DummyFieldMapper dummyFieldMapper,ProductService productService,
			JpaProductRepository jpaProductRepository) {
		this.jpaKardexInDetailRepository = jpaKardexInDetailRepository;
		this.dummyFieldMapper = dummyFieldMapper;
		this.jpaProductRepository = jpaProductRepository;
		this.productService = productService;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveKardexInDetail(KardexIn kardexIn, List<KardexInDetailDto> kardexInDetailsDto) throws Todo1CustomException {
		try {
			
			if (kardexIn.getIdkardexin() == null) {
				throw new Todo1CustomException(KardexInValidationMessages.ID_KARDEX_NULO);
			}

			for (KardexInDetailDto kardexInDetailDto : kardexInDetailsDto) {
				if(kardexInDetailDto.getStockmovement() < 0) {
					throw new Todo1CustomException(KardexInValidationMessages.STOCK_NEGATIVO);
				}
			}			

			for (KardexInDetailDto kardexInDetailDto : kardexInDetailsDto) {
				KardexInDetail kardexInDetail = dummyFieldMapper.map(kardexInDetailDto, KardexInDetail.class);
				Optional<Product> productExist = jpaProductRepository.findById(kardexInDetailDto.getIdproduct());
				ProductDto productDto = productService.getProductById(kardexInDetailDto.getIdproduct());
				if(productExist.isPresent()) {
					productDto.setIdProductCategory(productExist.get().getCategory().getIdcategory());
				}
				productDto.setStock(productDto.getStock()+kardexInDetailDto.getStockmovement());
				productService.updateProduct(productDto);
				Product product = dummyFieldMapper.map(productDto, Product.class);				
				kardexInDetail.setKardexIn(kardexIn);
				kardexInDetail.setProduct(product);
				kardexInDetail.setQuantity(kardexInDetailDto.getStockmovement());
				jpaKardexInDetailRepository.save(kardexInDetail);
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new Todo1CustomException(e.getMessage());
		}
		return true;
	}
	
}
