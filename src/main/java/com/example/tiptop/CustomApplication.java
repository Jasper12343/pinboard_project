package com.example.tiptop;

import org.glassfish.jersey.server.ResourceConfig;

 
public class CustomApplication extends ResourceConfig 
{
  public CustomApplication() 
  {
    packages("com.howtodoinjava.jersey");
	  
    //Register Auth Filter
    register(AuthenticationFilter.class);
  }
}