package com.example.demo.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.Entity.Pedido;
import com.example.demo.app.Entity.Trabajador;
import com.example.demo.app.Repository.PedidoRepository;
import com.example.demo.app.exception.NotFoundException;

@Controller 
@RequestMapping("/pedidos")
public class PedidoWebController {
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("/")
	public String pedidoTemplate(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "pedido-list";
	}

	@GetMapping("/new")
    public String pedidoNewTemplate(Model model) {
        model.addAttribute("pedido", new Pedido());
        return "pedido-form";
    }

    @GetMapping("/edit/{id}")
    public String pedidoEditTemplate(@PathVariable("id") String id, Model model) {
    	Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
    	
    	model.addAttribute("pedido",pedido);

        // Asegúrate de que la competición se encuentra en el modelo
        model.addAttribute("pedido", pedido);
        return "pedido-form";
    }

    @PostMapping("/save")
    public String pedidoSaveProcess(@ModelAttribute("pedido") Pedido pedido) {
        if (pedido.getId().isEmpty()) {
        	pedido.setId(null);
        }
        pedidoRepository.save(pedido);
        return "redirect:/pedidos/";
    }

    @GetMapping("/delete/{id}")
    public String pedidoDeleteProcess(@PathVariable("id") String id) {
    	pedidoRepository.deleteById(id);
        return "redirect:/pedidos/";
    }
}
