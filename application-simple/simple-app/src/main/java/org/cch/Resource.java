package org.cch;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

    @Inject
    Logger logger;
    

    @Path("")
    @GET
    public Response getHostname() {
        try {
            InetAddress iaAddress = InetAddress.getLocalHost();
            return Response.ok(iaAddress.getHostName()).build();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            logger.error("Get Host Name Error.");
            e.printStackTrace();
        }  
        return Response.status(Status.BAD_GATEWAY).build();
    }

    @Path("/ip")
    @GET
    public Response getIp() {
        try {
            InetAddress iaAddress = InetAddress.getLocalHost();
            String currentIp = iaAddress.getHostAddress();
            return Response.ok(currentIp).build();
        } catch (UnknownHostException e) {
            logger.error("Get IP address Error.");
            e.printStackTrace();
        }  
        return Response.status(Status.BAD_GATEWAY).build();
    }

    @Path("/version")
    @GET
    public Response getVersion() {
        String tag = System.getProperty("GIT_TAG", "1.0.0-SNAPSHOT");

        return Response.ok(tag).build();
        
    }

}
