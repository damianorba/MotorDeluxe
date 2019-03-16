package es.urjc.code.motorDeluxe;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.util.*;

@Entity

public class Anuncio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id = -1;
	
	private String nombre;
	private String asunto;
	private String comentario;
	private Double precio;
	
	@OneToOne 
	private Usuario usuario;
	
	@OneToOne 
	private Coche coche;
	
	public Anuncio () {}

	public Anuncio(long id, String nombre, String asunto, Double precio, String comentario, Usuario usuario, Coche coche) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.asunto = asunto;
		this.precio = precio;
		this.comentario = comentario;
		this.usuario = new Usuario;
		this.coche = new Coche;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	@Override
	public String toString() {
		return "Anuncio [nombre=" + nombre + ", asunto=" + asunto + ", comentario=" + comentario + ", precio=" + precio
				+ "]";
	}
	
	
	
	
	
	
	

	
}
