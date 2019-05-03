package es.urjc.code.motorDeluxe;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity

public class Anuncio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id = -1;
	private String nombre;
	private String asunto;
	
	@OneToOne
	private User usuario;
	
	@OneToOne
	private Coche coche;
	
	public Anuncio () {}

	public Anuncio(String nombre, String asunto) {
		super();
		this.nombre = nombre;
		this.asunto = asunto;
		this.usuario = new User ();
		this.coche = new Coche();
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

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
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
		return "Anuncio [nombre=" + nombre + ", asunto=" + asunto + "]";
	}
	
	
	
	
	
	
	

	
}
