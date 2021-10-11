package com.mintic.ciclo3.tiendawebService;

import com.mintic.ciclo3.tiendawebDto.LoginDTO;

public interface LoginService {
	boolean validateUser(LoginDTO userCredentials); 
}
