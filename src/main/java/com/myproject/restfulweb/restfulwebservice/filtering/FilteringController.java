/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.restfulweb.restfulwebservice.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author krish
 */
@RestController
public class FilteringController extends SimpleBeanPropertyFilter {
    //create bean which needs filter
    // assing the  value to the create propery filter
    //create filter provider and assign the filter  to the filterprovider
    // tell which object filters need to apply

    
    @GetMapping("/someBean/filterProperty")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);

        mapping.setFilters(filters);
        return mapping;

    }
    
    @GetMapping("/someBean/filterPropertyList")
    public MappingJacksonValue retrieveSomeBeanList() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        
        List<SomeBean> someBeanList =Arrays.asList(new SomeBean("value1", "value2", "value3"),new SomeBean("value4", "value5", "value6"));
        
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBeanList);

        mapping.setFilters(filters);
        return mapping;

    }
    
    
    
    
    
    
    
    

}
