package es.urjc.code.motorDeluxe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String passwordHash;
	
	private String nombre_completo;
	private String dni;
	private String email;
	private String telefono;
	

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public User() {
	}
	//User u1 = new User("dortiz","67890","Damian Ortiz","50574692D","demn007@gmail.com","648799392","ROLE_USER");
	public User(String name, String password,String nombre_completo, String dni ,String email,String telefono, String... roles) {
		this.name = name;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.nombre_completo = nombre_completo;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNombreCompleto() {
		return nombre_completo;
	}

	public void setNombreCompleto(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
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

}

	


