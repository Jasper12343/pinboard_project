package com.example.tiptop;

import java.util.ArrayList; 
import java.util.List;
import com.example.tiptop.newUser;
import com.example.tiptop.database.MitarbeiterDAO;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

// All of the CRUD functions here
@DeclareRoles({"ADMIN", "USER", "GUEST"})
@Path("/myresource")
public class MyResource {

	// Declare which roles are allowed
	@RolesAllowed({"ADMIN", "GUEST"})
	@Path("/create")
	@POST
	@Produces(MediaType.APPLICATION_JSON) // Specify JSON response
	public void addMitarbeiter(@FormParam("name2") String name, @FormParam("department2") String department, @FormParam("salary2") int salary) {

		//if (name.isEmpty() && department.isEmpty() && salary <= 0) {
		//	// Construct the URL to which you want to redirect (form.jsp)
		//	UriBuilder uriBuilder = UriBuilder.fromPath("http://localhost:8080/tiptop_war_exploded/form.jsp");
		//	// Build the response with the redirection status code and location header
		//	return Response.seeOther(uriBuilder.build()).build();
		//}

		postMitarbeiter post = new postMitarbeiter();
		MitarbeiterDAO mdao = new MitarbeiterDAO();

		// Mitarbeiter Objekt ändern
		post.setName(name);
		post.setDepartment(department);
		post.setSalary(salary);
		try {
			mdao.insert(post);
			//return Response.ok(post).build();
		} catch (Exception e) {
			// Return an error response
			//return Response.serverError().entity(e.getMessage()).build();
		}
	}


	// Declare which roles are allowed
	@RolesAllowed("ADMIN")
	@Path("/createNewUser")
	@POST
	//@Produces(MediaType.APPLICATION_JSON) // Specify JSON response
	public void createNewUser(@FormParam("username") String username, @FormParam("password") String password, @FormParam("searchCriteria") String role) { //Get the data from the URL

		//if (username.isEmpty() && password.isEmpty() && role.isEmpty()) {
		//     // Construct the URL to which you want to redirect
		//    UriBuilder uriBuilder = UriBuilder.fromPath("http://localhost:8080/tiptop_war_exploded/form.jsp");
		//    // Build the response with the redirection status code and location header
		//    return Response.seeOther(uriBuilder.build()).build();
		//}

		newUser newUser = new newUser();
	    MitarbeiterDAO mdao = new MitarbeiterDAO();

	    // Mitarbeiter Objekt ändern
	    newUser.setUsername(username);
	    newUser.setPassword(password);
	    newUser.setRole(role);
	    try {
			mdao.insertNewUser(newUser);
			//	return Response.ok(newUser).build();
		}catch(Exception e) {
			// return Response.ok(e).build();
		}
    }

	//Read (GET):
	@RolesAllowed({"ADMIN","USER"})
	@Path("/getAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployees() {
	    // Logik zum Abrufen des Mitarbeiters aus der Datenbank
		try {
            String employeesJson = MitarbeiterDAO.findAllAsJson();
            return Response.ok(employeesJson).build();
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return an error response
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("An error occurred while retrieving employees.")
                           .build();
        }

	}

	@RolesAllowed({"ADMIN","USER"})
	@Path("/getWithName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeesWithName(@QueryParam("name") String name) {

		if (name.isEmpty()) {
            // Construct the URL to which you want to redirect
            UriBuilder uriBuilder = UriBuilder.fromPath("http://localhost:8080/tiptop_war_exploded/form.jsp");
            // Build the response with the redirection status code and location header
            return Response.seeOther(uriBuilder.build()).build();
        }

	    // Logik zum Abrufen des Mitarbeiters aus der Datenbank
		try {
            String employeesJson = MitarbeiterDAO.findWithName(name);
            if (employeesJson == null) {
            	return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("An error occurred while retrieving employees.")
                        .build();
            }
            return Response.ok(employeesJson).build();
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return an error response
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("An error occurred while retrieving employees.")
                           .build();
        }

	}

