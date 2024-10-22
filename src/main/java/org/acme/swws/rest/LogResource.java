package org.acme.swws.rest;

import org.acme.swws.model.Log;
import org.jboss.logging.Logger;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("/logs")
public class LogResource {

    private static final Logger LOG = Logger.getLogger("SWWS_LOGS");

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String info() {
        return "[Serverless Workflow WorkShop] Log Service - ReST service for cloud events";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Log log(Log log, @Context UriInfo uriInfo) {
        LOG.info("[FROM: " + uriInfo.getAbsolutePath().toString() + "] - [LEVEL: " + log.level + "] - "  + log.message);
        return log;
    }


}
