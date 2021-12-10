/**
 * 
 */
package sv.com.marvel.serviciosDAO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import sv.com.marvel.domain.Token;
import sv.com.marvel.domain.Usuario;

/**
 * @author 50364
 *
 */

@Service
@Slf4j
public class LoginService  {

	/**
	 * 
	 */

	@Autowired
	private BusquedaServiceImpl busquedaService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired 
	protected EntityManager em;
	
	@Value("${marvel.api.security.secrectkey}")
	private String secretKey;
	
	@Value("${marvel.api.security.prefix}")
	private String pre="key ";
	
	
	
	public LoginService() {
		
	}

	
	

	
	@Transactional
	public String login(Usuario us) {
	
	    	String sql="From Usuario u Where  u.usu = :user and u.pass=:pass " 			;
	    	//Busquedas Busquedas=new Busquedas();
	    	
	    	
	    	Query q = em.createQuery(sql).setParameter("user",us.getUsu()).setParameter("pass", us.getPass());
	    	List<Usuario> list=(List<Usuario>)q.getResultList();
	    	String token= list.size()>0?getJWTToken(list.get(0).getUsu()):"0";
	    	Token tokenReg=new Token();
	    	tokenReg.setToken(token);
	    	tokenReg.setIdusuario(list.get(0).getIdusuario());
	    	tokenService.save(tokenReg);
		
	    	return token;
	

	}
	private String getJWTToken(String username) {
		//String secretKey = "1990naruto2005";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
	
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();
		log.info(pre+token);
		return pre + token;
	}
}
