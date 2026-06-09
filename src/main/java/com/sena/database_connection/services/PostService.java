package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.database_connection.entities.Post;
import com.sena.database_connection.repositories.PostRepository;

@Service
public class PostService {

    private PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> obetenerTodos() {
        return this.repository.findAll();
    }

    public Optional<Post> porId(Long id) {
        return this.repository.findById(id);
    }

    public Post crear(Post post) {
        return this.repository.save(post);
    }

    public Post actualizar(Post post) {

        Optional<Post> postFound = this.porId(post.getId());

        if (postFound.isEmpty()) {
            return null;
        }

        return this.repository.save(post);
    }

    public Post eliminar(Long id) {

        Optional<Post> postFound = this.porId(id);

        if (postFound.isEmpty()) {
            return null;
        }

        this.repository.delete(postFound.get());

        return postFound.get();
    }
}
