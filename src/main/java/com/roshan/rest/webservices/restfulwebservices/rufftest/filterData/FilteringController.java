package com.roshan.rest.webservices.restfulwebservices.rufftest.filterData;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.roshan.rest.webservices.restfulwebservices.rufftest.SomeBean;

@RestController
public class FilteringController {

	@GetMapping(path = "/filtering")
	public SomeBean getSomeBean() {
		return new SomeBean("Value 1","Value 2","Value 3","Value 4","Value 5");
	}
	
	
	@GetMapping(path = "/filtering-list")
	public MappingJacksonValue getSomeBeanList() {
		List<SomeBean> someBeanList = new ArrayList<>(); 
		someBeanList.add(new SomeBean("Value 1","Value 2","Value 3","Value 4","Value 5"));
		someBeanList.add(new SomeBean("Value 1","Value 2","Value 3","Value 4","Value 5"));
		
		SimpleBeanPropertyFilter beanPropertyFilter  = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3"); //Only We need these field in Response
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", beanPropertyFilter);
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeanList);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}
	
}
