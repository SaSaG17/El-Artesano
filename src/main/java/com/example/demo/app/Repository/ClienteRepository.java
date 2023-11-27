package com.example.demo.app.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.app.Entity.Cliente;

public interface ClienteRepository  extends MongoRepository<Cliente, String>{

	Cliente findByCorreoAndPassword(String correo, String password);
}
