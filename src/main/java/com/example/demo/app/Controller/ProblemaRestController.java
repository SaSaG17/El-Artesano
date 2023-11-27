package com.example.demo.app.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.app.Entity.Problema;
import com.example.demo.app.Repository.ProblemaRepository;
import com.example.demo.app.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/problemas")
public class ProblemaRestController {
	@Autowired
    private ProblemaRepository probRepository;
	
	@GetMapping("/")
    public List<Problema> getAllProblema() {
        return probRepository.findAll();
    }
	
	@PutMapping("/{id}")
    public Problema updateProblema(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Problema problema = mapper.convertValue(body, Problema.class);
        problema.setId(id);
        return probRepository.save(problema);
    }

    @DeleteMapping("/{id}")
    public Problema deleteProblema(@PathVariable String id) {
    	Problema problema = probRepository.findById(id).orElseThrow(() -> new NotFoundException("Petición no encontrada"));
    	probRepository.deleteById(id);
        return problema;
    }
    @GetMapping("/{id}")
    public Problema getProblemaById(@PathVariable String id) {
        return probRepository.findById(id).orElseThrow(() -> new NotFoundException("Petición no encontrada"));
    }

    @PostMapping("/")
    public Problema saveProblema(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Problema problema = mapper.convertValue(body, Problema.class);
        return probRepository.save(problema);
    }
}