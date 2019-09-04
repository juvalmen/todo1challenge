package com.todo1.technicaltest.service;

import static org.junit.Assert.assertEquals;
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
import com.todo1.technicaltest.dto.ProductDto;
import com.todo1.technicaltest.enums.CategoryEnum;
import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;
import com.todo1.technicaltest.model.Category;
import com.todo1.technicaltest.model.Product;
import com.todo1.technicaltest.repository.jdbc.JdbcProductRepository;
import com.todo1.technicaltest.repository.jpa.JpaProductRepository;
import com.todo1.technicaltest.service.impl.CategoryServiceImpl;
import com.todo1.technicaltest.service.impl.ProductServiceImpl;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
@ActiveProfiles("test") 
public class ProductServiceImplTest {
	
	@Autowired
	@InjectMocks
	private ProductServiceImpl productServiceImpl;

	@Mock
	private JdbcProductRepository jdbcProductRepository;
	
	@Mock
	private JpaProductRepository jpaProductRepository;
	
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
    public void saveProductTestCategoryNull() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setIdProductCategory(null);
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.saveProduct(productDto);
    }
    
    public void saveProductTestIdentificationnumberExist() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.saveProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void saveProductTestNameNull() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setName(null);
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.saveProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void saveProductTestNameLentgh() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setName("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a,");
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.saveProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void saveProductTestDescriptionNull() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setDescription(null);
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.saveProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void saveProductTestDescriptionLentgh() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean ut");
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.saveProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void saveProductTestPriceNull() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setPrice(null);
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.saveProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void saveProductTestPriceNegative() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setPrice(-10000.0);
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.saveProduct(productDto);
    }
    
    public void saveProductTestStockNull() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setStock(null);
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.saveProduct(productDto);
    }
    
    public void saveProductTestStockNegative() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setStock(-1L);
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.saveProduct(productDto);
    }
      
    @Test
    public void saveProductTest() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
	
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.saveProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updateProductTestCategoryNull() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			productDto.setIdproduct(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setIdProductCategory(null);
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.updateProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updateProductTestNameNull() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			productDto.setIdproduct(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setName(null);

			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.updateProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updateProductTestNameLentgh() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			productDto.setIdproduct(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setName("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a,");
					
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.updateProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updateProductTestDescriptionNull() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			productDto.setIdproduct(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setDescription(null);
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.updateProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updateProductTestDescriptionLentgh() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			productDto.setIdproduct(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			productDto.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean ut");
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.updateProduct(productDto);
    }
    
    @Test(expected = Todo1CustomException.class)
    public void updateProductTestIdentificationnumberLentgh() throws Todo1CustomException {
			Product product = getProduct();
			ProductDto productDto = getProductDto();
			productDto.setIdproduct(1L);
			CategoryDto categoryDto = getCategoryDto();
			Category category = getCategory();
			
			when(categoryServiceImpl.getCategoryById(1L)).thenReturn(categoryDto);
			
			when(dummyFieldMapper.map(categoryDto, Category.class)).thenReturn(category);

			when(dummyFieldMapper.map(productDto, Product.class)).thenReturn(product);
			
			productServiceImpl.updateProduct(productDto);
    }
    
    @Test
    public void deleteProductTest() throws Todo1CustomException {
						
			productServiceImpl.deleteProduct(1L);
			
			ProductDto productDto2 = productServiceImpl.getProductById(1L);
	
			assertEquals(null, productDto2);
    }
    
    @Test
    public void getEmptyListProductTest() throws Todo1CustomException {
		
		List<ProductDto> list = productServiceImpl.getProductsList();
		
		assertEquals(true, list.isEmpty());
}

}
