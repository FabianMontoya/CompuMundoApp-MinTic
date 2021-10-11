package com.mintic.ciclo3.tiendawebService;

import java.util.List;

import com.mintic.ciclo3.tiendawebDto.UsuarioDTO;

public interface UsuarioService {
	boolean CreateUser(UsuarioDTO userInfo);
	boolean UpdateUser(UsuarioDTO userInfo);
	boolean DeleteUser(String username);
	List<UsuarioDTO> GetAllUsers();
	UsuarioDTO GetUserInfo(String username);
}
