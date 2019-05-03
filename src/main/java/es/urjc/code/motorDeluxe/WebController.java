package es.urjc.code.motorDeluxe;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController{
	@Autowired
	private AnuncioRepository repository;
    
	@Autowired
	private CocheRepository cocheRepository;
	
	@Autowired
	private UserRepository usuarioRepository;
	
	@Autowired
	private OfertaCompraRepository ofertaCompraRepository;
	
	
	@Autowired
	private VentaRepository ventaRepository;
	

	/*@GetMapping("/login")
    public String login() {
    	return "login";
    }
	
	
	@GetMapping("/nuevoUsuario")
    public String getUsuario(Model model, HttpServletRequest request,Pageable page) {
		
    	return "nuevoUsuario";
    }
    
	
	@GetMapping("/anuncioNuevo")
    public String getAnuncio() {
    	return "anuncioNuevo";
    }
    
	
    @GetMapping("/loginerror")
    public String loginerror() {
    	return "loginerror";
    }*/

  
    
    
    @GetMapping("/index")
	public String home(Model model, HttpServletRequest request,Pageable page) { 
		
		model.addAttribute("anuncios", repository.findAll(page));
		model.addAttribute("anunciosCount", repository.count());
		model.addAttribute("usuarios", usuarioRepository.count());
		model.addAttribute("coches", cocheRepository.count());
		
		return "index";
	
	}
}
