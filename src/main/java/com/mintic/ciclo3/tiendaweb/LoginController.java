package com.mintic.ciclo3.tiendaweb;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mintic.ciclo3.tiendawebDto.LoginDTO;
import com.mintic.ciclo3.tiendawebService.LoginService;
import com.mintic.ciclo3.tiendawebService.LoginServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;

@Controller
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class LoginController {
	
	//@Autowired
	LoginService loginService;
	
	
	@RequestMapping("/login")
	public String InitLogin(Model model) {
		model.addAttribute("loginForm", new LoginDTO());
		return "/Login";
	}
	
	@RequestMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login?action=logout";
	}
	
	@PostMapping("/validatelogin")
	public String ValidateLogin(@ModelAttribute("loginForm") LoginDTO data,
	        final HttpServletRequest request, Model model, HttpSession session) {
		
		if(data.getPassword().length() == 0 && data.getUsername().length() == 0) {
			model.addAttribute("credentials_error", "Debe completar todos los datos para poder validar el acceso, por favor verificar.");
			return "/CredentialsError";
		}
		
		loginService = new LoginServiceImpl();
		boolean result = loginService.validateUser(data);
				
		if(result) {
			addUserInSession(data, session);
			return "redirect:/menu";
		} else {
			model.addAttribute("credentials_error", "Usuario o contraseña incorrecta, por favor verificar.");
			return "/CredentialsError";
		}
	}
	
	private void addUserInSession(LoginDTO userInfo, HttpSession session) {
		session.setAttribute("username", userInfo.getUsername());
	}	

}
