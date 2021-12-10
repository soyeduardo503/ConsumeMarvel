package sv.com.marvel.json.comics;

import lombok.Data;
import sv.com.marvel.response.Response;

@lombok.Data()
public class MarvelComicsResponse implements Response {

	
	private String code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    
 

	
}
