/**
 * 
 */
package sv.com.marvel.ws.consume;

import lombok.Data;
import sv.com.marvel.json.creator.MarvelCreatorResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
/**
 * @author 50364
 *
 */

@Service
public class CreatorService extends AbstractEndpoint{


	

	
	@Value("${marvel.api.endpoint.creators}")
	private String endPoint;

	@Override
	public String getEndpoint() {
		
		return endPoint;
	}

	@Override
	public void defineTypeResponse() {
		this.setTypeResponse(MarvelCreatorResponse.class);
		
	}
	
	
	
}
