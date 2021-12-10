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
public abstract class Request {

	private Integer id;
	
	public abstract String buildParams() ;
	
}
