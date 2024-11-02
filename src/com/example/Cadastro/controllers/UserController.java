package com.example.Cadastro.controllers;

import com.example.Cadastro.models.User;
import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository repository;
    @GetMapping
    public ResponseEntity<List<User>> findAllClient(){
        List<User> allUser = repository.findAll();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> postClient(User user){
        user.setId(UUID.randomUUID());
        repository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(value = "/findbyid/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable UUID id){
        Optional<User> user = repository.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/findbyname/{name}")
    public ResponseEntity<Optional<User>> findByName(@PathVariable String name){
        Optional<User> user = repository.findByName(name);
        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateClient(@RequestBody User user, @PathVariable UUID id){
        Optional<User> user1 = repository.findById(id);
        if(user1.isPresent()){
            User intermedUser = user1.get();

            user.setId(intermedUser.getId());
           User userUpdate = repository.save(user);
            return new ResponseEntity<>(userUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteChild(@PathVariable UUID id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
