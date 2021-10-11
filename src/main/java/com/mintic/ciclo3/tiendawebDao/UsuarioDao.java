package com.mintic.ciclo3.tiendawebDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mintic.ciclo3.tiendawebDto.LoginDTO;
import com.mintic.ciclo3.tiendawebDto.UsuarioDTO;

public class UsuarioDao {

	public boolean VerifyCredentialsUser(LoginDTO userCredentials) {
		Conexion conex = new Conexion();
		boolean exist = false;
		try {
			String query = "SELECT username, password FROM usuario WHERE username=? AND password=?";
			PreparedStatement consulta = conex.GetConnection().prepareStatement(query);
			consulta.setString(1, userCredentials.getUsername());
			consulta.setString(2, userCredentials.getPassword());
			
			ResultSet res = consulta.executeQuery();
			if (res.next()) {
				exist = true;
			} else {
				exist = false;
				System.out.println("No existe usuario «" + userCredentials.getUsername() + "» con las credenciales dadas.");
			}
			res.close();
			consulta.close();
			conex.CloseConnection();

		} catch (Exception e) {
			System.out.println("Error al intentar verificar la información del usuario --- " + e);
		}
		return exist;
	}
	
	public List<UsuarioDTO> getAllUsers() {
		List<UsuarioDTO> result = new ArrayList<>();
		Conexion conex = new Conexion();
		
		try {
			String query="SELECT username, name, dni, email, usuario_crea FROM usuario ORDER BY username ASC";
			PreparedStatement consulta =conex.GetConnection().prepareStatement(query);
			ResultSet res=consulta.executeQuery();
			
			
			while(res.next()) {
				UsuarioDTO us= new UsuarioDTO();
				
				us.setUsername(res.getString("username"));
				us.setName(res.getString("name"));
				us.setDni(res.getString("dni"));
				us.setEmail(res.getString("email"));
				us.setUsuario_crea(res.getString("usuario_crea"));
				
				result.add(us);
			}
			res.close();
			consulta.close();
			conex.CloseConnection();
			
		} catch (Exception e) {
			System.out.println("Error al intentar consultar la información de usuarios --- " + e);
		}
		
		return result;
	}
	
	public UsuarioDTO getUserById(String username) {
		UsuarioDTO result = null;
		Conexion conex = new Conexion();
		
		try {
			String query="SELECT username, name, dni, email, usuario_crea FROM usuario WHERE username=?";
			PreparedStatement consulta =conex.GetConnection().prepareStatement(query);
			consulta.setString(1, username);
			
			ResultSet res=consulta.executeQuery();			
			
			while(res.next()) {
				result = new UsuarioDTO();
				
				result.setUsername(res.getString("username"));
				result.setName(res.getString("name"));
				result.setDni(res.getString("dni"));
				result.setEmail(res.getString("email"));
				result.setUsuario_crea(res.getString("usuario_crea"));
				result.setPassword("");
			}
			
			res.close();
			consulta.close();
			conex.CloseConnection();
			
		} catch (Exception e) {
			System.out.println("Error al intentar consultar la información de usuarios --- " + e);
		}
		
		return result;
	}
	
	public boolean InsertUserInfo(UsuarioDTO userInfo) {
		Conexion conex = new Conexion();
		
		try {
			String query="INSERT INTO usuario (username, password, name, dni, email, usuario_crea) VALUES " + 
							"(?,?,?,?,?,?) ";
			PreparedStatement preparedStatement =conex.GetConnection().prepareStatement(query);
			preparedStatement.setString(1, userInfo.getUsername());
			preparedStatement.setString(2, userInfo.getPassword());
			preparedStatement.setString(3, userInfo.getName());
			preparedStatement.setString(4, userInfo.getDni());
			preparedStatement.setString(5, userInfo.getEmail());
			preparedStatement.setString(6, userInfo.getUsuario_crea());
			
			int row = preparedStatement.executeUpdate();
			
			System.out.println("Registro correcto." + row);
			preparedStatement.close();
			conex.CloseConnection();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Error al intentar crear el usuario --- " + e);
			return false;
		}
	}
	
	public boolean UpdateUserInfo(UsuarioDTO userInfo) {
		Conexion conex = new Conexion();
		
		try {
			boolean changePassword = (userInfo.getPassword() != null && userInfo.getPassword().length() > 0);
			
			String query="UPDATE usuario SET name=?, dni=?, email=?";
			
			if(changePassword) {
				query = query + ", password=?";
			}
			
			query = query + " WHERE username=?";
			
			PreparedStatement preparedStatement =conex.GetConnection().prepareStatement(query);
			
			
			preparedStatement.setString(1, userInfo.getName());
			preparedStatement.setString(2, userInfo.getDni());
			preparedStatement.setString(3, userInfo.getEmail());

			int index = 4;
			
			if(changePassword) {
				preparedStatement.setString(index, userInfo.getPassword());
				index++;
			}

			preparedStatement.setString(index, userInfo.getUsername());
			
			int row = preparedStatement.executeUpdate();
			
			System.out.println("Actualización correcta." + row);
			preparedStatement.close();
			conex.CloseConnection();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Error al intentar actualizar el usuario --- " + e);
			return false;
		}
	}
	
	public boolean DeleteUserInfo(String username) {
Conexion conex = new Conexion();
		
		try {
			String query="DELETE FROM usuario WHERE username=? ";
			PreparedStatement preparedStatement =conex.GetConnection().prepareStatement(query);
			preparedStatement.setString(1, username);
			
			int row = preparedStatement.executeUpdate();
			
			System.out.println("Eliminación correcta." + row);
			preparedStatement.close();
			conex.CloseConnection();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Error al intentar eliminar el usuario --- " + e);
			return false;
		}
	}
	
}
