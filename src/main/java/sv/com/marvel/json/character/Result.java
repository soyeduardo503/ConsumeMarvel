package sv.com.marvel.json.character;

import java.util.List;

import sv.com.marvel.response.Thumbnail;
import sv.com.marvel.response.Url;



@lombok.Data
public class Result {

	/*Campos de Character*/
	private String id;
    private String name;
    private String description;
    private String modified;
    
    
    private String resourceURI;
    
    /*Campos de autor*/
    
//    private String firstName;
//    private String middleName;
//    private String lastName;
//    private String suffix;
//    private String fullName;
    
    
    /*
     * Campos de comics
     * */
//    
//    
//    private String digitalId;
//    private String title;
//    private String issueNumber;
//    private String variantDescription;    
//    private String isbn;
//    private String upc;
//    private String diamondCode;
//    private String ean;
//    private String issn;
//    private String format;
//    private String pageCount;
//    private List<TextObject> textObjects;
    
    
    
    
    /*Campos comunes*/
    private List<Url> urls;
    private Thumbnail thumbnail;
    //private List<PersonajeMarvel> characters;
    private Comics comics;
    private Stories stories;
    private Series series;
}
