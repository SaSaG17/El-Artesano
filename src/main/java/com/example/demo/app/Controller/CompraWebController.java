package com.example.demo.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.Entity.*;
import com.example.demo.app.Repository.*;
import com.example.demo.app.exception.NotFoundException;

@Controller 
@RequestMapping("/compras")
public class CompraWebController {
	@Autowired
    private PedidoRepository compraRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PlatoRepository platoRepository;
	
	@GetMapping("/")
	public String compraListTemplate(Model model) {
		model.addAttribute("compras", compraRepository.findAll());
		return "pedido-list";
	}

	@GetMapping("/new")
	public String compraNewTemplate(Model model, Model clienteModel, Model platoModel) {
		List<Cliente> clientes = clienteRepository.findAll();
		List<Plato> platosDisponibles = platoRepository.findAll();

		clienteModel.addAttribute("clientes", clientes);
		platoModel.addAttribute("platosDisponibles", platosDisponibles);
		model.addAttribute("compra", new Pedido());
		return "pedido-form";
	}

	@GetMapping("/edit/{id}")
	public String compraEditTemplate(@PathVariable("id") String id, Model model, Model clienteModel, Model platoModel) {
		model.addAttribute("compra",compraRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado")));
		List<Cliente> clientes = clienteRepository.findAll();
		List<Plato> platosDisponibles = platoRepository.findAll();

		clienteModel.addAttribute("clientes", clientes);
		platoModel.addAttribute("platosDisponibles", platosDisponibles);
		return "pedido-form";
	}

	@PostMapping("/save")
	public String comprasSaveProcess(@ModelAttribute("compra") Pedido compra) {
		if (compra.getId().isEmpty()) {
			compra.setId(null);
		}
		compraRepository.save(compra);
		return "redirect:/compras/";
	}

	@GetMapping("/delete/{id}")
	public String compraDeleteProcess(@PathVariable("id") String id) {
		compraRepository.deleteById(id);
		return "redirect:/compras/";
	}
}
