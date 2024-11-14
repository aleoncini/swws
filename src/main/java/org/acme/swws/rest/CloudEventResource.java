package org.acme.swws.rest;

import org.acme.swws.model.CloudEvent;
import org.acme.swws.model.Subscription;
import org.acme.swws.services.EventManagement;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("/events")
public class CloudEventResource {

    private static final Logger LOG = Logger.getLogger("EVENT_BROKER");

    @Inject
    EventManagement broker;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String info() {
        return "[CloudEvent endpoint] Serverless Workflow WorkShop - ReST service for cloud events";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CloudEvent publish(@QueryParam("type") String type, @QueryParam("data") String data, @QueryParam("src") String src, @Context UriInfo uriInfo) {
        if (type == null) {
            type = "generic";
        }
        if (data == null) {
            data = "empty";
        }
        if (src == null) {
            src = uriInfo.getBaseUri().toString();
        }
        CloudEvent event = new CloudEvent()
            .withType(type)
            .withSource(src)
            .withData(data);
        event.persist();
        broker.notify(event);
        LOG.info("[EVENT BROKER] new event from: " + event.getSource());
        return event;
    }

    @POST
    @Path("/subscribe")
    @Produces(MediaType.TEXT_PLAIN)
    public String subscribe(Subscription subscription) {
        broker.subscribe(subscription);
        return "OK";
    }

}