	@RolesAllowed({"ADMIN","USER"})
	@Path("/getWithDepartment")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeesWithDepartment(@QueryParam("department") String department) {

		if (department.isEmpty()) {
            // Construct the URL to which you want to redirect
            UriBuilder uriBuilder = UriBuilder.fromPath("http://localhost:8080/tiptop_war_exploded/form.jsp");
            // Build the response with the redirection status code and location header
            return Response.seeOther(uriBuilder.build()).build();
        }

	    // Logik zum Abrufen des Mitarbeiters aus der Datenbank
		try {
            String employeesJson = MitarbeiterDAO.findWithDepartment(department);
            return Response.ok(employeesJson).build();
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return an error response
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("An error occurred while retrieving employees.")
                           .build();
        }

	}

	@RolesAllowed({"ADMIN","USER"})
	@Path("/getWithSalary")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeesWithSalary(@QueryParam("salary") int salary) {

		if (salary <= 0) {
            // Construct the URL to which you want to redirect
            UriBuilder uriBuilder = UriBuilder.fromPath("http://localhost:8080/tiptop_war_exploded/form.jsp");
            // Build the response with the redirection status code and location header
            return Response.seeOther(uriBuilder.build()).build();
        }

	    // Logik zum Abrufen des Mitarbeiters aus der Datenbank
		try {
            String employeesJson = MitarbeiterDAO.findWithSalary(salary);
            return Response.ok(employeesJson).build();
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return an error response
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("An error occurred while retrieving employees.")
                           .build();
        }

	}

	//Read (GET):
	@RolesAllowed({"ADMIN","USER"})
	@Path("/getWithId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMitarbeiter(@QueryParam("id1") int id1) {

		if (id1 <= 0) {
            // Construct the URL to which you want to redirect
            UriBuilder uriBuilder = UriBuilder.fromPath("http://localhost:8080/tiptop_war_exploded/form.jsp");
            // Build the response with the redirection status code and location header
            return Response.seeOther(uriBuilder.build()).build();
        }

	    // Logik zum Abrufen des Mitarbeiters aus der Datenbank
		try {
            String employeesJson = MitarbeiterDAO.findById(id1);
            return Response.ok(employeesJson).build();
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return an error response
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("An error occurred while retrieving employees.")
                           .build();
        }

	}


	// Declare Which roles are allowed
	@RolesAllowed({"ADMIN","USER"})
	@Path("/update")
	@PUT
	public Response updateMitarbeiter(@FormParam("id") int id, @FormParam("name") String name, @FormParam("department") String department, @FormParam("salary") int salary) {

		if (id <= 0 || name.isEmpty() || department.isEmpty() || salary <= 0) {
            // Construct the URL to which you want to redirect
            UriBuilder uriBuilder = UriBuilder.fromPath("http://localhost:8080/tiptop_war_exploded/form.jsp");
            // Build the response with the redirection status code and location header
            return Response.seeOther(uriBuilder.build()).build();
        }

		// Logik zum Aktualisieren des Mitarbeiters in der Datenbank
		Mitarbeiter mitarbeiter = new Mitarbeiter();
	    MitarbeiterDAO mdao = new MitarbeiterDAO();

	    // Mitarbeiter Objekt ändern
	    mitarbeiter.setID(id);
	    mitarbeiter.setName(name);
		mitarbeiter.setDepartment(department);
		mitarbeiter.setSalary(salary);

		try {
			mdao.update(mitarbeiter);
			return Response.ok(mitarbeiter).build();
		}catch(Exception e) {
		    return Response.ok(e).build();
		}
	}


	@RolesAllowed({"ADMIN", "GUEST"})
	//Delete (DELETE):
	@Path("/delete")
	@DELETE
	public Response deleteMitarbeiter(@QueryParam("id") int id) {

		if (id <= 0) {
            // Construct the URL to which you want to redirect
            UriBuilder uriBuilder = UriBuilder.fromPath("http://localhost:8080/tiptop_war_exploded/form.jsp");
            // Build the response with the redirection status code and location header
            return Response.seeOther(uriBuilder.build()).build();
        }

	    // Logik zum LÃ¶schen des Mitarbeiters aus der Datenbank
		MitarbeiterDAO mdao = new MitarbeiterDAO();
		
		// id in String umwandeln
		try {
			mdao.delete(id);
			return Response.ok(id).build();
			
		}catch(Exception e) {
			return Response.ok(e).build();
		}
		
	}
	
}
