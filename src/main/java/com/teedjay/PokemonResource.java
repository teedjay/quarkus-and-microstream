package com.teedjay;

import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pokemon")
@Produces(MediaType.APPLICATION_JSON)
public class PokemonResource {

    @Inject
    PokemonRepository repository;

    @GET
    public Response info() {
        return Response.ok(repository.pokemons()).build();
    }

    @GET
    @Path("/{id}")
    public Response info(@RestPath Long id) {
        return Response.ok(repository.pokemons().stream().filter(p -> id.equals(p.id())).findFirst().get()).build();
    }

}
