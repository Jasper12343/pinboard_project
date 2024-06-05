package com.example.tiptop;

import java.io.IOException; 
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.ext.Provider;
import jakarta.servlet.http.HttpServletRequest;
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
	
	@Context
	private HttpServletRequest request;
	@Context
	private ResourceInfo resourceInfo;
      
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        // Access allowed for all
        if (!method.isAnnotationPresent(PermitAll.class)) {
            // Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Access blocked for all users !!").build());
                return;
            }
            
            // Get session from request
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role") == null) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("You cannot access this resource").build());
                return;
            }
            
            
            String role = (String) session.getAttribute("role");
            System.out.println(role);

            // Verify user access
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                // Is user valid?
                if (!isUserAllowed(role,rolesSet)) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You cannot access this resource").build());
                    return;
                }
            }
        }
    }

	private boolean isUserAllowed(String role, final Set<String> rolesSet)
    {
        boolean isAllowed = false;
              //Step 2. Verify user role
              if(rolesSet.contains(role))
              {
              	System.out.println("Role: Passed with "+role);
                  isAllowed = true;
              }
        return isAllowed;
    }
}