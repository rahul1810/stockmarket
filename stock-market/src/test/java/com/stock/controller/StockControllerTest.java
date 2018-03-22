package com.stock.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.stock.manager.StockManager;


@RunWith(SpringRunner.class)
@WebMvcTest(value = StockController.class, secure = false)
public class StockControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StockManager stockManager;
	
	
	@Test
	public void stockCreationTest() throws Exception {
		String exampleStockJson = "{\"id\": 1,\"currentPrice\": 39.34,\"name\": \"Test Stock\"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/stocks/1/").accept(MediaType.APPLICATION_JSON)
				.content(exampleStockJson).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void stockUpdationTest() throws Exception {
		
		int maxStockPrice =  100;
		double currentPrice = (double) (Math.random() * maxStockPrice);
		currentPrice = Math.round(currentPrice*100)/100.0;
		String exampleStockJson = "{\"id\": 1 ,\"currentPrice\": 99.99}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/stocks/1/").accept(MediaType.APPLICATION_JSON)
				.content(exampleStockJson).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
	}
	
	@Test
	public void stockListTest() throws Exception{
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/stocks/");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
