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
@RequestMapping(value = "/api/pedidos")
public class PedidoRestController {
	@Autowired
    private PedidoRepository pedidoRepository;
	
	@GetMapping("/")
    public List<Pedido> getAllPedido() {
        return pedidoRepository.findAll();
    }

    @PutMapping("/{id}")
    public Pedido updatepedido(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Pedido pedido = mapper.convertValue(body, Pedido.class);
        pedido.setId(id);
        return pedidoRepository.save(pedido);
    }

    @DeleteMapping("/{id}")
    public Pedido deletepedido(@PathVariable String id) {
    	Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
    	pedidoRepository.deleteById(id);
        return pedido;
    }
    @GetMapping("/{id}")
    public Pedido getpedidoById(@PathVariable String id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
    }

    @PostMapping("/")
    public Pedido savepedido(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Pedido pedido = mapper.convertValue(body, Pedido.class);
        return pedidoRepository.save(pedido);
    }
}
