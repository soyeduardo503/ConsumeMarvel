/**
 * 
 */
package sv.com.marvel.response;

import java.util.Calendar;
import java.util.Date;

/**
 * @author 50364
 *
 */
@lombok.Data
public class MUC {

		
		private Response response;
		
		private Date horaRespuesta=Calendar.getInstance().getTime();
		
		private byte[] img;
		
		
		
		public MUC() {
			
		}
		public MUC(Error error) {
			setResponseObject(error);
		}
		public void setResponseObject(Response respWS) {
			response=respWS;
			
		}

}
