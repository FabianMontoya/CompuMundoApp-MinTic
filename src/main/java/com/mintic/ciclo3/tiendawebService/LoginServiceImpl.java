package com.mintic.ciclo3.tiendawebService;

import org.apache.commons.codec.digest.DigestUtils;

import com.mintic.ciclo3.tiendawebDao.UsuarioDao;
import com.mintic.ciclo3.tiendawebDto.LoginDTO;

//@Service
public class LoginServiceImpl implements LoginService {

	//@Autowired
    private UsuarioDao usuarioDao;
	
	public LoginServiceImpl() {
		this.usuarioDao = new UsuarioDao();
	}
	
	@Override
	public boolean validateUser(LoginDTO userCredentials) {		
		userCredentials.setUsername(userCredentials.getUsername().toLowerCase());
		userCredentials.setPassword(HashPassword(userCredentials.getUsername(), userCredentials.getPassword()));
		
		return usuarioDao.VerifyCredentialsUser(userCredentials);
	}
	
	private String HashPassword(String username, String password) {
		return DigestUtils.sha256Hex(username + password); //La contraseña es la unión entre el usuario y su contraseña final
	}

}
