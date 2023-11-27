package com.example.demo.app.Controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.app.exception.NotFoundException;
import com.example.demo.app.Entity.Admin;
import com.example.demo.app.Repository.AdminRepository;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminRestController {
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/")
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    @PutMapping("/{id}")
    public Admin updateadmin(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Admin coordinador = mapper.convertValue(body, Admin.class);
        coordinador.setId(id);
        return adminRepository.save(coordinador);
    }

    @DeleteMapping("/{id}")
    public Admin deleteadmin(@PathVariable String id) {
    	Admin admin = adminRepository.findById(id).orElseThrow(() -> new NotFoundException("Admin no encontrado"));
    	adminRepository.deleteById(id);
        return admin;
    }
    @GetMapping("/{id}")
    public Admin getadminById(@PathVariable String id) {
        return adminRepository.findById(id).orElseThrow(() -> new NotFoundException("Admin no encontrado"));
    }

    @PostMapping("/")
    public Admin saveadmin(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Admin admin = mapper.convertValue(body, Admin.class);
        return adminRepository.save(admin);
    }
}
