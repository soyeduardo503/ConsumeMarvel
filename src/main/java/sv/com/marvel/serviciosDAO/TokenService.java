package sv.com.marvel.serviciosDAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import sv.com.marvel.dao.DaoToken;
import sv.com.marvel.dao.DaoUsuario;
import sv.com.marvel.domain.Busquedas;
import sv.com.marvel.domain.Token;
import sv.com.marvel.domain.Usuario;


@Configurable
@Service
@Slf4j
public class TokenService {

	@Autowired
	private DaoToken dao;
	
	@Autowired
	private EntityManager em; 
	

	private final static String HEADER ="Authorization";
	private final static String PREFIX="cle";

	public String getToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).split(", Basic")[0];
		return jwtToken;
	}
	
	@Autowired
	private BusquedaServiceImpl busquedaServicio;
	
	@Autowired
	private DaoUsuario usuarioServicio;
	
	
	
	public void registrarBusqueda(String token,String path) {
		if(token!=null) {
			int i=findIdUserByToken(token);
			if(i!=0) {
				Busquedas busqueda=new Busquedas();
				Usuario us=usuarioServicio.findById(i).orElse(new Usuario());
				busqueda.setFecha(new Date());
				busqueda.setIdusuario(us);
				busqueda.setBusqueda(path);
				busquedaServicio.save(busqueda);
			}
		}
	}
	
	
	@Transactional
	public void save(Token token) {
		dao.save(token);
	}
	
	@Transactional(readOnly = true)
	public Integer findIdUserByToken(String token) {
		log.info("Token ["+token +"]");
		String sql="Select t  From Token t where t.token=:token  ";
		Query q=em.createQuery(sql).setParameter("token", token);
		
		List<Token> l= q.getResultList();
		if(l.size()>0)
			return l.get(0).getIdusuario();
		return 0;
	}


	public void registrarBusqueda(HttpServletRequest request) {
		registrarBusqueda(getToken(request), request.getRequestURI());
		
	}
}
