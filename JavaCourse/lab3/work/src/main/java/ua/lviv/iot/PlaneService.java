package ua.lviv.iot;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;


@Path("/planes")
@SessionScoped
public class PlaneService implements Serializable{

    @Inject
    private CompanyManager companyManager;
//    private static CompanyManager companyManager = new CompanyManager();

    @GET
    @Path("{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public  Plane getPlane(@PathParam("id") Integer id) {
        return companyManager.findById(id);
    }


    @POST
    @Path("{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response updatePlane(@PathParam("id") Integer id,
                                      Plane plane) {
        companyManager.updatePlane(id, plane);
        return Response.status(200).entity("Successfully!!!").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response createPlane(Plane plane) {
        companyManager.addPlane(plane);
        return Response.status(200).entity("Successfully!!!").build();
    }

    @DELETE
    @Path("{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response deletePlane(@PathParam("id") Integer id) {
        companyManager.deletePlane(id);
        return Response.status(200).entity("Successfully!!!").build();
    }
}

