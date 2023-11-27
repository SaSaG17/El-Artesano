package com.example.demo.app.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.Entity.Trabajador;
import com.example.demo.app.Repository.*;
@Controller
@RequestMapping("")
public class MapeoModulos {
	@Autowired
    private TrabajaRepository trabajadorRepository;

	@PostMapping("/login")
    public String login(@RequestParam("correo") String correo, @RequestParam("id") String id, Model model) {
    	List<Trabajador> trabajadorList = trabajadorRepository.findAll();

    	Trabajador trabajador= trabajadorRepository.findByCorreoAndId(correo, id);
        model.addAttribute("trabajadores", trabajador);
        if (trabajador != null) {
          return "redirect:/EmpleadoMenu"; 
        } else {
        	 model.addAttribute("authenticationFailed", true);
             model.addAttribute("errorMessage", "Correo o contraseña incorrectos");
            return "TrabajadorModulos";
        }
    }
}