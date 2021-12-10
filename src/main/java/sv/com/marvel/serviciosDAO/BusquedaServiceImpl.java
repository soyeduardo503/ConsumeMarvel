/**
 * 
 */
package sv.com.marvel.serviciosDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import sv.com.marvel.dao.DaoBusquedas;
import sv.com.marvel.domain.Busquedas;
import sv.com.marvel.domain.Usuario;

/**
 * @author 50364
 *
 */

@Service
@Slf4j
public class BusquedaServiceImpl implements BusquedasInterface{

	/**
	 * 
	 */

	
	@Autowired 
	protected DaoBusquedas dao;
	
	@Autowired
	private EntityManager em;
	
	public BusquedaServiceImpl() {
		
	}
	

	@Override
	@Transactional
	public void save(Busquedas busqueda) {
		dao.save(busqueda);
		
	}



	



	@Override
	@Transactional(readOnly = true)
	public List<Busquedas> list(Usuario u) {
		log.info("["+u.getIdusuario()+"]");
		String sql="Select b   From Busquedas b, Usuario u Where  b.idusuario = u and u.idusuario=:us  ";
		Query q=em.createQuery(sql).setParameter("us", u.getIdusuario());
		
		List<Busquedas> l= q.getResultList();
		
		return l;
	}


	@Override
	public List<Busquedas> list() {
		return (List<Busquedas>) dao.findAll();
	}
}
