package org.acme;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.reactive.RestPath;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

@Path("/fruits")
@ApplicationScoped
public class ReactiveGreetingResource {

    @GET
    public Uni<List<Fruit>> listAll() {
        return Fruit.listAll();
    }

    @GET
    @Path("{id}")
    public Uni<Fruit> findById(@RestPath Long id) {
        return Fruit.findById(id);
    }

    @POST
    public Uni<Response> create(Fruit fruit) {
        return Panache.withTransaction(fruit::persist)
            .replaceWith(Response.ok(fruit).status(Status.CREATED)::build);
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@RestPath Long id) {
        return Panache.withTransaction(() -> Fruit.deleteById(id))
            .map(deleted -> deleted ? Response.ok().status(Status.NO_CONTENT).build()
                                    : Response.ok().status(Status.NOT_FOUND).build());
    }
}