/**
 * 
 */
package sv.com.marvel.ws.consume;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sv.com.marvel.json.comics.MarvelComicsResponse;


/**
 * @author 50364
 *
 */

@Service
public class ComicService extends AbstractEndpoint{


	

	
	@Value("${marvel.api.endpoint.comics}")
	private String endPoint;

	@Override
	public String getEndpoint() {
		
		return endPoint;
	}

	@Override
	public void defineTypeResponse() {
		this.setTypeResponse(MarvelComicsResponse.class);
		
	}
	
	
	
}
