package com.example.demo.app.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plato")
public class Plato {
	
	public Plato(ObjectId id, String nombre, String descripcion, double precio, String imagenURL) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagenURL = imagenURL;
	}
	private ObjectId id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagenURL;
    
    public String getImagenURL() {
		return imagenURL;
	}

	public void setImagenURL(String imagenURL) {
		this.imagenURL = imagenURL;
	}
    
    public ObjectId getId() {
  		return id;
  	}
  	public void setId(ObjectId id) {
  		this.id = id;
  	}
  	public String getNombre() {
  		return nombre;
  	}
  	public void setNombre(String nombre) {
  		this.nombre = nombre;
  	}
  	public String getDescripcion() {
  		return descripcion;
  	}
  	public void setDescripcion(String descripcion) {
  		this.descripcion = descripcion;
  	}
  	public double getPrecio() {
  		return precio;
  	}
  	public void setPrecio(double precio) {
  		this.precio = precio;
  	}
}
