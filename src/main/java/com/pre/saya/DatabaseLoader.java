package com.pre.saya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final ComicArtistRepository repository;

    @Autowired
    public DatabaseLoader(ComicArtistRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new ComicArtist("@karory", "karory", "http://karory.net"));
    }

}
