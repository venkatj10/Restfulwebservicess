/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.restfulweb.restfulwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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
public class UserJPAResource {
    
    @Autowired
    UserDaoService userDaoService;
    
    
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PostRepository postRepository;
    
    
    
    
   @GetMapping("/userJpa/findAll")
   public List<User> retriveAllUsers(){
    return userRepository.findAll();   
    }
   
   
   
   @GetMapping("/userJpa/findUser/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id){
               Optional<User>user = userRepository.findById(id);
               
               
               
     EntityModel<User> resource=new EntityModel<User>(user.get());
       WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retriveAllUsers());
 
    resource.add(linkTo.withRel("all-users"));
    return resource;
    }
    
    
    @PostMapping("/userJpa/createUser")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        
        User savedUser=userRepository.save(user);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
        
      return ResponseEntity.created(location).build();
    }
    
    
    
    @DeleteMapping("/userJpa/DeleteUser/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);  
    }
    
    
    @PostMapping("/userJpa/users/{id}/post")
    public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post ){
             Optional<User> userOptional=userRepository.findById(id);
                    
             if(!userOptional.isPresent()){
               throw new UserNotFoundException("id-" + id);   
             }
             
            User user= userOptional.get();
             
           post.setUser(user);
         
           Post savedPost=postRepository.save(post);
             
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
         return ResponseEntity.created(location).build();
    }
    
    
    @GetMapping("/userJpa/findAllPost")
    public List<Post> retriveAllPost(){
        return postRepository.findAll();
    }



    
    @GetMapping("/userJpa/users/{userId}/findPost")
    public List<Post> findUserPost(@PathVariable int userId){
        Optional<User>userOptional= userRepository.findById(userId);
        User user=userOptional.get();
        return user.getPosts();
    }
    
   
    
    @GetMapping("/userJpa/findPost/{id}")
    public EntityModel<Post> findPost(@PathVariable int id){
     Optional<Post>post = postRepository.findById(id);    
     EntityModel<Post> resource=new EntityModel<Post>(post.get());
     WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retriveAllPost());
    resource.add(linkTo.withRel("all-Post"));
    return resource;
        
    } 
}