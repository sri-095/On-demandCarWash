package com.capg.ocw.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.ocw.model.dto.CarDetailsDto;
import com.capg.ocw.operation.CarManagementOperation;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

@SpringBootTest
public class CarManagementTest {
	
	@InjectMocks
	private CarManagementContoller carManagementContoller;
	
	@Mock
	private CarManagementOperation carManagementOperation;
	
	 List<CarDetailsDto> carDetailsDtos;
	 @Before
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	        
	        carDetailsDtos = new ArrayList<>();
	    }
	 
	 @Test
	 public void testFetchAllCarDetailsReturnsList() {
		Mockito.when(carManagementOperation.fetchAllCarDetails()).thenReturn(carDetailsDtos);
		assertNotNull(carManagementContoller.fetchAllCarDetails());
	 }


}
