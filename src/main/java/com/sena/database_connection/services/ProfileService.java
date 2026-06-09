package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.database_connection.entities.Profile;
import com.sena.database_connection.repositories.ProfileRepository;

@Service
public class ProfileService {

    private ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public List<Profile> obetenerTodos() {
        return this.repository.findAll();
    }

    public Optional<Profile> porId(Long id) {
        return this.repository.findById(id);
    }

    public Profile crear(Profile profile) {
        return this.repository.save(profile);
    }

    public Profile actualizar(Profile profile) {

        Optional<Profile> profileFound = this.porId(profile.getId());

        if (profileFound.isEmpty()) {
            return null;
        }

        return this.repository.save(profile);
    }

    public Profile eliminar(Long id) {

        Optional<Profile> profileFound = this.porId(id);

        if (profileFound.isEmpty()) {
            return null;
        }

        this.repository.delete(profileFound.get());

        return profileFound.get();
    }
}
