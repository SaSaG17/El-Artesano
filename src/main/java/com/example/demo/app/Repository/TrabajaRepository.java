package com.example.demo.app.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.app.Entity.Trabajador;

public interface TrabajaRepository extends MongoRepository<Trabajador, String>{
	Trabajador findByCorreoAndId(String correo, String id);
}
