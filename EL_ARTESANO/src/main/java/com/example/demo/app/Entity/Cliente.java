package com.example.demo.app.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;

@Document(collection = "cliente")
public class Cliente {
	
	@Id
	private String id;
	@NotEmpty
	private String correo;
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	private String numero;
	@NotEmpty
	private String direccion;
	
	public Cliente() {}
	
	public Cliente(String id, @NotEmpty String correo, @NotEmpty String password, @NotEmpty String nombre,
			@NotEmpty String apellido, @NotEmpty String numero, @NotEmpty String direccion) {
		super();
		this.id = id;
		this.correo = correo;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numero = numero;
		this.direccion = direccion;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
