package org.acme.swws.rest;

import java.util.List;

import org.acme.swws.model.Pratica;
import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/pratiche")
public class PraticaResource {

    private static final Logger LOG = Logger.getLogger("EVENT_BROKER");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pratica> list() {
        return Pratica.listAll();
    }

    @GET
    @Path("/tba")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pratica> listToBeApproved() {
        return Pratica.list("approved", 0);
    }

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public Pratica save(@QueryParam("pec") String pec, @QueryParam("fonte") String fonte, @QueryParam("note") String note, @QueryParam("oe") String oe) {
        Pratica pratica = new Pratica();

        if (pec != null) {
            pratica.pec = pec;
        }
        if (fonte != null) {
            pratica.fonte = fonte;
        }
        if (note != null) {
            pratica.note = note;
        }
        if (oe != null) {
            pratica.operatoreEconomico = oe;
        }

        pratica.persist();

        LOG.info("[GESTIONE PRATICHE] aperta nuova pratica: " + pratica.id);
        return pratica;
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pratica update(@PathParam("id") String id, @QueryParam("status") String status, @QueryParam("pec") String pec, @QueryParam("fonte") String fonte, @QueryParam("note") String note, @QueryParam("oe") String oe, @QueryParam("approved") String approved) {
        Pratica pratica = Pratica.findById(new ObjectId(id));

        if (pratica == null) {
            throw new NotFoundException();
        }

        if (status != null) {
            pratica.status = status;
        }
        if (pec != null) {
            pratica.pec = pec;
        }
        if (fonte != null) {
            pratica.fonte = fonte;
        }
        if (note != null) {
            pratica.note = note;
        }
        if (oe != null) {
            pratica.operatoreEconomico = oe;
        }
        if (approved != null) {
            if (approved.equalsIgnoreCase("yes")) {
                pratica.approved = 1;
            }
            if (approved.equalsIgnoreCase("no")) {
                pratica.approved = -1;
            }
        }

        pratica.update();

        LOG.info("[GESTIONE PRATICHE] aggiornati dati della pratica: " + pratica.id);
        return pratica;
    }
}