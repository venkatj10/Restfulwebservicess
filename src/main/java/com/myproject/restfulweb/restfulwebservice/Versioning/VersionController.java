/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.restfulweb.restfulwebservice.Versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author krish
 */

@RestController
public class VersionController {
    
    
    @GetMapping("/person/v1")
    public PersonV1 personV1(){
       return new PersonV1("bob charlie");   
    }
    
    
    @GetMapping("/person/v2")
    public PersonV2 personV2(){
        return new PersonV2(new Name("bob","charlie"));
    }
    
    @GetMapping(value="/person/param", params="version=1")
    public PersonV1 personParamV1(){
       return new PersonV1("bob charlie");   
    }
    
    
    @GetMapping(value="/person/param", params="version=2")
    public PersonV2 personParamV2(){
        return new PersonV2(new Name("bob","charlie"));
    }
    
    
     @GetMapping(value="/person/header", headers = "X-API-VERSION=1")
    public PersonV1 personHeaderV1(){
       return new PersonV1("bob charlie");   
    }
    
    
    @GetMapping(value="/person/header", headers = "X-API-VERSION=2")
    public PersonV2 personHeaderV2(){
        return new PersonV2(new Name("bob","charlie"));
    }
    
    
        @GetMapping(value="/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personProducesV1(){
       return new PersonV1("bob charlie");   
    }
    
    
    @GetMapping(value="/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 personProducesV2(){
        return new PersonV2(new Name("bob","charlie"));
    }
    
   
}
