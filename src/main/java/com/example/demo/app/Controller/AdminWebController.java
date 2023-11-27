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

import com.example.demo.app.Entity.Admin;
import com.example.demo.app.Repository.AdminRepository;
import com.example.demo.app.exception.NotFoundException;

@Controller 
@RequestMapping("/admins")
public class AdminWebController {
	@Autowired
	private AdminRepository adminRepository;

	@GetMapping("/")
	public String adminTemplate(Model model) {
        model.addAttribute("admins", adminRepository.findAll());
        return "AdminInicio";
	}
	
	@GetMapping("/lista")
	public String AdminListTemplate(Model model) {
		model.addAttribute("admins", adminRepository.findAll());
		return "Admin-list";
	}

	@GetMapping("/new")
	public String AdminsNewTemplate(Model model) {
		model.addAttribute("admin", new Admin());
		return "Admin-form";
	}

	@GetMapping("/edit/{id}")
	public String AdminEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("admin",
				adminRepository.findById(id).orElseThrow(() -> new NotFoundException("Admin no encontrado")));
		return "admin-form";
	}

	@PostMapping("/save")
	public String AdminSaveProcess(@ModelAttribute("usuario") Admin admin) {
		if (admin.getId().isEmpty()) {
			admin.setId(null);
		}
		adminRepository.save(admin);
		return "redirect:/admins/lista";
	}
	
	@PostMapping("/salvar")
	public String AdminSalvarProcess(@ModelAttribute("usuario") Admin admin) {
		if (admin.getId().isEmpty()) {
			admin.setId(null);
		}
		adminRepository.save(admin);
		return "redirect:/admins/lista";
	}
	

	@GetMapping("/delete/{id}")
	public String AdminDeleteProcess(@PathVariable("id") String id) {
		adminRepository.deleteById(id);
		return "redirect:/admins/lista";
	}

	@GetMapping("/registro")
	public String registroTemplate(Model model) {
		model.addAttribute("coordinador", new Admin());
		return "registro-admin";
	}

	@PostMapping("/ingresar")
	public String login(@RequestParam("correo") String correo, @RequestParam("password") String password, Model model) {
		System.out.println("Usuario: " + correo + " Password:" + password);
		List<Admin> AdminList = adminRepository.findAll();
		System.out.println(AdminList.get(0).getCorreo());
	Admin admin = adminRepository.findByCorreoAndPassword(correo, password);
		if (admin != null) {
			System.out.println("Usuario: " + admin.getCorreo() + " Password:" + admin.getPassword());
			return "redirect:/Menu"; 
		} else {
			model.addAttribute("authenticationFailed", true);
			model.addAttribute("errorMessage", "Usuario o contraseña incorrectos");
			return "AdminInicio";
		}
	}
}
