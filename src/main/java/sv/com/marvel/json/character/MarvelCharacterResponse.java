package sv.com.marvel.json.character;

import lombok.Data;
import sv.com.marvel.response.Response;

@lombok.Data()
public class MarvelCharacterResponse implements Response {

	
	private String code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    
    public DataResponse data;

	
}
