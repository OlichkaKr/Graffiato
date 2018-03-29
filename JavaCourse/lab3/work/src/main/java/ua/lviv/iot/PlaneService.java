package ua.lviv.iot;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/planes")
public class PlaneService {

    private static CompanyManager companyManager = new CompanyManager();

    @GET
    @Path("{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public final Plane getPlane(@PathParam("id") Integer id) {
        return companyManager.getPlaneList().get(id);
    }


    @POST
    @Path("{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response updatePlane(@PathParam("id") Integer id,
                                      Plane plane) {
        companyManager.getPlaneList().put(plane.getId(), plane);
        return Response.status(200).entity("Successfully!!!").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response createPlane(Plane plane) {
        companyManager.addPlane(plane);
        return Response.status(200).entity("Successfully!!!").build();
    }

    @DELETE
    @Path("{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response deletePlane(@PathParam("id") Integer id) {
        companyManager.getPlaneList().remove(id);
        return Response.status(200).entity("Successfully!!!").build();
    }
}

