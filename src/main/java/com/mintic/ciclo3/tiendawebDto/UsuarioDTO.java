package com.mintic.ciclo3.tiendawebDto;

public class UsuarioDTO {

	private String username;
	private String password;
	private String name;
	private String dni;
	private String email;
	private String usuario_crea;
	
	private String option;
	
	public UsuarioDTO() {
		super();
	}
	
	public UsuarioDTO(String username, String password, String name, String dni, String email, String usuario_crea) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.dni = dni;
		this.email = email;
		this.usuario_crea = usuario_crea;
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

	public String getUsuario_crea() {
		return usuario_crea;
	}

	public void setUsuario_crea(String usuario_crea) {
		this.usuario_crea = usuario_crea;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
	
	
}
