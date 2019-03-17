package es.urjc.code.motorDeluxe;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.motorDeluxe.Anuncio;
import es.urjc.code.motorDeluxe.AnuncioRepository;
import es.urjc.code.motorDeluxe.Usuario;
import es.urjc.code.motorDeluxe.UsuarioRepository;
import es.urjc.code.motorDeluxe.Coche;
import es.urjc.code.motorDeluxe.CocheRepository;



	
	@Controller
	public class TablonController {
		
		//Si se abre la URL http://127.0.0.1:8080/h2-console y se configura
		//la URL JDBC con el valor jdbc:h2:mem:testdb se puede acceder a la 
		//base de datos de la aplicación

		@Autowired
		private AnuncioRepository repository;
		
		@Autowired
		private CocheRepository cocheRepository;
		
		@Autowired
		private UsuarioRepository usuarioRepository;
		
		@Autowired
		private OfertaCompraRepository ofertaCompraRepository;
		
		
		@Autowired
		private VentaRepository ventaRepository;

		@PostConstruct
		public void init() {
			
			/*Rellenamos la base con un administrador, dos usuarios que venden y un usuario que va a realizar la compra*/
			
			Usuario ad = new Usuario("WEB_Manager","54678932R","manager@yahoo.es","672243775","12345","ROLE_USER","ROLE_ADMIN");
			
			Usuario u1 = new Usuario("Damian Ortiz Barahona","50574692D","demn007@gmail.com","648799392","67890","ROLE_USER");
			
			Usuario u2 = new Usuario("Paula Rodriguez de Zoluaga","73263327S","prr.zoluaga@wanadoo.com","64537872","123456", "ROLE_USER");
			
			Usuario u3 = new Usuario("Benedicto tercero","59563719W","benedict@plus.com","634522718","1234567","ROLE_USER"); 
		
			/*Insertamos 2 coches*/
			
			Coche c1 = new Coche("0830DTJ", "OPEL","ASTRA GTC","NEGRO","no",3,30.000,2009,12000.00);
			Coche c2 = new Coche("2052GCP", "CITROEN","C4","BLANCO","techo solar",5,10.000,2011,15000.00);
			
			/*Persisto a  los usuarios*/
			
			usuarioRepository.save(ad);
			usuarioRepository.save(u1);
			usuarioRepository.save(u2);
			
			
	        /*Persisto los coches */
			
			cocheRepository.save(c1);
			cocheRepository.save(c2);
			
			/*Creamos anuncios y los asignamos a los usuarios*/
			
			Anuncio a1 = new Anuncio("Damian Ortiz","Opel Astra GTC de oprotunidad","coche en prefecto estado",12000.00);
			Anuncio a2 = new Anuncio("Paula","Citroen C4","Vendo monovolumen",15000.00);
			a1.setUsuario(u1);
			a2.setUsuario(u2);
			a1.setCoche(c1);
			a2.setCoche(c2);
			
			/*Creamor una oferta de compra */
			
			OfertaCompra comp1 = new OfertaCompra("17/03/2019", 10.000);
			comp1.setCoche(c1);
			comp1.setUsuario(u3);
			
			ofertaCompraRepository.save(comp1);
			
			/*Creamos una venta */
			
			Venta venta1 = new Venta("18/03/2019", 10.000);
			
			
			venta1.setVendedor(u1);
			venta1.setComprador(u3);
						
			venta1.setCoche(c1);
			
			//Persisto los anuncios p1,p2,p3
			repository.save(a1);
			repository.save(a2);
			
			
		
			//Persisto las ventas de los usuarios
			
			ventaRepository.save(venta1);
			
			
		}

		@RequestMapping("/")
		public String tablon(Model model, Pageable page) {

			model.addAttribute("anuncios", repository.findAll(page));
			model.addAttribute("usuarios", usuarioRepository.count());
			model.addAttribute("coches", cocheRepository.count());
			return "tablon";
		}

		@RequestMapping("/anuncio/nuevo")
		public String nuevoAnuncio(Model model, Anuncio anuncio, Coche coche) {

			repository.save(anuncio);
			cocheRepository.save(coche);

			return "anuncio_guardado";

		}

		@RequestMapping("/anuncio/{id}")
		public String verAnuncio(Model model, @PathVariable long id) {
			
			Anuncio anuncio = repository.findOne(id);
			model.addAttribute("anuncio", anuncio);
			
			//Optional<Anuncio> anuncio = repository.findById(id);

			/*if(anuncio.isPresent()) {
				model.addAttribute("anuncio", anuncio.get());
			}*/

			return "ver_anuncio";
		}
		
		
		@PostMapping("/usuario/nuevo")
		public String nuevoUsuario(Model model, Usuario usuario,HttpServletRequest request) {
			
			//Para dar de alta otro administardor solo puede otro administardor
			
			if(request.isUserInRole("ADMIN")){
				model.addAttribute("admin", request.isUserInRole("ADMIN"));
				usuario.setPrivilegios(new ArrayList<>(Arrays.asList("ROLE_USER","ROLE_ADMIN")));
				usuarioRepository.save(usuario);
			return "usuario_guardado";
			
			//creo un nuevo usuario
			}
			else{
			usuario.setPrivilegios(new ArrayList<>(Arrays.asList("ROLE_USER")));
			usuarioRepository.save(usuario);
			
			return "usuario_guardado";
			}
		}
		
		
		@RequestMapping("/vender/{id}")
		public String comprar(Model model, Venta venta, @PathVariable long id) {
			
			Coche coche = cocheRepository.findOne(id);
			Anuncio anuncio = repository.findOne(id);
			String nombre = anuncio.getNombre();
			
			
			Usuario usuario = usuarioRepository.findByNombre(nombre);
			
			
			String fechaCompra = "17/03/2019";
			venta.setCoche(coche);
			venta.setComprador(anuncio.getUsuario());
			venta.setVendedor(anuncio.getUsuario());
			venta.setFechaVenta(fechaCompra);
			venta.setPrecio_venta(anuncio.getPrecio());
			
			
			ventaRepository.save(venta);
			
			return ("Venta ok");
		}
		
		@RequestMapping("/oferta/{id}")
		public String ofertaCompra(Model model, OfertaCompra oferta, @PathVariable long id) {
			
			Coche coche = cocheRepository.findOne(id);
			Anuncio anuncio = repository.findOne(id);
			
			String fechaVenta = "17/03/2019";
			oferta.setCoche(coche);
			oferta.setUsuario(anuncio.getUsuario());
			oferta.setFechaOferta(fechaVenta);
			oferta.setPrecioOferta(anuncio.getPrecio());

			ofertaCompraRepository.save(oferta);

			return "venta_guardada";

		}
	
	}


