package org.acme;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.jboss.resteasy.reactive.RestPath;

import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
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
    @ReactiveTransactional
    public Uni<Fruit> create(Fruit fruit) {
        return Fruit.persist(fruit).replaceWith(fruit);
    }

    @DELETE
    @Path("{id}")
    @ReactiveTransactional
    public Uni<Void> delete(@RestPath Long id) {
        return Fruit.deleteById(id).replaceWithVoid();
        
        
    }
}