package sv.com.marvel.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import sv.com.marvel.domain.Usuario;
import sv.com.marvel.response.ResponseLogin;
import sv.com.marvel.serviciosDAO.LoginService;

@RestController
@Slf4j
public class LoginRestful {
	

	@Autowired
	private LoginService  dao;
	
	public LoginRestful() {
		
	}
	@PostMapping("/login")
	public ResponseLogin login(@RequestBody  Usuario us) {
		ResponseLogin resp=new ResponseLogin();
		
		resp.setToken(dao.login(us));
		resp.setUsuario(us.getUsu());
		
		log.info("Respondiendo Login");
		return resp;
	}

}
