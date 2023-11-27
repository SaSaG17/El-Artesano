package com.example.demo.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;
import com.example.demo.app.Entity.Plato;
import com.example.demo.app.Repository.PlatoRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/platos")
public class PlatoController {

    @Autowired
    private PlatoRepository platoRepository;

    @GetMapping
    public List<Plato> obtenerPlatos() {
        return platoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plato> obtenerPlatoPorId(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        Optional<Plato> plato = platoRepository.findById(objectId);
        return plato.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plato> agregarPlato(@RequestBody Plato plato) {
        Plato nuevoPlato = platoRepository.save(plato);
        return ResponseEntity.ok(nuevoPlato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plato> editarPlato(@PathVariable String id, @RequestBody Plato plato) {
        ObjectId objectId = new ObjectId(id);
        if (!platoRepository.existsById(objectId)) {
            return ResponseEntity.notFound().build();
        }
        plato.setId(objectId);
        Plato platoActualizado = platoRepository.save(plato);
        return ResponseEntity.ok(platoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlato(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        if (!platoRepository.existsById(objectId)) {
            return ResponseEntity.notFound().build();
        }
        platoRepository.deleteById(objectId);
        return ResponseEntity.noContent().build();
    }
    
   
}