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
import com.todo1.technicaltest.dto.KardexOutDetailDto;
import com.todo1.technicaltest.dto.KardexOutDto;
import com.todo1.technicaltest.dto.PersonDto;
import com.todo1.technicaltest.dto.ProductDto;
import com.todo1.technicaltest.enums.CategoryEnum;
import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;
import com.todo1.technicaltest.model.Category;
import com.todo1.technicaltest.model.KardexOut;
import com.todo1.technicaltest.model.KardexOutDetail;
import com.todo1.technicaltest.model.Person;
import com.todo1.technicaltest.model.Product;
import com.todo1.technicaltest.repository.jdbc.JdbcKardexOutRepository;
import com.todo1.technicaltest.repository.jdbc.JdbcProductRepository;
import com.todo1.technicaltest.repository.jpa.JpaProductRepository;
import com.todo1.technicaltest.service.impl.CategoryServiceImpl;
import com.todo1.technicaltest.service.impl.KardexOutDetailServiceImpl;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
@ActiveProfiles("test") 
public class KardexOutDetailServiceImplTest {

	@Autowired
	@InjectMocks
	private KardexOutDetailServiceImpl kardexOutDetailServiceImpl;

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
	private KardexOutDetailService kardexOutDetailService;

	@Mock
	private JdbcKardexOutRepository jdbcKardexOutRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	public KardexOutDto getKardexOutDto() {
		KardexOutDto kardexOutDto = new KardexOutDto();
		kardexOutDto.setIdkardexout(1L);
		kardexOutDto.setDescription("Description");
		kardexOutDto.setIdperson(getPerson().getIdperson());
		kardexOutDto.setSequential(1L);
		kardexOutDto.setKardexOutDetails(getKardexOutListDto());
		return kardexOutDto;
	}
	
	public KardexOut getKardexOut() {
		KardexOut kardexOut = new KardexOut();
		kardexOut.setIdkardexout(1L);
		kardexOut.setDescription("Description");
		kardexOut.setPerson(getPerson());
		kardexOut.setSequential(1L);
		return kardexOut;
	}
	
	public KardexOutDetail getKardexOutDetail() {
		KardexOutDetail kardexOutDetail = new KardexOutDetail();
		kardexOutDetail.setIdkardexoutdetail(null);
		kardexOutDetail.setKardexOut(getKardexOut());
		kardexOutDetail.setProduct(getProduct());
		kardexOutDetail.setQuantity(4L);
		return kardexOutDetail;
	}

	public KardexOutDetailDto getKardexOutDetailDto() {
		KardexOutDetailDto kardexOutDetail = new KardexOutDetailDto();
		kardexOutDetail.setIdkardexoutdetail(null);
		kardexOutDetail.setIdkardexout(1L);
		kardexOutDetail.setIdproduct(1L);
		kardexOutDetail.setQuantity(4L);
		return kardexOutDetail;
	}

	public List<KardexOutDetailDto> getKardexOutListDto() {
		List<KardexOutDetailDto> detailDtos = new ArrayList<KardexOutDetailDto>(); 
		KardexOutDetailDto kardexOutDetailDto = new KardexOutDetailDto();
		kardexOutDetailDto.setIdkardexout(1L);
		kardexOutDetailDto.setIdkardexoutdetail(null);
		kardexOutDetailDto.setStockmovement(2L);
		kardexOutDetailDto.setIdproduct(getProduct().getIdproduct());
		detailDtos.add(kardexOutDetailDto);
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
	public void saveKardexOutDetailTestKardexNull() throws Todo1CustomException {
		KardexOut kardexOut = getKardexOut();			

		kardexOut.setIdkardexout(null);

		kardexOutDetailServiceImpl.saveKardexOutDetail(kardexOut, getKardexOutListDto());
	}
	
	@Test(expected = Todo1CustomException.class)
	public void saveKardexOutDetailTestStockNegative() throws Todo1CustomException {
		KardexOut kardexOut = getKardexOut();	
		List<KardexOutDetailDto> detailDtos = getKardexOutListDto();
		detailDtos.get(0).setStock(-1L);
		
		kardexOutDetailServiceImpl.saveKardexOutDetail(kardexOut, detailDtos);
	}
	
	@Test(expected = Todo1CustomException.class)
	public void saveKardexOutDetailTest() throws Todo1CustomException {
		KardexOut kardexOut = getKardexOut();	
		KardexOutDetail kardexOutDetail = getKardexOutDetail();	
		KardexOutDetailDto kardexOutDetailDto = getKardexOutDetailDto();	

		when(dummyFieldMapper.map(kardexOutDetailDto, KardexOutDetail.class)).thenReturn(kardexOutDetail);

		kardexOutDetailServiceImpl.saveKardexOutDetail(kardexOut, getKardexOutListDto());
	}
	
	
}
