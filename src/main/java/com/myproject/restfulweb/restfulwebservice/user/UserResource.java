/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.restfulweb.restfulwebservice.user;

import com.myproject.restfulweb.restfulwebservice.hello.HelloWorldBean;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author krish
 */

@RestController
public class UserResource {
    
    @Autowired
    UserDaoService service;
    
    
      @GetMapping(path= "/user/sample")
    public String findSample( ){
        return "user service" ;
        
    }
    
     @GetMapping(path="/user/{userId}")
    public EntityModel findUser(@PathVariable int userId){
       User user=service.findOne(userId);
     
         if(user==null){
            throw new UserNotFoundException("id"+userId);  
            }
   EntityModel<User> model =new EntityModel<User>(user);  
  // model.add(Link.of("http://localhost:8081/user/getAll"));  //hard code link
   
   
   	WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAll());
 
	model.add(linkTo.withRel("all-users"));
 
	return model;
        
    }
    
    @GetMapping(path= "/user/getAll")
    public List<User> findAll(){
        return service.findALL() ;  
    }
    
    
    @PostMapping(path= "/saveUser")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
        
        User resultUser = service.save(user);
      //  return resultUser;
        
     URI location=ServletUriComponentsBuilder
             .fromCurrentRequest()
             .path("/{id}")
             .buildAndExpand(resultUser.getId()).toUri();
     
    return ResponseEntity.created(location).build();
 
    }
    
  
    
    
    @DeleteMapping(path="/deleteUser/{id}")
    public void deleteUser(@PathVariable int id){
     User user= service.deleteUser(id);
        if(user==null){
            throw new UserNotFoundException("id"+id);
        }
 
    }
    
    
}
