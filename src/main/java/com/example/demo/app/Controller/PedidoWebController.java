package com.example.demo.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import com.example.demo.app.Entity.Pedido;
import com.example.demo.app.Entity.Plato;
import com.example.demo.app.Repository.PedidoRepository;
import com.example.demo.app.Repository.PlatoRepository;
import com.example.demo.app.exception.NotFoundException;
import org.bson.types.ObjectId;
import java.util.Optional; // Si estás usando Optional en tu código

	@Controller
	@RequestMapping("/pedido")
	@SessionAttributes({"carrito", "pedido"})// Nombre del atributo de sesión
	public class PedidoWebController {
		
	    @Autowired
	    private PedidoRepository pedidoRepository;
	    @Autowired
	    private PlatoRepository platoRepository;
	    
	    @GetMapping("/")
		public String pedidoTemplate(Model model) {
	        model.addAttribute("pedidos", pedidoRepository.findAll());
	        return "pedido-list";
		}

	    @GetMapping("/form/{platoId}")
	    public String mostrarFormularioPedido(@PathVariable String platoId, Model model) {
	    ObjectId objectId = new ObjectId(platoId);
	    Plato plato2 = platoRepository.findById(objectId).orElseThrow(() -> new NotFoundException("Plato no encontrado"));

	        // Crear un nuevo pedido o recuperar uno existente (depende de tu lógica de negocio)
	        Pedido pedido = new Pedido(); // Aquí podrías implementar tu lógica para crear un nuevo pedido

	        // Asignar el plato al pedido
	        pedido.setPlato(plato2);

	        // Añadir el pedido al modelo para usarlo en la vista
	        if (pedido != null) {
	            model.addAttribute("pedido", pedido);
	        }

	        return "pedido-form"; // Nombre de la vista donde se mostrará el formulario del pedido
	    }

	    @PostMapping("/agregarAlPedido")
	    public String agregarAlPedido(@ModelAttribute("pedido") Pedido pedido, Model model) {
	        // Obtener el pedido actual del modelo
	        Pedido pedidoActual = (Pedido) model.getAttribute("pedido");

	        // Verificar si ya hay un pedido en el modelo
	        if (pedidoActual != null) {
	            // Lógica para agregar el plato al pedido actual
	            // Supongamos que tienes el ID del plato que se quiere agregar
	            ObjectId platoId = new ObjectId("ID_DEL_PLATO");
	            Plato plato2 = platoRepository.findById(platoId)
	                .orElseThrow(() -> new NotFoundException("Plato no encontrado"));

	            // Actualizar el pedido en el modelo
	            model.addAttribute("pedido", pedidoActual);
	        } else {
	            // Lógica para manejar si no hay un pedido en el modelo
	            // ...
	        }

	        // Redireccionar a alguna página después de agregar el producto al pedido
	        return "redirect:/pedido-list"; // Página donde se lista el pedido o se confirma la adición al pedido
	    }
	    
	    @PostMapping("/agregarAlCarrito")
	    public String agregarAlCarrito(@RequestParam("id") String id, Model model)
	    
	    	 {
	    	System.out.println("ID del plato recibido: " + id);
	    	ObjectId objectId = new ObjectId(id);
	        Plato plato = platoRepository.findById(objectId).orElse(null); 
	        
	        if (plato != null) {
	            // Verificar si el carrito existe en el modelo
	            Pedido carrito = (Pedido) model.getAttribute("carrito");
	            if (carrito == null) {
	                carrito = new Pedido(); // O la lógica para inicializar un carrito vacío
	            }
	            // Guardar el carrito actualizado en el modelo
	            model.addAttribute("carrito", carrito);
	        }
	       
	        // Redirigir a donde se muestran los platos después de agregar al carrito
	        return "redirect:/pedido-form"; // Ajusta la ruta según tu aplicación
	    }
	}
    

