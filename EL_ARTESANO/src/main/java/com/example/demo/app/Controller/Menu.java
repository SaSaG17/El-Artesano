package com.example.demo.app.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/Menu")
public class Menu {
	@GetMapping
    public String MenuList() {
        // Lógica para obtener los datos que se mostrarán en la página de usuarios
        return "AdminModulos";
    }
}