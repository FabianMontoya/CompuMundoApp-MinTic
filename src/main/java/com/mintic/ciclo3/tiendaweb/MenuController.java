package com.mintic.ciclo3.tiendaweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mintic.ciclo3.tiendawebDto.LoginDTO;

@Controller
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class MenuController {

	@GetMapping("/menu")
	public String InitLogin() {
		return "/Menu";
	}
}
