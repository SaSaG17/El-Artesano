package com.example.demo.app.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.app.Entity.Pedido;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

}
 