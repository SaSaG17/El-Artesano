package com.example.demo.app.Entity;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.util.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Document(collection = "pedido")
public class Pedido {
	@DocumentReference
	private List<Plato> plato;
	@DocumentReference
	private Cliente cliente;
	
	private Plato plato2;
	
	@Id
	private String id;
	@NotEmpty
	private String precio;
	@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fechaPedido;  // fecha del pedido
	    
    public Pedido() {
        // Constructor sin argumentos
    }

	public Pedido(List<Plato> plato, Cliente cliente, String id, @NotEmpty String precio,
			@NotNull String fechaPedido) {
		super();
		this.plato = plato;	
		this.cliente = cliente;
		this.id = id;
		this.precio = precio;
		this.fechaPedido = fechaPedido;
	}

	public List<Plato> getPlato() {
		return plato;
	}
	public void setPlato(List<Plato> plato) {
		this.plato = plato;
	}

	public Plato getPlato2() {
		return plato2;
	}
	public void setPlato(Plato plato2) {
		this.plato2 = plato2;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
}