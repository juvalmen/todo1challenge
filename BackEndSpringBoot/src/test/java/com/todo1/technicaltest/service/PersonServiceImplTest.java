package com.todo1.technicaltest.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.todo1.technicaltest.dto.PersonDto;
import com.todo1.technicaltest.enums.CategoryEnum;
import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;
import com.todo1.technicaltest.model.Category;
import com.todo1.technicaltest.model.Person;
import com.todo1.technicaltest.repository.jdbc.JdbcPersonRepository;
import com.todo1.technicaltest.repository.jpa.JpaPersonRepository;
import com.todo1.technicaltest.service.impl.CategoryServiceImpl;
import com.todo1.technicaltest.service.impl.PersonServiceImpl;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
@ActiveProfiles("test") 
public class PersonServiceImplTest {
	
	@Autowired
	@InjectMocks
	private PersonServiceImpl personServiceImpl;

	@Mock
	private JdbcPersonRepository jdbcPersonRepository;
	
	@Mock
	private JpaPersonRepository jpaPersonRepository;
	
	@Mock
	private CategoryServiceImpl categoryServiceImpl;
	
	@Mock
	private DummyFieldMapper dummyFieldMapper;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * 
	 */
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
	public Person getPerson() {
		Person person = new Person();
		person.setName("Julian");
		person.setLastname("Valencia");
		person.setIdentificationnumber("123456");
		person.setCategory(getCategory());
		return person;
	}
	
	/**
	 * 
	 */
	public List<PersonDto> getListPersonDto() {
		List<PersonDto> list = new ArrayList<PersonDto>();
		list.add(getPersonDto());
		return list;
	}
	
	/**
	 * 
	 */
	public CategoryDto getCategoryDto() {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setIdcategory(1L);
		categoryDto.setName(CategoryEnum.VENDEDOR.getType());
		categoryDto.setValue("1");
		categoryDto.setKey("categoryperson");
		return categoryDto;
	}
	
	/**
	 * 
	 */
	public Category getCategory() {
		Category category = new Category();
		category.setIdcategory(1L);
		category.setName(CategoryEnum.VENDEDOR.getType());
		category.setValue("1");
		category.setKey("categoryperson");
		return category;
	}
	
    @Test(expected = Todo1CustomException.class)
    public void savePersonTestIdentificationnumberNull() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setIdentificationnumber(null);
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.savePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void savePersonTestIdentificationnumberEmpty() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setIdentificationnumber("");
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.savePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void savePersonTestCategoryNull() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setIdPersonCategory(null);
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.savePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void savePersonTestIdentificationnumberExist() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(getListPersonDto());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.savePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void savePersonTestNameNull() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setName(null);
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.savePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void savePersonTestNameLentgh() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setName("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a,");
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.savePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void savePersonTestLastNameNull() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setLastname(null);
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.savePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void savePersonTestLastNameLentgh() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setLastname("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean ut");
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.savePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void savePersonTestIdentificationnumberLentgh() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setIdentificationnumber("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a,");
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.savePerson(personDto);
    }
    
    @Test
    public void savePersonTest() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
							
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.savePerson(personDto);
    }
        
    @Test(expected = Todo1CustomException.class)
    public void updatePersonIdNull() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			personDto.setIdperson(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setIdperson(null);
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.updatePerson(personDto);
    }

    @Test(expected = Todo1CustomException.class)
    public void updatePersonIdDoesntExist() throws Todo1CustomException {
    	Person person = getPerson();
    	PersonDto personDto = getPersonDto();
    	personDto.setIdperson(1L);
    	CategoryDto categoryDto = getCategoryDto();
    	Category category = getCategory();
    	
    	personDto.setIdperson(3L);
    	
    	when(jpaPersonRepository.findById(3L)).thenReturn(Optional.ofNullable(null));
    	
    	when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
    	
    	when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
    	
    	when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);
    	
    	when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
    	
    	personServiceImpl.updatePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updatePersonIdExist() throws Todo1CustomException {
    	Person person = getPerson();
    	PersonDto personDto = getPersonDto();
    	personDto.setIdperson(1L);
    	CategoryDto categoryDto = getCategoryDto();
    	Category category = getCategory();
    	
    	personDto.setIdperson(3L);
    	
    	when(jpaPersonRepository.findById(3L)).thenReturn(Optional.of(person));
    	
    	when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
    	
    	when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
    	
    	when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);
    	
    	when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
    	
    	personServiceImpl.updatePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updatePersonTestIdentificationnumberNull() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			personDto.setIdperson(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setIdentificationnumber(null);
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.updatePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updatePersonTestIdentificationnumberEmpty() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			personDto.setIdperson(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setIdentificationnumber("");
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.updatePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updatePersonTestCategoryNull() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			personDto.setIdperson(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setIdPersonCategory(null);
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.updatePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updatePersonTestIdentificationnumberExist() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			personDto.setIdperson(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(getListPersonDto());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.updatePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updatePersonTestNameNull() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			personDto.setIdperson(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setName(null);
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.updatePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updatePersonTestNameLentgh() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			personDto.setIdperson(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setName("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a,");
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.updatePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updatePersonTestLastNameNull() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			personDto.setIdperson(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setLastname(null);
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.updatePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updatePersonTestLastNameLentgh() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			personDto.setIdperson(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setLastname("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean ut");
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.updatePerson(personDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updatePersonTestIdentificationnumberLentgh() throws Todo1CustomException {
			Person person = getPerson();
			PersonDto personDto = getPersonDto();
			personDto.setIdperson(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			personDto.setIdentificationnumber("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a,");
					
			when(jdbcPersonRepository.getPersonByIdentification("123456")).thenReturn(new ArrayList<PersonDto>());
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(personDto, Person.class)).thenReturn(person);
			
			personServiceImpl.updatePerson(personDto);
    }
    
    @Test
    public void deletePersonTest() throws Todo1CustomException {
						
			personServiceImpl.deletePerson(1L);
			
			PersonDto personDto2 = personServiceImpl.getPersonById(1L);
	
			assertEquals(null, personDto2);
    }
    
    @Test
    public void getEmptyListPersonTest() throws Todo1CustomException {
		
		List<PersonDto> list = personServiceImpl.getPersonsList();
		
		assertEquals(true, list.isEmpty());
}

}
