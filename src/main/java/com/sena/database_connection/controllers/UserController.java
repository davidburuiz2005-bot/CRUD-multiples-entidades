package com.sena.database_connection.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.database_connection.dtos.UserDto;
import com.sena.database_connection.entities.User;
import com.sena.database_connection.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    // Servicio encargado de la lógica de usuarios
    private UserService service;

    // Constructor con inyección de dependencias
    public UserController(UserService service) {
        this.service = service;
    }

    // Endpoint para obtener todos los usuarios
    @GetMapping()
    public List<User> get() {
        return this.service.obetenerTodos();
    }

    // Endpoint para obtener un usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        Optional<User> user = this.service.porId(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(user.get());
    }

    // Endpoint para crear un usuario
    @PostMapping
    public User create(@RequestBody UserDto body) {

        // Se crea una nueva instancia de User con los datos recibidos
        /*
         * User user = new User(
         * null,
         * body.getName(),
         * body.getEmail(),
         * body.getAge(),
         * body.getPhone());
         */

        User user = new User();

        user.setName(body.getName());
        user.setEmail(body.getEmail());
        user.setAge(body.getAge());
        user.setPhone(body.getPhone());

        return this.service.crear(user);
    }

    // Endpoint para actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody UserDto body) {

        // Se crea una instancia de User con el id recibido
        // y los nuevos datos enviados en el body
        /*
         * User user = new User(
         * id,
         * body.getName(),
         * body.getEmail(),
         * body.getAge(),
         * body.getPhone());
         */

        User user = new User();

        user.setId(id);
        user.setName(body.getName());
        user.setEmail(body.getEmail());
        user.setAge(body.getAge());
        user.setPhone(body.getPhone());

        User userUptaded = this.service.actualizar(user);

        if (userUptaded == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(userUptaded);

    }

    // Endpoint para eliminar un usuario por id
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {

        User userDeleted = this.service.eliminar(id);

        if (userDeleted == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(userDeleted);
    }
}