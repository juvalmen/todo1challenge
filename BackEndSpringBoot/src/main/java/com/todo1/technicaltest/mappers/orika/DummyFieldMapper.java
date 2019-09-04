package com.todo1.technicaltest.mappers.orika;

import org.springframework.stereotype.Component;

import com.todo1.technicaltest.dto.CategoryDto;
import com.todo1.technicaltest.dto.KardexInDetailDto;
import com.todo1.technicaltest.dto.KardexInDto;
import com.todo1.technicaltest.dto.KardexOutDetailDto;
import com.todo1.technicaltest.dto.PersonDto;
import com.todo1.technicaltest.dto.ProductDto;
import com.todo1.technicaltest.model.Category;
import com.todo1.technicaltest.model.KardexIn;
import com.todo1.technicaltest.model.KardexInDetail;
import com.todo1.technicaltest.model.KardexOutDetail;
import com.todo1.technicaltest.model.Person;
import com.todo1.technicaltest.model.Product;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * 
 * @author Julian Valencia
 * 29/08/2019
 */
@Component("dummyFieldMapper")
public class DummyFieldMapper extends ConfigurableMapper {

    @Override
	public void configure(MapperFactory factory) {
    	
    	factory = new DefaultMapperFactory.Builder().build();
    	
    	factory.classMap(Person.class, PersonDto.class)
    	.field("category.idcategory", "idPersonCategory")
    	.register();
    	
    	factory.classMap(Category.class, CategoryDto.class).register();
    	factory.classMap(Product.class, ProductDto.class)
    	.field("category.idcategory", "idProductCategory")
    	.register();
    	
    	factory.classMap(KardexIn.class, KardexInDto.class)
    	.field("person.idperson", "idperson")
    	.field("person.name", "personname")
    	.register();
    	
    	factory.classMap(KardexInDetail.class, KardexInDetailDto.class)
    	.field("product.idproduct", "idproduct")
    	.field("product.name", "productname")
    	.register();
    	
    	factory.classMap(KardexOutDetail.class, KardexOutDetailDto.class)
    	.field("product.idproduct", "idproduct")
    	.field("product.name", "productname")
    	.register();
    	
    }
  

}

