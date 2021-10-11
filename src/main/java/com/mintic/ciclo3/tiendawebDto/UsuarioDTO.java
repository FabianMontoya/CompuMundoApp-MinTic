package com.mintic.ciclo3.tiendawebDto;

public class UsuarioDTO {

	private String username;
	private String password;
	private String name;
	private String dni;
	private String email;
	
	public UsuarioDTO() {
		super();
	}
	
	public UsuarioDTO(String username, String password, String name, String dni, String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.dni = dni;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
