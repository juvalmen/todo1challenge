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
import com.todo1.technicaltest.dto.PersonDto;
import com.todo1.technicaltest.service.PersonService;
import com.todo1.technicaltest.util.SystemMessage;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
@ActiveProfiles("test") 

public class PersonControllerTest {

    private MockMvc mvc;

	@MockBean
	private PersonService personService;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Autowired private ObjectMapper objectMapper;
	
	private JacksonTester <Object> listJsonTester;
	private PersonDto person;
	
	@Autowired
	private PersonController controller;
	
	@Before
    public void setup() {

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        JacksonTester.initFields(this, objectMapper);
        person = new PersonDto();
    	person.setIdperson(1L);
    	person.setName("NIER AUTOMATA");
    	person.setLastname("CALLE 4 5 4-67");    	
    }
 
    @Test
    public void findAll() throws Exception {
    
    	//Given
    	List<PersonDto> persons = Collections.singletonList(person);
        given(personService.getPersonsList()).willReturn(persons);
        final String outputJson = listJsonTester.write(persons).getJson();
        
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
