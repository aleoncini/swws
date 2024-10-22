package org.acme.swws.rest;

import org.acme.swws.model.Protocollo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/urp")
public class ProcolloResource {

    @GET
    @Path("/protocollo/{size}")
    @Produces(MediaType.TEXT_PLAIN)
    public String richiediProtocollo(@PathParam("size") int size) {
        return new Protocollo().assegnaCodice(size);
    }

}
