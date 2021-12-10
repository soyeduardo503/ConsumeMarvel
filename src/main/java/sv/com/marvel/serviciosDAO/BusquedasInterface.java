package sv.com.marvel.serviciosDAO;

import java.util.List;

import sv.com.marvel.domain.Busquedas;
import sv.com.marvel.domain.Usuario;



public interface BusquedasInterface {

	
	
	
	public void save(Busquedas u);
	
	public List<Busquedas> list(Usuario u);
	

	public List<Busquedas> list();
	
	
	
}
