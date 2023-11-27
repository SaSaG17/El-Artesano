package com.example.demo.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.types.ObjectId;
import com.example.demo.app.Entity.Plato;

import com.example.demo.app.Repository.PlatoRepository;
import com.example.demo.app.exception.*;



import java.util.List;

@Controller
@RequestMapping("")
public class PlatoWebController {

    @Autowired
    private PlatoRepository platoRepository;

    @GetMapping("/{id}")
    public String obtenerPlatoPorId(@PathVariable String id, Model model) {
        ObjectId objectId = new ObjectId(id);
        Plato plato = platoRepository.findById(objectId).orElse(null);

        model.addAttribute("plato", plato);
        return "plato";  // Corregir aquí para devolver la vista correcta
    }

    @GetMapping("/plato-list")
    public String listTemplate(Model model) {
        List<Plato> platos = platoRepository.findAll();
        model.addAttribute("platos", platos);
        return "plato-list";
    }

    @GetMapping("/plato-form")
    public String platoForm(Model model) {
        model.addAttribute("nuevoPlato", new Plato(null, null, null, 0, null)); // Agregar un objeto Plato al modelo
        return "plato-form";
    }

    @PostMapping("/plato-form")
    public String procesarPlatoForm(@ModelAttribute Plato nuevoPlato) {
        platoRepository.save(nuevoPlato);
        // Puedes redirigir a la página de detalles del nuevo plato u otra página según tus necesidades
        return "redirect:/" + nuevoPlato.getId().toString();
    }

    
    @GetMapping("/delete/{id}")
    public String platoDeleteProcess(@PathVariable("id") String id) {
        ObjectId objectId = new ObjectId(id);
        platoRepository.deleteById(objectId);
        return "redirect:/plato-list";
    }
    @GetMapping("/buscar")
    public String buscarPlatoPorNombre(@RequestParam("nombre") String nombre, Model model) {
        List<Plato> platosEncontrados = platoRepository.findByNombreContainingIgnoreCase(nombre);
        model.addAttribute("platos", platosEncontrados);
        return "plato-list";
    }
    
    /*
    @GetMapping("/edit/{id}")
    public String platoEditTemplate(@PathVariable("id") String id, Model model) {
        ObjectId objectId = new ObjectId(id);
        Plato plato = platoRepository.findById(objectId).orElse(null);

        model.addAttribute("nuevoPlato", plato);
        return "plato-edit";
    }
    @PostMapping("/edit/{id}")
    public String procesarEditarPlatoForm(@PathVariable("id") String id, @ModelAttribute Plato nuevoPlato, @RequestParam("imagen") MultipartFile imagen) {
        // Lógica para procesar la imagen y guardar el plato editado
        if (!imagen.isEmpty()) {
            // Procesa y guarda la nueva imagen
            String nombreImagen = guardarImagen(imagen);
            nuevoPlato.setImagenURL(nombreImagen);
        }

        // Actualiza el plato existente con los nuevos datos
        ObjectId objectId = new ObjectId(id);
        Plato platoExistente = platoRepository.findById(objectId).orElse(null);
        if (platoExistente != null) {
            platoExistente.setNombre(nuevoPlato.getNombre());
            platoExistente.setDescripcion(nuevoPlato.getDescripcion());
            platoExistente.setPrecio(nuevoPlato.getPrecio());

            platoRepository.save(platoExistente);
        } else {
            // Manejo de error, el plato no fue encontrado
        }

        // Redirige a la lista de platos después de la edición
        return "redirect:/plato-list";
    }

*/


    
}
