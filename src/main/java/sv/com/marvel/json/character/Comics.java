package sv.com.marvel.json.character; 
import java.util.List;

import lombok.Data;
import sv.com.marvel.response.Thumbnail;
import sv.com.marvel.response.Url; 


@Data
public class Comics{
    private String available;
    private String returned;
    private String collectionURI;
    
    private String id;
    private String digitalId;
    private String title;
    private String issueNumber;
    private String variantDescription;
    private String description;
    private String modified;
    private String isbn;
    private String upc;
    private String diamondCode;
    private String ean;
    private String issn;
    private String format;
    private String pageCount;
   // private List<TextObject> textObjects; comentado por la cantidad de texto
    private String resourceURI;
    private List<Url> urls;
    
  /*  private List<Variant> variants;
    private List<Collection> collections;
    private List<CollectedIssue> collectedIssues;
    private List<Date> dates;
    private List<Price> prices;*/
    private Thumbnail thumbnail;
//    private List<Item> items;
//    
//    private List<Character> characters;
//    
//    private List<Series> series;
//    
//    private List<Stories> stories;
}
