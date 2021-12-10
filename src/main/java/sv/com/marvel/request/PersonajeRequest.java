/**
 * 
 */
package sv.com.marvel.request;

import lombok.Data;

/**
 * @author 50364
 *
 */
@Data
public class PersonajeRequest extends Request {

	/**
	 * 
	 */
	private String name;
	private Integer idComic;
	private Integer idSerie;
	
	
	public PersonajeRequest() {
		
	}


	@Override
	public String buildParams() {
		
		if(name!=null||idComic!=null||idSerie!=null) {
			StringBuilder sb=new StringBuilder("&");
			sb.append(name!=null?"name=":"").append(name!=null?name:"")
			.append(idComic!=null?"comics=":"").append(idComic!=null?idComic:"")
			.append(idSerie!=null?"serie=":"").append(idSerie!=null?idSerie:"")
			;
			return sb.toString();
		}else
			return null;
	}
	
	

	
}
