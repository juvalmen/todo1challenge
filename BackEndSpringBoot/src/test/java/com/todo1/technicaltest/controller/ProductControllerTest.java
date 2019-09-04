package com.todo1.technicaltest.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo1.technicaltest.dto.BaseResponseDto;
import com.todo1.technicaltest.dto.ProductDto;
import com.todo1.technicaltest.service.ProductService;
import com.todo1.technicaltest.util.SystemMessage;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
@ActiveProfiles("test") 

public class ProductControllerTest {

    private MockMvc mvc;

	@MockBean
	private ProductService productService;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Autowired private ObjectMapper objectMapper;
	
	private JacksonTester <Object> listJsonTester;
	private ProductDto product;
	
	@Autowired
	private ProductController controller;
	
	@Before
    public void setup() {

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        JacksonTester.initFields(this, objectMapper);
        product = new ProductDto();
    	product.setIdproduct(1L);
    	product.setName("NIER AUTOMATA");
    	product.setDescription("VIDEO GAME");    	
    }
 
    @Test
    public void findAll() throws Exception {
    
    	//Given
    	List<ProductDto> products = Collections.singletonList(product);
        given(productService.getProductsList()).willReturn(products);
        final String outputJson = listJsonTester.write(products).getJson();
        
        //When
        String root = controller.getClass().getAnnotation(RequestMapping.class).value()[0];
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(root +"/getAll"))
                .andExpect(status().isOk());
        
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        BaseResponseDto response = objectMapper.readValue(contentAsString, BaseResponseDto.class);
        
        //Then
        assertEquals(listJsonTester.write(response.getResponseBody()).getJson(), outputJson);
        assertEquals(response.getResponseMessage(), SystemMessage.STATUS_OK);
    }
      

}
