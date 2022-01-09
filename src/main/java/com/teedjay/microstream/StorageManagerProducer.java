package com.teedjay.microstream;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import com.teedjay.DataRoot;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import one.microstream.storage.types.StorageManager;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.nio.file.FileSystem;

@Singleton
public class StorageManagerProducer {

    final DataRoot root = new DataRoot();
    final FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
    final EmbeddedStorageManager storageManager = EmbeddedStorage.start(root, fs.getPath("storage"));

    @Produces
    public StorageManager createStorageManager() {
        return storageManager;
    }

}
