package sv.com.marvel.ws;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import sv.com.marvel.domain.Busquedas;
import sv.com.marvel.domain.Usuario;
import sv.com.marvel.request.ComicRequest;
import sv.com.marvel.request.PersonajeRequest;
import sv.com.marvel.response.MUC;
import sv.com.marvel.serviciosDAO.BusquedaServiceImpl;
import sv.com.marvel.serviciosDAO.TokenService;
import sv.com.marvel.ws.consume.ComicService;
import sv.com.marvel.ws.consume.CreatorService;
import sv.com.marvel.ws.consume.MarvelClient;
import sv.com.marvel.ws.consume.PersonajeService;

@RestController(value = "/marvel")
@Slf4j
public class MarvelRest {
	
	@Autowired
	private MarvelClient service;
	
	@Autowired
	private PersonajeService personajeService;
	
	@Autowired
	private ComicService comicService;
	
	@Autowired
	private CreatorService creatorService;

	@Autowired 
	private BusquedaServiceImpl busquedaService;
	
	@Autowired 
	private TokenService tokenService;
	
	
//	@Autowired
//	private Personaje personaje;
	
	public MarvelRest() {
		
	}
	@GetMapping("/listPersonajes")
	public MUC listPersonaje(HttpServletRequest request) {
		this.tokenService.registrarBusqueda(request);
		log.info("Respondiendo status: "+personajeService);
		
		return service.consume(personajeService);
	}
	
	@GetMapping("/listCreadores")
	public MUC listCreador(HttpServletRequest request) {
		
		log.info("Respondiendo status: "+creatorService);
		
		return service.consume(creatorService);
	}
	
	@GetMapping("/listComics")
	public MUC listComic() {
		
		log.info("Respondiendo status: "+comicService);
		
		return service.consume(comicService);
	}
	
	@PostMapping("/comics")
	public MUC character(@RequestBody  ComicRequest req) {
		log.info("ID +"+req);
		comicService.setId(req.getId());
		comicService.setFilter(req.buildParams());
		log.info("Respondiendo status: "+comicService);
	
		return service.consume(comicService);
	}

	@PostMapping("/personajes")
	public MUC character(@RequestBody  PersonajeRequest req) {
		log.info("ID +"+req);
		personajeService.setId(req.getId());
		personajeService.setFilter(req.buildParams());
		log.info("Respondiendo status: "+personajeService);
	
		return service.consume(personajeService);
	}
	
	@PostMapping("/personajes/img")	
	public @ResponseBody() ResponseEntity<byte[]> characterImg(@RequestBody  PersonajeRequest req, Object response) throws Exception {
		log.info("ID +"+req);
		personajeService.setId(req.getId());
		personajeService.setFilter(req.buildParams());
		log.info("Respondiendo status: "+personajeService);
		
		//response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.IMAGE_JPEG);
		headers.setContentType(MediaType.IMAGE_JPEG);
		
		 ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(service.getImgeCharacter(personajeService), headers, HttpStatus.OK);
		 return responseEntity;
		//return service.getImgeCharacter(personajeService);
	}
	
	@PostMapping("/personajes/datos")	
	public MUC characterData(@RequestBody  PersonajeRequest req,HttpServletRequest request) throws Exception {
		this.tokenService.registrarBusqueda(request);
		log.info("ID +"+req);
		personajeService.setId(req.getId());
		personajeService.setFilter(req.buildParams());
		log.info("Respondiendo datos  personaje: "+personajeService);
		
		//response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.IMAGE_JPEG);
		headers.setContentType(MediaType.IMAGE_JPEG);
		
		 return service.getDataCharacter(personajeService);
		 
		//return service.getImgeCharacter(personajeService);
	}
	
	@PostMapping("/listComics/personaje")
	public MUC listComicsPersonaje(@RequestBody  PersonajeRequest req) {
		log.info("ID +"+req);
		personajeService.setId(req.getId());
		personajeService.setFilter(req.buildParams());
		log.info("Respondiendo status: "+personajeService);
	
		return service.consume(personajeService);
	}
	
	@PostMapping("/personajes/pic")
	public MUC characterPic(@RequestBody  PersonajeRequest req) {
		log.info("ID +"+req);
		personajeService.setId(req.getId());
		personajeService.setFilter(req.buildParams());
		log.info("Respondiendo status: "+personajeService);
	
		return service.consume(personajeService);
	}
	
	@PostMapping("/busquedaPorUsuario")
	public List<Busquedas> busquedaPorUsuario(@RequestBody Usuario usuario,HttpServletRequest request){
			
		return this.busquedaService.list(usuario); 
	}
	
	@GetMapping("/busquedas")
	public List<Busquedas> busquedaPorUsuario(){
			
		return this.busquedaService.list(); 
	}
}
