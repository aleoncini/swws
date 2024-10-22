package org.acme.swws.rest;


import org.acme.click.model.CodiceFiscale;
import org.jboss.logging.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/cofis")
@Produces(MediaType.APPLICATION_JSON)
public class CodiceFiscaleResource {

    private static final Logger LOG = Logger.getLogger("COFIS");
    
    @GET
    @Path("isvalid/{cf}")
    public Boolean check(@PathParam("cf") String cf) {
        LOG.info("Verifica codice fiscale: " + cf);
        return new CodiceFiscale().setCodiceFiscale(cf).isValid();
    }

}
