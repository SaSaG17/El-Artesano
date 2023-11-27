package com.example.demo.app.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import jakarta.validation.constraints.NotEmpty;

@Document(collection = "pedido")
public class Pedido {
	
	@DocumentReference
	private Cliente usuario;
	@DocumentReference
	private Plato plato;
	
	@Id
	private String id;
	@NotEmpty
	private String Precio;
	
	public Pedido() {}
	
	public Pedido(Cliente usuario, Plato plato, String id, @NotEmpty String precio) {
		super();
		this.usuario = usuario;
		this.plato = plato;
		this.id = id;
		Precio = precio;
	}

	public Cliente getUsuario() {
		return usuario;
	}

	public void setUsuario(Cliente usuario) {
		this.usuario = usuario;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrecio() {
		return Precio;
	}

	public void setPrecio(String precio) {
		Precio = precio;
	}
	
	
}
