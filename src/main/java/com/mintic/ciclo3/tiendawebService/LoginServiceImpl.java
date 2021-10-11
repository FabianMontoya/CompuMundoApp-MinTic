package com.mintic.ciclo3.tiendawebService;

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
		return usuarioDao.VerifyCredentialsUser(userCredentials);
	}

}
