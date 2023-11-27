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

import com.example.demo.app.Entity.Cliente;
import com.example.demo.app.Repository.ClienteRepository;
import com.example.demo.app.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/cliente")
public class ClienteRestController {
	@Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/")
    public List<Cliente> getAllCliente() {
        return clienteRepository.findAll();
    }
    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = mapper.convertValue(body, Cliente.class);
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public Cliente deleteCliente(@PathVariable String id) {
    	Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    	clienteRepository.deleteById(id);
        return cliente;
    }
    @GetMapping("/{id}")
    public Cliente getclienteById(@PathVariable String id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    }

    @PostMapping("/")
    public Cliente saveCliente(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = mapper.convertValue(body, Cliente.class);
        return clienteRepository.save(cliente);
    }
}