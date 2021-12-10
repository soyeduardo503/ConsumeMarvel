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
public class ComicRequest extends Request {

	/**
	 * 
	 */
	private String name;
	
	private Integer idCreador;
	
	
	public ComicRequest() {
		
	}


	@Override
	public String buildParams() {
		
		if(name!=null||idCreador!=null) {
			StringBuilder sb=new StringBuilder("&");
			sb.append(name!=null?"name=":"").append(name!=null?name:"")
			
			.append(idCreador!=null?"serie=":"").append(idCreador!=null?idCreador:"")
			;
			return sb.toString();
		}else
			return null;
	}
	
	

	
}
