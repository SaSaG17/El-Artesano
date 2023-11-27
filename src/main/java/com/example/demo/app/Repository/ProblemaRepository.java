package com.example.demo.app.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.app.Entity.Problema;

public interface ProblemaRepository extends MongoRepository<Problema, String>{

}
