package com.mintic.ciclo3.tiendawebDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mintic.ciclo3.tiendawebDto.ClienteDTO;

public class ClientesDAO {

	public List<ClienteDTO> getAll() {
		List<ClienteDTO> result = new ArrayList<>();
		Conexion conex = new Conexion();
		
		try {
			String query="SELECT dni, name, tel, email, address, usuario_crea FROM cliente ORDER BY name ASC";
			PreparedStatement consulta =conex.GetConnection().prepareStatement(query);
			ResultSet res=consulta.executeQuery();
			
			
			while(res.next()) {
				ClienteDTO us= new ClienteDTO();
				
				us.setDni(res.getString("dni"));
				us.setName(res.getString("name"));
				us.setTel(res.getString("tel"));
				us.setEmail(res.getString("email"));
				us.setAddress(res.getString("address"));
				us.setUsuario_crea(res.getString("usuario_crea"));
				
				result.add(us);
			}
			res.close();
			consulta.close();
			conex.CloseConnection();
			
		} catch (Exception e) {
			System.out.println("Error al intentar consultar la información de clientes --- " + e);
		}
		
		return result;
	}
	
	public ClienteDTO getById(String dni) {
		ClienteDTO result = null;
		Conexion conex = new Conexion();
		
		try {
			String query="SELECT dni, name, tel, email, address, usuario_crea FROM cliente WHERE dni=?";
			PreparedStatement consulta =conex.GetConnection().prepareStatement(query);
			consulta.setString(1, dni);
			
			ResultSet res=consulta.executeQuery();			
			
			while(res.next()) {
				result = new ClienteDTO();
				
				result.setDni(res.getString("dni"));
				result.setName(res.getString("name"));
				result.setTel(res.getString("tel"));
				result.setEmail(res.getString("email"));
				result.setAddress(res.getString("address"));
				result.setUsuario_crea(res.getString("usuario_crea"));
			}
			
			res.close();
			consulta.close();
			conex.CloseConnection();
			
		} catch (Exception e) {
			System.out.println("Error al intentar consultar la información del cliente --- " + e);
		}
		
		return result;
	}
	
	public boolean InsertInfo(ClienteDTO userInfo) {
		Conexion conex = new Conexion();
		
		try {
			String query="INSERT INTO cliente (dni, name, tel, email, address, usuario_crea) VALUES " + 
							"(?,?,?,?,?,?) ";
			PreparedStatement preparedStatement =conex.GetConnection().prepareStatement(query);
			preparedStatement.setString(1, userInfo.getDni());
			preparedStatement.setString(2, userInfo.getName());
			preparedStatement.setString(3, userInfo.getTel());
			preparedStatement.setString(4, userInfo.getEmail());
			preparedStatement.setString(5, userInfo.getAddress());
			preparedStatement.setString(6, userInfo.getUsuario_crea());
			
			int row = preparedStatement.executeUpdate();
			
			System.out.println("Registro correcto." + row);
			preparedStatement.close();
			conex.CloseConnection();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Error al intentar crear el cliente --- " + e);
			return false;
		}
	}
	
	public boolean UpdateInfo(ClienteDTO userInfo) {
		Conexion conex = new Conexion();
		
		try {
			
			String query="UPDATE cliente SET name=?, tel=?, email=?, address=? WHERE dni=?";
			
			PreparedStatement preparedStatement =conex.GetConnection().prepareStatement(query);
			
			
			preparedStatement.setString(1, userInfo.getName());
			preparedStatement.setString(2, userInfo.getTel());
			preparedStatement.setString(3, userInfo.getEmail());
			preparedStatement.setString(4, userInfo.getAddress());
			preparedStatement.setString(5, userInfo.getDni());
			
			int row = preparedStatement.executeUpdate();
			
			System.out.println("Actualización correcta." + row);
			preparedStatement.close();
			conex.CloseConnection();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Error al intentar actualizar el cliente --- " + e);
			return false;
		}
	}
	
	public boolean DeleteInfo(String dni) {
		Conexion conex = new Conexion();
		
		try {
			String query="DELETE FROM cliente WHERE dni=? ";
			PreparedStatement preparedStatement =conex.GetConnection().prepareStatement(query);
			preparedStatement.setString(1, dni);
			
			int row = preparedStatement.executeUpdate();
			
			System.out.println("Eliminación correcta." + row);
			preparedStatement.close();
			conex.CloseConnection();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Error al intentar eliminar el cliente --- " + e);
			return false;
		}
	}
	
}
