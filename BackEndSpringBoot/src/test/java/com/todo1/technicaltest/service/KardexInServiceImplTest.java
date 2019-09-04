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
import com.todo1.technicaltest.model.Person;
import com.todo1.technicaltest.model.Product;
import com.todo1.technicaltest.repository.jdbc.JdbcKardexInRepository;
import com.todo1.technicaltest.repository.jdbc.JdbcProductRepository;
import com.todo1.technicaltest.repository.jpa.JpaProductRepository;
import com.todo1.technicaltest.service.impl.CategoryServiceImpl;
import com.todo1.technicaltest.service.impl.KardexInServiceImpl;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
@ActiveProfiles("test") 
public class KardexInServiceImplTest {

	@Autowired
	@InjectMocks
	private KardexInServiceImpl kardexInServiceImpl;

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
	public void saveKardexInTestSequentialNull() throws Todo1CustomException {
		KardexInDto kardexInDto = getKardexInDto();			

		kardexInDto.setSequential(null);

		kardexInServiceImpl.saveKardexIn(kardexInDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexInTestDescriptionNull() throws Todo1CustomException {
		KardexInDto kardexInDto = getKardexInDto();			

		kardexInDto.setDescription(null);

		kardexInServiceImpl.saveKardexIn(kardexInDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexInTestDescriptionLentgh() throws Todo1CustomException {
		KardexInDto kardexInDto = getKardexInDto();	

		kardexInDto.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean ut");

		kardexInServiceImpl.saveKardexIn(kardexInDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexInTestPriceNull() throws Todo1CustomException {
		KardexInDto kardexInDto = getKardexInDto();	

		kardexInDto.setIdperson(null);

		kardexInServiceImpl.saveKardexIn(kardexInDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexInMovementListNull() throws Todo1CustomException {
		KardexInDto kardexInDto = getKardexInDto();	

		kardexInDto.setKardexInDetails(null);

		kardexInServiceImpl.saveKardexIn(kardexInDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexInMovementListEmpty() throws Todo1CustomException {
		KardexInDto kardexInDto = getKardexInDto();	

		kardexInDto.setKardexInDetails(new ArrayList<KardexInDetailDto>());

		kardexInServiceImpl.saveKardexIn(kardexInDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexInTestSecuenceNull() throws Todo1CustomException {
		
		KardexInDto kardexInDto = getKardexInDto();	
		
		Person person = getPerson();
		PersonDto personDto = getPersonDto();
		KardexIn kardexIn = getKardexIn();

		when(personService.getPersonById(1L)).thenReturn(personDto);

		when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);

		when(dummyFieldMapper.map(kardexInDto, KardexIn.class)).thenReturn(kardexIn);

		when(jdbcKardexInRepository.getLastSequence()).thenReturn(null);

		when(kardexInDetailService.saveKardexInDetail(kardexIn, kardexInDto.getKardexInDetails())).thenReturn(true);

		kardexInServiceImpl.saveKardexIn(kardexInDto);
	}
	
	@Test(expected = Todo1CustomException.class)
	public void saveKardexInTestSecuenceNotNull() throws Todo1CustomException {
		
		KardexInDto kardexInDto = getKardexInDto();	
		
		Person person = getPerson();
		PersonDto personDto = getPersonDto();
		KardexIn kardexIn = getKardexIn();

		when(personService.getPersonById(1L)).thenReturn(personDto);

		when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);

		when(dummyFieldMapper.map(kardexInDto, KardexIn.class)).thenReturn(kardexIn);

		when(jdbcKardexInRepository.getLastSequence()).thenReturn(1L);

		when(kardexInDetailService.saveKardexInDetail(kardexIn, kardexInDto.getKardexInDetails())).thenReturn(true);

		kardexInServiceImpl.saveKardexIn(kardexInDto);
	}
	
}
