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

import com.example.demo.app.Entity.Pedido;
import com.example.demo.app.Repository.PedidoRepository;
import com.example.demo.app.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/compras")
public class CompraRestController {
	@Autowired
	private PedidoRepository compraRepository;
	
	@GetMapping("/")
    public List<Pedido> getAllPedidos() {
        return compraRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido getCompraById(@PathVariable String id) {
        return compraRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
    }

    @PostMapping("/")
    public Pedido saveCompra(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Pedido compra = mapper.convertValue(body, Pedido.class);
        return compraRepository.save(compra);
    }

    @PutMapping("/{id}")
    public Pedido updateCompra(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Pedido compra = mapper.convertValue(body, Pedido.class);
        compra.setId(id);
        return compraRepository.save(compra);
    }

    @DeleteMapping("/{id}")
    public Pedido deleteCompra(@PathVariable String id) {
    	Pedido compra = compraRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
    	compraRepository.deleteById(id);
        return compra;
    }
}
