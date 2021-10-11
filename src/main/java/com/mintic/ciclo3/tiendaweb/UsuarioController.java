package com.mintic.ciclo3.tiendaweb;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.mintic.ciclo3.tiendawebDto.UsuarioDTO;
import com.mintic.ciclo3.tiendawebService.UsuarioService;
import com.mintic.ciclo3.tiendawebService.UsuarioServiceImpl;

@Controller
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class UsuarioController {

	private UsuarioService usuarioService;
	
	
	@RequestMapping("/usuarios")
	public String InitLogin(Model model) {
		this.usuarioService = new UsuarioServiceImpl();
		
		List<UsuarioDTO> userList = this.usuarioService.GetAllUsers();
		model.addAttribute("usersList", userList);
		
		return "/AdminUsers";
	}
	
	@GetMapping("/usuario/create")
	public String CreateUser(Model model) {
		model.addAttribute("userInfoForm", new UsuarioDTO());	
		model.addAttribute("option", "create");		
		return "/UserForm";
	}
	
	@GetMapping("/usuario/edit/{username}")
	public String EditUser(@PathVariable("username")String username, Model model) {
		this.usuarioService = new UsuarioServiceImpl();
		UsuarioDTO userForm = this.usuarioService.GetUserInfo(username);
		
		if(userForm == null) {
			model.addAttribute("title_error", "Usuario no existe.");
			model.addAttribute("message_error", "Lo sentimos, el usuario <b>" +username + "</b> no es un usuario valido para el sistema.<br>" +
					"Si cree que es un error, favor comunicarse con el administrador del sistema.");
			model.addAttribute("return_url", "/usuarios");
			return "/GeneralError";
		}
		
		model.addAttribute("userInfoForm", userForm);	
		model.addAttribute("option", "edit");		
		return "/UserForm";
	}
	
	@GetMapping("/usuario/delete/{username}")
	public String DeleteUser(@PathVariable("username")String username, Model model) {
		this.usuarioService = new UsuarioServiceImpl();
		UsuarioDTO userForm = this.usuarioService.GetUserInfo(username);
		
		if(userForm == null) {
			model.addAttribute("title_error", "Usuario no existe.");
			model.addAttribute("message_error", "Lo sentimos, el usuario <b>" +username + "</b> no es un usuario valido para el sistema.<br>" +
					"Si cree que es un error, favor comunicarse con el administrador del sistema.");
			model.addAttribute("return_url", "/usuarios");
			return "/GeneralError";
		}
		
		if(username.equals("admininicial")) {
			model.addAttribute("title_error", "Error eliminando usuario");
			model.addAttribute("message_error", "Lo sentimos, no se puede eliminar el usuario <b>" +username + "</b> dado que este es un <u>usuario inicial</u>." +
					"<br>Si cree que es un error, favor comunicarse con el administrador del sistema.");
			model.addAttribute("return_url", "/usuarios");
			return "/GeneralError";
		}
		
		
		if(!this.usuarioService.DeleteUser(username)) {
			model.addAttribute("title_error", "Error eliminando usuario");
			model.addAttribute("message_error", "Lo sentimos, no se logró eliminar el usuario <b>" +username + "</b>." +
					"<br>Si cree que es un error, favor comunicarse con el administrador del sistema.");
			model.addAttribute("return_url", "/usuarios");
			return "/GeneralError";
		}
		
		return GetRedirectFinalActionRoute("delete", "ok", username);
	}
	
	@PostMapping("/userInfo")
	public String ProcessUserInfo(@ModelAttribute("userInfoForm") UsuarioDTO userInfo,  Model model, HttpSession session) {
		this.usuarioService = new UsuarioServiceImpl();
		
		String option = userInfo.getOption();
		
		if(option.equals("create")) {
			System.out.println("OptionCreate");
			userInfo.setUsuario_crea(session.getAttribute("username").toString());
			System.out.println("Usuario Crea " + userInfo.getUsuario_crea());
			
			UsuarioDTO userForm = this.usuarioService.GetUserInfo(userInfo.getUsername());
			
			if(userForm != null) {
				model.addAttribute("title_error", "Usuario existe.");
				model.addAttribute("message_error", "Lo sentimos, no se puede crear el usuario <b>" + userInfo.getUsername() + "</b> dado que este ya existe" +
						".<br>Si cree que es un error, favor comunicarse con el administrador del sistema.");
				model.addAttribute("return_url", "/usuarios");
				return "/GeneralError";
			}
			
			if(!this.usuarioService.CreateUser(userInfo)) {
				model.addAttribute("title_error", "Error creando usuario.");
				model.addAttribute("message_error", "Lo sentimos, ocurrió un error y no se logró crear el usuario <b>" + userInfo.getUsername() + "</b>."+ 
						"<br>Si el error persiste, favor comunicarse con el administrador del sistema.");
				model.addAttribute("return_url", "/usuario/create");
				return "/GeneralError";
			}
		} else if(option.equals("edit")) {
			System.out.println("OptionEdit");
			
			if(userInfo.getUsername().equals("admininicial")) {
				model.addAttribute("title_error", "Error actualizando usuario");
				model.addAttribute("message_error", "Lo sentimos, no se puede actualizar el usuario <b>" + userInfo.getUsername() + "</b> dado que este es un <u>usuario inicial</u>." +
						"<br>Si cree que es un error, favor comunicarse con el administrador del sistema.");
				model.addAttribute("return_url", "/usuarios");
				return "/GeneralError";
			}
			
			
			if(!this.usuarioService.UpdateUser(userInfo)) {
				model.addAttribute("title_error", "Error actualizando usuario.");
				model.addAttribute("message_error", "Lo sentimos, ocurrió un error y no se logró actualizar la información del usuario <b>" + userInfo.getUsername() + "</b>."+
						"<br>Si el error persiste, favor comunicarse con el administrador del sistema.");
				model.addAttribute("return_url", "/usuario/edit/" + userInfo.getUsername());
				return "/GeneralError";
			}
		}
		
		return GetRedirectFinalActionRoute(option, "ok", userInfo.getUsername());
	}
	
	private String GetRedirectFinalActionRoute(String option, String result, String data) {
		return "redirect:/usuarios?action="+ option +"&result=ok&data=" + Base64.getEncoder().encodeToString(data.getBytes());
	}
}
