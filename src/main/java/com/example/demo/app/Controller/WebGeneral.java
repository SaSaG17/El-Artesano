package com.example.demo.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/informacion")
public class WebGeneral {
	@GetMapping
    public String mostrarInformacion() {
        // Lógica para obtener los datos que se mostrarán en la página
        return "informacion";
    }
}
