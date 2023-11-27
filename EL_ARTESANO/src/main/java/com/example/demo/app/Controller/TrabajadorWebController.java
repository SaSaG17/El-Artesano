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

import com.example.demo.app.Repository.TrabajaRepository;
import com.example.demo.app.Entity.Trabajador;
import com.example.demo.app.exception.NotFoundException;

@Controller
@RequestMapping("/trabajadores")
public class TrabajadorWebController {
	@Autowired
    private TrabajaRepository trabajadorRepository;
	
	@GetMapping("/")
	public String adminTemplate(Model model) {
        model.addAttribute("trabajadores", trabajadorRepository.findAll());
        return "TrabajadorInicio";
	}
	
	@GetMapping("/lista")
    public String trabajadorListTemplate(Model model) {
        model.addAttribute("trabajadores", trabajadorRepository.findAll());
        return "trabajador-list";
    }

    @GetMapping("/new")
    public String trabajadorNewTemplate(Model model) {
        model.addAttribute("trabajador", new Trabajador());
        return "trabajador-form";
    }

    @GetMapping("/edit/{id}")
    public String trabajadorEditTemplate(@PathVariable("id") String id, Model model) {
    	Trabajador trabajador = trabajadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Trabajador no encontrado"));
    	
    	model.addAttribute("trabajador",trabajador);

        // Asegúrate de que la competición se encuentra en el modelo
        model.addAttribute("trabajador", trabajador);
        return "trabajador-form";
    }

    @PostMapping("/save")
    public String trabajadorSaveProcess(@ModelAttribute("trabajador") Trabajador trabajador) {
        if (trabajador.getId().isEmpty()) {
        	trabajador.setId(null);
        }
        trabajadorRepository.save(trabajador);
        return "redirect:/trabajadores/lista";
    }

    @GetMapping("/delete/{id}")
    public String trabajadorDeleteProcess(@PathVariable("id") String id) {
    	trabajadorRepository.deleteById(id);
        return "redirect:/trabajadores/lista";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam("correo") String correo, @RequestParam("telefono") String telefono, Model model) {
    	List<Trabajador> trabajadorList = trabajadorRepository.findAll();

    	Trabajador trabajador= trabajadorRepository.findByCorreoAndTelefono(correo, telefono);
        model.addAttribute("trabajadores", trabajador);
        if (trabajador != null) {
          return "redirect:/EmpleadoMenu"; 
        } else {
        	 model.addAttribute("authenticationFailed", true);
             model.addAttribute("errorMessage", "Correo o contraseña incorrectos");
            return "TrabajadorInicio";
        }
    }
}
