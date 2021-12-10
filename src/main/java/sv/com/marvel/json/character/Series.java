package sv.com.marvel.json.character; 
import java.util.List;

import sv.com.marvel.response.Item;

@lombok.Data
public class Series{
    private String available;
    private String returned;
    private String collectionURI;
    private List<Item> items;
}
