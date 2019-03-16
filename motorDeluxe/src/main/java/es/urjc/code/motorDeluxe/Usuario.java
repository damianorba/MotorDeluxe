package es.urjc.code.motorDeluxe;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.lang.reflect.Array;
import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Entity
@Component
@SessionScope
public class Usuario {, ,,
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre = "";
	private String dni;
	
	private String email;
	private String telefono;
	private String password;
	
	private List<String> privilegios;
	
	/**Constructores**/
	
	protected Usuario () {}
	public Usuario(long id, String nombre, String dni, String email, String telefono, String password,
			String... privilegios) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.password = password;
		this.privilegios = new ArrayList<>(Arrays.asList(privilegios));
	}
	
	


	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getPrivilegios() {
		return privilegios;
	}
	public void setPrivilegios(List<String> privilegios) {
		this.privilegios = privilegios;
	}






	
	
	
	
	
	}
}
	


