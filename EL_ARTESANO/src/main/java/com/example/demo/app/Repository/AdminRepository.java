package com.example.demo.app.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.app.Entity.Admin;

public interface AdminRepository extends MongoRepository<Admin, String>{
	
	Admin findByCorreoAndPassword(String correo, String password);
}
