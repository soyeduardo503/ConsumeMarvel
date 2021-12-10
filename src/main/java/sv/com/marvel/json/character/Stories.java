package sv.com.marvel.json.character; 
import java.util.List;

import sv.com.marvel.response.Item;

@lombok.Data
public class Stories{
	
    private String available;
    private String returned;
    private String collectionURI;
    
    
    public List<Item> items;
    
    private List<Character> characters;
    
    private List<Series> series;
    
    private List<Comics> comics;
}
