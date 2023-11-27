package com.example.demo.app.Repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.app.Entity.Plato;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlatoRepository extends MongoRepository<Plato, ObjectId> {
    List<Plato> findByNombreContainingIgnoreCase(String nombre);
    List<Plato> findByDescripcionContainingIgnoreCase(String descripcion);
}