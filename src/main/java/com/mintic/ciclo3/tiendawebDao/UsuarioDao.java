package com.mintic.ciclo3.tiendawebDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mintic.ciclo3.tiendawebDto.LoginDTO;

public class UsuarioDao {

	public boolean VerifyCredentialsUser(LoginDTO userCredentials) {
		Conexion conex = new Conexion();
		boolean exist = false;
		try {
			String query = "select username, password from usuario where username=? and password=?";
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
	
}
