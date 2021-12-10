/**
 * 
 */
package sv.com.marvel.ws.consume;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import sv.com.marvel.json.character.MarvelCharacterResponse;
import sv.com.marvel.response.DataCharacter;
import sv.com.marvel.response.Error;
import sv.com.marvel.response.MUC;
import sv.com.marvel.response.Response;
import sv.com.marvel.response.Thumbnail;


/**
 * @author 50364
 *
 */



@Service
@Slf4j
public class MarvelClient {


	
	
	public MarvelClient() {
		
	}
	
	public MUC consume(AbstractEndpoint endpoint ) {
		RestTemplate restTemplate = new RestTemplate();
		log.info("->"+endpoint.buildURL()+endpoint.endPoint());
		 //Root resp = restTemplate.getForObject(endpoint.buildURL()+endpoint.endPoint(), Root.class);
		 MUC mavelUniverseControler=new MUC();
		 mavelUniverseControler.setResponseObject((Response)restTemplate.getForObject(endpoint.buildURL()+endpoint.endPoint(), endpoint.getTypeResponse()));
		//WebClient client = WebClient.create(endpoint.buildURL())
		  //ResponseEntity<MUC> resp = restTemplate.getForEntity(endpoint.buildURL()+endpoint.endPoint(), MUC.class);	
		//Flux<Root> resp = client.get().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).uri(endpoint.endPoint()).retrieve().bodyToFlux(Root.class);
		
		
		 return mavelUniverseControler;
	}
	
	public byte[] getImgeCharacter(PersonajeService endpoint) throws Exception {
		MUC resp=getDataCharacter(endpoint);
		DataCharacter data=(DataCharacter) resp.getResponse();
		if(data!=null) {
			
			return obtenerImg(data.getUrlImg());
		}else {
			throw new Exception("No se recibio path de imagen");
		}
	}
	
	private byte[] obtenerImg(String url) throws IOException  {
		URL img=new URL(url);
		log.info("URL->"+url);
		  BufferedImage image = ImageIO.read(img);
		  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		  ImageIO.write(image,"jpg",byteArrayOutputStream);
		  byteArrayOutputStream.flush();
		  return byteArrayOutputStream.toByteArray();
		
	}

	public MUC getDataCharacter(PersonajeService endpoint) throws Exception {
		if(endpoint.getId()==null )
			throw new Exception("Parametro necesario");
		MUC ironMan = consume(endpoint);
		MUC resp=new MUC();
		MarvelCharacterResponse c = (MarvelCharacterResponse)ironMan.getResponse();
		if(c.getData().getResults()!=null&&!c.getData().getResults().isEmpty()) {
			DataCharacter data=new DataCharacter();
			Thumbnail mini=c.getData().getResults().get(0).getThumbnail();
			data.setUrlImg(mini.toString());
			data.setDescripcion(c.getData().getResults().get(0).getDescription());
			resp.setResponse(data);
		}else {
			resp.setResponse(new Error(99999,"Personaje no encontrado"));
		}
		return resp;
	}
	
	
	
}
