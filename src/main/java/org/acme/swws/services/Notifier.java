package org.acme.swws.services;

import org.acme.swws.model.CloudEvent;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("")
@RegisterRestClient()
public interface Notifier {
    
    @POST
    //@Produces(MediaType.TEXT_PLAIN)
    void inform(CloudEvent event);
}