package ithome.cch;

import org.jboss.resteasy.reactive.RestQuery;

import ithome.cch.service.ProducerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

    @Inject
    ProducerService producerService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/myip")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIP(@RestQuery String uri) {
        return Response.ok(producerService.getIP(uri)).build();
    }
}
