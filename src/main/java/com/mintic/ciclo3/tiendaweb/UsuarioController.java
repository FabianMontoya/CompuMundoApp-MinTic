package com.mintic.ciclo3.tiendaweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mintic.ciclo3.tiendawebDto.LoginDTO;

@Controller
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class UsuarioController {

	@RequestMapping("/usuarios")
	public String InitLogin(Model model) {
		return "/AdminUsers";
	}
}
