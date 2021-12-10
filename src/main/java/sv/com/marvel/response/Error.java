/**
 * 
 */
package sv.com.marvel.response;

/**
 * @author 50364
 *
 */
public class Error implements Response {

	public Error(int i, String desc) {
		cod=i;
		descripcion=desc;
	}
	private int cod;
	private String descripcion;
}
