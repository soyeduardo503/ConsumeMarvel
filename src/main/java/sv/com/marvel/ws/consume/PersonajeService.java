/**
 * 
 */
package sv.com.marvel.ws.consume;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sv.com.marvel.json.character.MarvelCharacterResponse;
/**
 * @author 50364
 *
 */

@Service
public class PersonajeService extends AbstractEndpoint{


	

	
	@Value("${marvel.api.endpoint.personaje}")
	private String endPoint;

	@Override
	public String getEndpoint() {
		
		return endPoint;
	}

	@Override
	public void defineTypeResponse() {
		this.setTypeResponse(MarvelCharacterResponse.class);
		
	}
	
	
	
}
