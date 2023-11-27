package com.example.demo.app.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.app.Repository.*;
import com.example.demo.app.Entity.Plato;
@Controller
@RequestMapping("")
public class Menu_clientes {

    @Autowired 
    private PlatoRepository platoRepository;
    @GetMapping("/MenuClientes")
	    public String listMenu(Model model) {
	        List<Plato> platos = platoRepository.findAll();
	        model.addAttribute("platos", platos);
	        return "Menu-Usuarios";
	    }
	    
}