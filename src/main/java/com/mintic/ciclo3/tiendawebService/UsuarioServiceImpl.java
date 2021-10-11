package com.mintic.ciclo3.tiendawebService;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.mintic.ciclo3.tiendawebDao.UsuarioDao;
import com.mintic.ciclo3.tiendawebDto.UsuarioDTO;

public class UsuarioServiceImpl implements UsuarioService {

	//@Autowired
    private UsuarioDao usuarioDao;
	
	public UsuarioServiceImpl() {
		this.usuarioDao = new UsuarioDao();
	}
	
	@Override
	public boolean CreateUser(UsuarioDTO userInfo) {
		userInfo.setUsername(userInfo.getUsername().toLowerCase());		
		userInfo.setPassword(HashPassword(userInfo.getUsername(), userInfo.getPassword()));
		
		return this.usuarioDao.InsertUserInfo(userInfo);
	}

	@Override
	public boolean UpdateUser(UsuarioDTO userInfo) {		
		if(userInfo.getPassword() != null && userInfo.getPassword().length() > 0) {
			userInfo.setPassword(HashPassword(userInfo.getUsername(), userInfo.getPassword())); 
		}
		
		return this.usuarioDao.UpdateUserInfo(userInfo);
	}

	@Override
	public boolean DeleteUser(String username) {
		return this.usuarioDao.DeleteUserInfo(username);
	}

	@Override
	public List<UsuarioDTO> GetAllUsers() {
		return this.usuarioDao.getAllUsers();
	}

	@Override
	public UsuarioDTO GetUserInfo(String username) {
		return this.usuarioDao.getUserById(username);
	}
	
	private String HashPassword(String username, String password) {
		return DigestUtils.sha256Hex(username + password); //La contraseña es la unión entre el usuario y su contraseña final
	}

}
