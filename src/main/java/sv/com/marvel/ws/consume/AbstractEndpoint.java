/**
 * 
 */
package sv.com.marvel.ws.consume;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 50364
 *
 */
@Data
@Slf4j
public abstract class AbstractEndpoint {

	@Value("${marvel.key.public}")
	private String publicKey;
	@Value("${marvel.key.private}")
	private String privateKey;
	
	@Value("${marvel.api.base.url}")
	private String urlBase;
	
	{
		defineTypeResponse();
	}
	
	private Integer id;
	
	private String browser;
	
	private Long ts;
	
	private String filter;
	
	private Class typeResponse;
	
	public String buildURL() {
		return urlBase;
	}
	
	public abstract void defineTypeResponse();
	
	public String endPoint() {
		StringBuilder sb=new StringBuilder();
		
		 sb.append(getEndpoint()).append(id!=null?"/":"").append(id!=null?id:"").append(browser!=null?"/":"")
				.append(browser!=null?browser:"").append(params());
		
			
		return sb.toString();
	}
	
	public String params() {
		ts=Calendar.getInstance().getTimeInMillis();
		return new StringBuilder().append("?")
				.append("ts=").append(ts).append("&")
				.append("apikey=").append(publicKey).append("&")
				.append("hash=").append(createHash()).append(filter!=null&&!"".equals(filter)?filter:"").toString();
		
	}
	
	public abstract String getEndpoint();
	
//	private String urlLista() {
//		return new StringBuilder(urlBase).toString();
//	}

	
	private String createHash() {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			
			byte[] result = md.digest(new StringBuilder(ts+privateKey+publicKey).toString().getBytes());
			return obtenerString(result);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return "noValid";
		
	}
	
	private static String obtenerString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        log.info(sb.toString());
        return sb.toString();
    }

	
}
