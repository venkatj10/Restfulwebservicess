/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.restfulweb.restfulwebservice.hello;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author krish
 */
@RestController
public class helloController {
    
    @Autowired
    private MessageSource messageSource; 
    
    @GetMapping(path="/hello")
    public String helloworld(){
        
        return"hey buddy this is me your program";
        
    }
    
    @GetMapping(path= "/hellobean")
    public HelloWorldBean helloworldbean(){
        
        return new HelloWorldBean(10,"VIRAT") ;
        
    }
    
    @GetMapping(path= "/hellobean/path-var/{name}")
    public HelloWorldBean helloworldbean(@PathVariable String name){
        return new HelloWorldBean(10,name) ;
        
    }
    
    @GetMapping(path= "/hellobean/internalised")
    public String helloworldbeanInternal(){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());  
    }
    
    
    
    
}
