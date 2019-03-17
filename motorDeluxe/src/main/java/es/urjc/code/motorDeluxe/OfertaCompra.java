package es.urjc.code.motorDeluxe;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;


@Entity
public class OfertaCompra {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    
    private String fechaOferta;
    private Double precioOferta;
    

    @OneToOne
    private Usuario usuario;
    
    
    @OneToOne
    private Coche coche;


	public OfertaCompra() {}


	public OfertaCompra(String fechaOferta, Double precioOferta) {
		
		this.id = id;
		this.fechaOferta = fechaOferta;
		this.precioOferta = precioOferta;
		this.usuario = new Usuario();
		this.coche = new Coche();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFechaOferta() {
		return fechaOferta;
	}


	public void setFechaOferta(String fechaOferta) {
		this.fechaOferta = fechaOferta;
	}


	public Double getPrecioOferta() {
		return precioOferta;
	}


	public void setPrecioOferta(Double precioOferta) {
		this.precioOferta = precioOferta;
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
	
	
    
    
	
}
