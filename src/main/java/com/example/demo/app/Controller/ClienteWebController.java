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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.Repository.ClienteRepository;
import com.example.demo.app.Entity.Cliente;
import com.example.demo.app.exception.NotFoundException;

@Controller
@RequestMapping("/clientes")
public class ClienteWebController {
	@Autowired
    private ClienteRepository clienteRepository;
	
	@GetMapping("/")
	public String clienteTemplate(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "inicio";
	}
	
	@GetMapping("/lista")
    public String clienteListTemplate(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "cliente-list";
    }

    @GetMapping("/new")
    public String clienteNewTemplate(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    @GetMapping("/edit/{id}")
    public String clienteEditTemplate(@PathVariable("id") String id, Model model) {
    	Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    	
    	model.addAttribute("cliente",cliente);

        // Asegúrate de que la competición se encuentra en el modelo
        model.addAttribute("cliente", cliente);
        return "cliente-form";
    }

    @PostMapping("/save")
    public String clienteSaveProcess(@ModelAttribute("cliente") Cliente cliente) {
        if (cliente.getId().isEmpty()) {
        	cliente.setId(null);
        }
        clienteRepository.save(cliente);
        return "redirect:/clientes/lista";
    }

    @GetMapping("/delete/{id}")
    public String clienteDeleteProcess(@PathVariable("id") String id) {
    	clienteRepository.deleteById(id);
        return "redirect:/clientes/lista";
    }
    
    @PostMapping("/ingreso")
    public String login(@RequestParam("correo") String correo, @RequestParam("password") String password, Model model) {
    	List<Cliente> clienteList = clienteRepository.findAll();

    	Cliente cliente= clienteRepository.findByCorreoAndPassword(correo, password);
        model.addAttribute("clientes", cliente);
        if (cliente != null) {
          return "ClienteModulos"; 
        } else {
        	 model.addAttribute("authenticationFailed", true);
             model.addAttribute("errorMessage", "Correo o contraseña incorrectos");
            return "inicio";
        }
    }
    /*@GetMapping("/Menu-User")
    public String clienteMenuTemplate(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "Menu-usuario";
    }
    
    */
    
}
