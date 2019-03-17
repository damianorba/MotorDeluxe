package es.urjc.code.motorDeluxe;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

	@Entity
	public class Venta {
		
		
		    @Id
		    @GeneratedValue(strategy = GenerationType.AUTO)
		    private long id;
		    
		    
		    private String fechaVenta;
		    private Double precio_venta;
		   
		    @OneToOne
		    private Usuario vendedor;
		    
		    @OneToOne
		    private Usuario comprador;
		    
		    @OneToOne
		    private Coche coche;
		    
		   
		    
		    protected Venta(){}//constructor para usar el repositorio

		    public Venta(String fechaVenta, Double precio_venta) {
				
				this.id = id;
				this.fechaVenta = fechaVenta;
				this.precio_venta = precio_venta;
				this.vendedor = new Usuario();
				this.comprador = new Usuario();
				this.coche = new Coche();
			}

			public long getId() {
				return id;
			}

			public void setId(long id) {
				this.id = id;
			}

			public String getFechaVenta() {
				return fechaVenta;
			}

			public void setFechaVenta(String fechaVenta) {
				this.fechaVenta = fechaVenta;
			}

			public Double getPrecio_venta() {
				return precio_venta;
			}

			public void setPrecio_venta(Double precio_venta) {
				this.precio_venta = precio_venta;
			}

			public Usuario getVendedor() {
				return vendedor;
			}

			public void setVendedor(Usuario vendedor) {
				this.vendedor = vendedor;
			}

			public Usuario getComprador() {
				return comprador;
			}

			public void setComprador(Usuario comprador) {
				this.comprador = comprador;
			}

			public Coche getCoche() {
				return coche;
			}

			public void setCoche(Coche coche) {
				this.coche = coche;
			}

		    		    
		    

	}


	


