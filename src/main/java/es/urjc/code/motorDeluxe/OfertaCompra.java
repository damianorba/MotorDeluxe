package es.urjc.code.motorDeluxe;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class OfertaCompra {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    
    private String fOferta;
    private Double precioOferta;
    

    @OneToOne
    private User comprador;
    
    @OneToOne
    private User vendedor;
    
	@OneToOne
	private Coche coche;
	protected OfertaCompra() {}
	public OfertaCompra(String fOferta, Double precioOferta) {
		
		super();
		this.fOferta = fOferta;
		this.precioOferta = precioOferta;
		this.comprador = new User();
		this.vendedor = new User();
		this.coche = new Coche();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFechaOferta() {
		return fOferta;
	}


	public void setFechaOferta(String fOferta) {
		this.fOferta = fOferta;
	}


	public Double getPrecioOferta() {
		return precioOferta;
	}


	public void setPrecioOferta(Double precioOferta) {
		this.precioOferta = precioOferta;
	}


	public User getComprador() {
		return comprador;
	}


	public void setComprador(User comprador) {
		this.comprador = comprador;
	}
	
	public User getVendedor() {
		return vendedor;
	}


	public void setVendedor(User vendedor) {
		this.vendedor = vendedor;
	}


	public Coche getCoche() {
		return coche;
	}


	public void setCoche(Coche coche) {
		this.coche = coche;
	}
	
	@Override
	public String toString() {
		return "Oferta [Fecha Oferta=" + fOferta + ", precioOferta=" + precioOferta + " " + coche +"]";
	}
    
}
