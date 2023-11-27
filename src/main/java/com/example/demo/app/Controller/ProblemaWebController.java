package com.example.demo.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.Entity.Problema;
import com.example.demo.app.Repository.ProblemaRepository;
import com.example.demo.app.exception.NotFoundException;

@Controller
@RequestMapping("/problemas")
public class ProblemaWebController {
	@Autowired
    private ProblemaRepository probRepository;
	
	@GetMapping("/")
	public String probTemplate(Model model) {
        model.addAttribute("problemas", probRepository.findAll());
        return "problemas";
	}
	
	@GetMapping("/new")
    public String probNewTemplate(Model model) {
        model.addAttribute("problema", new Problema());
        return "problema-form";
    }
	
	@GetMapping("/edit/{id}")
    public String probEditTemplate(@PathVariable("id") String id, Model model) {
		Problema problema = probRepository.findById(id).orElseThrow(() -> new NotFoundException("Problema no encontrado"));
    	
    	model.addAttribute("problema",problema);

        // Asegúrate de que la competición se encuentra en el modelo
        model.addAttribute("problema", problema);
        return "problema-form";
    }

    @PostMapping("/save")
    public String probSaveProcess(@ModelAttribute("problema") Problema problema) {
        if (problema.getId().isEmpty()) {
        	problema.setId(null);
        }
        probRepository.save(problema);
        return "redirect:/clientes/";
    }

    @GetMapping("/delete/{id}")
    public String probDeleteProcess(@PathVariable("id") String id) {
    	probRepository.deleteById(id);
        return "redirect:/problemas/";
    }
}
