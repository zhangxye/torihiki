package com.tsrs.webedi.modular.system.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tsrs.webedi.common.persistence.model.User;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
initParams = {
    @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
})
class LoginControllerTest {
	
    @Autowired
    private MockMvc mockMvc;

	  @Autowired WebApplicationContext webApplicationContext;
	  
	  @BeforeEach
	  void beforeEach() {
	    mockMvc =
	        MockMvcBuilders.webAppContextSetup(webApplicationContext)     // MockMVCをセットアップ
//	            .addFilter(logFilter, "/*")                               // ただしfilterは手動で追加が必要
	            .build();
	  }

	@Test
	void test() throws Exception {
	    // Given
	    String expectedMimeType = "application/json";
	    
	    User user = new User();
	    user.setAccount("test");
	    user.setPassword("111111");
	    // Then
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/login", user))
	      .andReturn();
	    
	    String actualMimeType = mvcResult.getResponse().getContentType();

	    assertEquals(expectedMimeType, actualMimeType);
	}

}
