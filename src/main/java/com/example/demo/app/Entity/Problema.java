package com.example.demo.app.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Document(collection = "problema")
public class Problema {

	@Id
	private String id;
	@NotEmpty
	private String nomcliente;
	@NotEmpty
	private String numpedido;
	@NotEmpty
	private String asunto;
	@NotEmpty
	private String texto;
	@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fecha;
	
	public Problema() {}

	public Problema(String id, @NotEmpty String nomcliente, @NotEmpty String numpedido, @NotEmpty String asunto,
			@NotEmpty String texto, @NotNull String fecha) {
		super();
		this.id = id;
		this.nomcliente = nomcliente;
		this.numpedido = numpedido;
		this.asunto = asunto;
		this.texto = texto;
		this.fecha = fecha;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getNomcliente() {
		return nomcliente;
	}
	public void setNomcliente(String nomcliente) {
		this.nomcliente = nomcliente;
	}

	public String getNumpedido() {
		return numpedido;
	}
	public void setNumpedido(String numpedido) {
		this.numpedido = numpedido;
	}

	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}