package sv.com.marvel.response; 

@lombok.Data
public class Thumbnail{
	
    private String path;
    private String extension;
    
    @Override
    public String toString() {
    	return path+"."+extension;
    }
}
