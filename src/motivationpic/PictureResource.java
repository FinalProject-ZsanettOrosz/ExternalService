package motivationpic;


import java.util.List;



import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless // only used if the the application is deployed in a Java EE container
@LocalBean // only used if the the application is deployed in a Java EE container
@Path("/pic")
public class PictureResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    int id;

    
    public  PictureResource() {
		// TODO Auto-generated constructor stub
	}

    // Application integration
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
    public Response getPicture() {
    	InstagramClient ic = new InstagramClient();
    	
    	
    	Random random = new Random();
    	int randomNumber = random.nextInt((ic.getMotivPics().length - 1) - 1) + 1;
    	
        String picture = ic.getMotivPics()[randomNumber];
        
        if (picture == null)
        	throw new WebApplicationException(Response.Status.NOT_FOUND);
        	//throw new RuntimeException("Get: Person with " + id + " not found");
        return Response.ok(picture).build();
    }
    

    // for the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getPersonHTML() {
    	InstagramClient ic = new InstagramClient();
    	String picture = ic.getMotivPics()[0];;
        if (picture == null)
            throw new RuntimeException("Get: Person with " + id + " not found");
        return picture;
    }
    
}