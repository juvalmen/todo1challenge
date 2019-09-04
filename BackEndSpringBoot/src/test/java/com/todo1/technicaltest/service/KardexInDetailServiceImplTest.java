package com.todo1.technicaltest.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.technicaltest.dto.CategoryDto;
import com.todo1.technicaltest.dto.KardexInDetailDto;
import com.todo1.technicaltest.dto.KardexInDto;
import com.todo1.technicaltest.dto.PersonDto;
import com.todo1.technicaltest.dto.ProductDto;
import com.todo1.technicaltest.enums.CategoryEnum;
import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;
import com.todo1.technicaltest.model.Category;
import com.todo1.technicaltest.model.KardexIn;
import com.todo1.technicaltest.model.KardexInDetail;
import com.todo1.technicaltest.model.Person;
import com.todo1.technicaltest.model.Product;
import com.todo1.technicaltest.repository.jdbc.JdbcKardexInRepository;
import com.todo1.technicaltest.repository.jdbc.JdbcProductRepository;
import com.todo1.technicaltest.repository.jpa.JpaProductRepository;
import com.todo1.technicaltest.service.impl.CategoryServiceImpl;
import com.todo1.technicaltest.service.impl.KardexInDetailServiceImpl;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
@ActiveProfiles("test") 
public class KardexInDetailServiceImplTest {

	@Autowired
	@InjectMocks
	private KardexInDetailServiceImpl kardexInDetailServiceImpl;

	@Mock
	private JdbcProductRepository jdbcProductRepository;

	@Mock
	private JpaProductRepository jpaProductRepository;

	@Mock
	private CategoryServiceImpl categoryServiceImpl;

	@Mock
	private DummyFieldMapper dummyFieldMapper;

	@Mock
	private ProductService productService;
	
	@Mock
	private PersonService personService;
	
	@Mock
	private KardexInDetailService kardexInDetailService;

	@Mock
	private JdbcKardexInRepository jdbcKardexInRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	public KardexInDto getKardexInDto() {
		KardexInDto kardexInDto = new KardexInDto();
		kardexInDto.setIdkardexin(1L);
		kardexInDto.setDescription("Description");
		kardexInDto.setIdperson(getPerson().getIdperson());
		kardexInDto.setSequential(1L);
		kardexInDto.setKardexInDetails(getKardexInListDto());
		return kardexInDto;
	}
	
	public KardexIn getKardexIn() {
		KardexIn kardexIn = new KardexIn();
		kardexIn.setIdkardexin(1L);
		kardexIn.setDescription("Description");
		kardexIn.setPerson(getPerson());
		kardexIn.setSequential(1L);
		return kardexIn;
	}
	
	public KardexInDetail getKardexInDetail() {
		KardexInDetail kardexInDetail = new KardexInDetail();
		kardexInDetail.setIdkardexindetail(null);
		kardexInDetail.setKardexIn(getKardexIn());
		kardexInDetail.setProduct(getProduct());
		kardexInDetail.setQuantity(4L);
		return kardexInDetail;
	}

	public KardexInDetailDto getKardexInDetailDto() {
		KardexInDetailDto kardexInDetail = new KardexInDetailDto();
		kardexInDetail.setIdkardexindetail(null);
		kardexInDetail.setIdkardexin(1L);
		kardexInDetail.setIdproduct(1L);
		kardexInDetail.setQuantity(4L);
		return kardexInDetail;
	}

	public List<KardexInDetailDto> getKardexInListDto() {
		List<KardexInDetailDto> detailDtos = new ArrayList<KardexInDetailDto>(); 
		KardexInDetailDto kardexInDetailDto = new KardexInDetailDto();
		kardexInDetailDto.setIdkardexin(1L);
		kardexInDetailDto.setIdkardexindetail(null);
		kardexInDetailDto.setStockmovement(2L);
		kardexInDetailDto.setIdproduct(getProduct().getIdproduct());
		detailDtos.add(kardexInDetailDto);
		return detailDtos;
	}

	/**
	 * 
	 */
	public ProductDto getProductDto() {
		ProductDto productDto = new ProductDto();
		productDto.setName("Camiseta Anime");
		productDto.setDescription("Camiseta Anime Dragon Ball Z");
		productDto.setPrice(0.0);
		productDto.setStock(0L);
		productDto.setIdProductCategory(1L);
		return productDto;
	}

	/**
	 * 
	 */
	public Person getPerson() {
		Person person = new Person();
		person.setIdperson(1L);
		person.setName("Julian");
		person.setLastname("Valencia");
		person.setIdentificationnumber("123456");
		person.setCategory(getCategoryPerson());
		return person;
	}

	public Category getCategoryPerson() {
		Category category = new Category();
		category.setIdcategory(1L);
		category.setName(CategoryEnum.VENDEDOR.getType());
		category.setValue("1");
		category.setKey("categoryperson");
		return category;
	}

	public PersonDto getPersonDto() {
		PersonDto personDto = new PersonDto();
		personDto.setName("Julian");
		personDto.setLastname("Valencia");
		personDto.setIdentificationnumber("123456");
		personDto.setIdPersonCategory(1L);
		return personDto;
	}

	/**
	 * 
	 */
	public Product getProduct() {
		Product product = new Product();
		product.setName("Camiseta Anime");
		product.setDescription("Camiseta Anime Dragon Ball Z");
		product.setPrice(0.0);
		product.setStock(0L);
		product.setCategory(getCategory());
		return product;
	}

	/**
	 * 
	 */
	public List<ProductDto> getListProductDto() {
		List<ProductDto> list = new ArrayList<ProductDto>();
		list.add(getProductDto());
		return list;
	}

	/**
	 * 
	 */
	public CategoryDto getCategoryDto() {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setIdcategory(1L);
		categoryDto.setName(CategoryEnum.CAMISETA.getType());
		categoryDto.setValue("1");
		categoryDto.setKey("categoryproduct");
		return categoryDto;
	}

	/**
	 * 
	 */
	public Category getCategory() {
		Category category = new Category();
		category.setIdcategory(1L);
		category.setName(CategoryEnum.CAMISETA.getType());
		category.setValue("1");
		category.setKey("categoryproduct");
		return category;
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexInDetailTestKardexNull() throws Todo1CustomException {
		KardexIn kardexIn = getKardexIn();			

		kardexIn.setIdkardexin(null);

		kardexInDetailServiceImpl.saveKardexInDetail(kardexIn, getKardexInListDto());
	}
	
	@Test(expected = Todo1CustomException.class)
	public void saveKardexInDetailTestStockNegative() throws Todo1CustomException {
		KardexIn kardexIn = getKardexIn();	
		List<KardexInDetailDto> detailDtos = getKardexInListDto();
		detailDtos.get(0).setStock(-1L);
		
		kardexInDetailServiceImpl.saveKardexInDetail(kardexIn, detailDtos);
	}
	
	@Test(expected = Todo1CustomException.class)
	public void saveKardexInDetailTest() throws Todo1CustomException {
		KardexIn kardexIn = getKardexIn();	
		KardexInDetail kardexInDetail = getKardexInDetail();	
		KardexInDetailDto kardexInDetailDto = getKardexInDetailDto();	

		when(dummyFieldMapper.map(kardexInDetailDto, KardexInDetail.class)).thenReturn(kardexInDetail);

		kardexInDetailServiceImpl.saveKardexInDetail(kardexIn, getKardexInListDto());
	}
	
	
}
