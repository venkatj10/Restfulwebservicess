/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.restfulweb.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author krish
 */
@Component
public class UserDaoService {
    
    
 private static List<User> userList = new ArrayList<User>();
 private static int  userCount=4;
 
    
 static{
   
                userList.add(new User(1, "Adam", new Date()));
		userList.add(new User(2, "Eve", new Date()));
		userList.add(new User(3, "Jack", new Date()));
                userList.add(new User(4,"venkat",new Date()));
     
 }
 
 
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		userList.add(user);
		return user;
	}
  
  
  
       public User findOne(int id)
  {
     for(User user:userList){
         if(user.getId()== id){
              return user;
         }
        
     }
   return null;   
  }
  
  
  public List<User> findALL(){
  return userList;   
  }
  
   public User deleteUser(int id){
       
       
     Iterator<User> iterator = userList.iterator();
     
     while(iterator.hasNext()){
        User user =iterator.next();
        
        if(user.getId()==id){
            iterator.remove();
            return user;
        }
      
     }
      return null; 
   } 
  
}
