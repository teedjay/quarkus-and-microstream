package com.teedjay.microstream;

import com.teedjay.DataRoot;
import com.teedjay.Pokemon;
import io.quarkus.logging.Log;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import one.microstream.storage.types.StorageManager;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class StorageManagerLifeCycle {

    @Inject
    StorageManager storageManager;

    @PostConstruct
    public void loadDataSet() {
        var datasetName = "pokedex.json";
        DataRoot dataset = (DataRoot) storageManager.root();
        dataset.pokemons.add(new Pokemon(1L, "001", "Bulbasaur"));
        dataset.pokemons.add(new Pokemon(2L, "002", "Ivysaur"));
        storageManager.storeRoot();
        Log.infov("MicroStream loaded dataset named ''{0}'' with {1} records", datasetName, dataset.pokemons.size());
    }

    public void onStartup(@Observes StartupEvent event) {
        Log.infov("MicroStream has started = {0}", storageManager.isRunning());
    }

    public void onShutdown(@Observes ShutdownEvent event) {
        Log.infov("MicroStream has stopped = {0}", storageManager.shutdown());
    }

}
