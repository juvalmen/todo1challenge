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
import com.todo1.technicaltest.model.Person;
import com.todo1.technicaltest.model.Product;
import com.todo1.technicaltest.repository.jdbc.JdbcKardexOutRepository;
import com.todo1.technicaltest.repository.jdbc.JdbcProductRepository;
import com.todo1.technicaltest.repository.jpa.JpaProductRepository;
import com.todo1.technicaltest.service.impl.CategoryServiceImpl;
import com.todo1.technicaltest.service.impl.KardexOutServiceImpl;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
@ActiveProfiles("test") 
public class KardexOutServiceImplTest {

	@Autowired
	@InjectMocks
	private KardexOutServiceImpl kardexOutServiceImpl;

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
	public void saveKardexOutTestSequentialNull() throws Todo1CustomException {
		KardexOutDto kardexOutDto = getKardexOutDto();			

		kardexOutDto.setSequential(null);

		kardexOutServiceImpl.saveKardexOut(kardexOutDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexOutTestDescriptionNull() throws Todo1CustomException {
		KardexOutDto kardexOutDto = getKardexOutDto();			

		kardexOutDto.setDescription(null);

		kardexOutServiceImpl.saveKardexOut(kardexOutDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexOutTestDescriptionLentgh() throws Todo1CustomException {
		KardexOutDto kardexOutDto = getKardexOutDto();	

		kardexOutDto.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean ut");

		kardexOutServiceImpl.saveKardexOut(kardexOutDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexOutTestPriceNull() throws Todo1CustomException {
		KardexOutDto kardexOutDto = getKardexOutDto();	

		kardexOutDto.setIdperson(null);

		kardexOutServiceImpl.saveKardexOut(kardexOutDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexOutMovementListNull() throws Todo1CustomException {
		KardexOutDto kardexOutDto = getKardexOutDto();	

		kardexOutDto.setKardexOutDetails(null);

		kardexOutServiceImpl.saveKardexOut(kardexOutDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexOutMovementListEmpty() throws Todo1CustomException {
		KardexOutDto kardexOutDto = getKardexOutDto();	

		kardexOutDto.setKardexOutDetails(new ArrayList<KardexOutDetailDto>());

		kardexOutServiceImpl.saveKardexOut(kardexOutDto);
	}

	@Test(expected = Todo1CustomException.class)
	public void saveKardexOutTestSecuenceNull() throws Todo1CustomException {
		
		KardexOutDto kardexOutDto = getKardexOutDto();	
		
		Person person = getPerson();
		PersonDto personDto = getPersonDto();
		KardexOut kardexOut = getKardexOut();

		when(personService.getPersonById(1L)).thenReturn(personDto);

		when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);

		when(dummyFieldMapper.map(kardexOutDto, KardexOut.class)).thenReturn(kardexOut);

		when(jdbcKardexOutRepository.getLastSequence()).thenReturn(null);

		when(kardexOutDetailService.saveKardexOutDetail(kardexOut, kardexOutDto.getKardexOutDetails())).thenReturn(true);

		kardexOutServiceImpl.saveKardexOut(kardexOutDto);
	}
	
	@Test(expected = Todo1CustomException.class)
	public void saveKardexOutTestSecuenceNotNull() throws Todo1CustomException {
		
		KardexOutDto kardexOutDto = getKardexOutDto();	
		
		Person person = getPerson();
		PersonDto personDto = getPersonDto();
		KardexOut kardexOut = getKardexOut();

		when(personService.getPersonById(1L)).thenReturn(personDto);

		when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);

		when(dummyFieldMapper.map(kardexOutDto, KardexOut.class)).thenReturn(kardexOut);

		when(jdbcKardexOutRepository.getLastSequence()).thenReturn(1L);

		when(kardexOutDetailService.saveKardexOutDetail(kardexOut, kardexOutDto.getKardexOutDetails())).thenReturn(true);

		kardexOutServiceImpl.saveKardexOut(kardexOutDto);
	}
	
}
