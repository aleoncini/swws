package org.acme.swws.services;

import org.acme.swws.model.Subscription;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/events")
@RegisterRestClient(baseUri = "http://localhost:8080")
public interface Subscriber {
    
    @POST
    @Path("/subscribe")
    @Produces(MediaType.TEXT_PLAIN)
    String subscribe(Subscription subscription);
}