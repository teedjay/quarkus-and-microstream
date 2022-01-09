package com.teedjay;

import one.microstream.storage.types.StorageManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;

@ApplicationScoped
public class PokemonRepository {

    @Inject
    StorageManager storageManager;

    public DataRoot root() {
        return (DataRoot) storageManager.root();
    }

    public Set<Pokemon> pokemons() {
        return root().pokemons;
    }

    public void add(Pokemon pokemon) {
        root().pokemons.add(pokemon);
        storageManager.store(root().pokemons);
    }

}
