/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.restfulweb.restfulwebservice;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 *
 * @author krish
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    
 public static List<VendorExtension>vendorList=new ArrayList<>(); 

 

 
public static final Contact DEFAULT_CONTACT = new Contact(
			"venkat krishna", "http://www.vk.com", "venkatkrishna@gmail.com");

public static final ApiInfo Default_Api_Info=new ApiInfo("UserRestfulservices","Crud operation of the user",
        "1.0","urn:tos",DEFAULT_CONTACT,"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",vendorList);
    
 
 public static final Set Default_produces_And_Consumes=new HashSet<>(Arrays.asList("application/json",
					"application/xml"));
 
// List vendors=new ArrayList();
// vendors.add("personA");
 
@Bean
 public Docket api(){
     
     return new Docket(DocumentationType.SWAGGER_2)
             .apiInfo(ApiInfo.DEFAULT)
             .consumes(Default_produces_And_Consumes)
             .produces(Default_produces_And_Consumes); 
     
    }
 
   @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

    
}
