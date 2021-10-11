package com.mintic.ciclo3.tiendaweb;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mintic.ciclo3.tiendawebDto.ClienteDTO;
import com.mintic.ciclo3.tiendawebService.ClienteService;
import com.mintic.ciclo3.tiendawebService.ClienteServiceImpl;

@Controller
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ClienteController {

	private ClienteService clienteService;
	
	@RequestMapping("/clientes")
	public String InitLogin(Model model) {
		this.clienteService = new ClienteServiceImpl();
		
		List<ClienteDTO> clientsList = this.clienteService.GetAllClients();
		model.addAttribute("clientsList", clientsList);
		
		return "/AdminClients";
	}
	
	@GetMapping("/cliente/create")
	public String CreateClient(Model model) {
		model.addAttribute("InfoForm", new ClienteDTO());	
		model.addAttribute("option", "create");		
		return "/ClientForm";
	}
	
	@GetMapping("/cliente/edit/{dni}")
	public String EditClient(@PathVariable("dni")String dni, Model model) {
		this.clienteService = new ClienteServiceImpl();
		ClienteDTO clientForm = this.clienteService.GetClientInfo(dni);
		
		if(clientForm == null) {
			model.addAttribute("title_error", "Cliente no existe.");
			model.addAttribute("message_error", "Lo sentimos, el cliente <b>" + dni + "</b> no es un cliente valido para el sistema.<br>" +
					"Si cree que es un error, favor comunicarse con el administrador del sistema.");
			model.addAttribute("return_url", "/clientes");
			return "/GeneralError";
		}
		
		model.addAttribute("InfoForm", clientForm);	
		model.addAttribute("option", "edit");		
		return "/ClientForm";
	}
	
	@GetMapping("/cliente/delete/{dni}")
	public String DeleteClient(@PathVariable("dni")String dni, Model model) {
		this.clienteService = new ClienteServiceImpl();
		ClienteDTO userForm = this.clienteService.GetClientInfo(dni);
		
		if(userForm == null) {
			model.addAttribute("title_error", "Cliente no existe.");
			model.addAttribute("message_error", "Lo sentimos, el cliente <b>" +dni + "</b> no es un cliente valido para el sistema.<br>" +
					"Si cree que es un error, favor comunicarse con el administrador del sistema.");
			model.addAttribute("return_url", "/clientes");
			return "/GeneralError";
		}
		
		
		if(!this.clienteService.DeleteClient(dni)) {
			model.addAttribute("title_error", "Error eliminando cliente");
			model.addAttribute("message_error", "Lo sentimos, no se logró eliminar el cliente <b>" + dni + "</b>." +
					"<br>Si cree que es un error, favor comunicarse con el administrador del sistema.");
			model.addAttribute("return_url", "/clientes");
			return "/GeneralError";
		}
		
		return GetRedirectFinalActionRoute("delete", "ok", dni);
	}
	
	@PostMapping("/clientInfo")
	public String ProcessClientInfo(@ModelAttribute("InfoForm") ClienteDTO clientInfo,  Model model, HttpSession session) {
		this.clienteService = new ClienteServiceImpl();
		
		String option = clientInfo.getOption();
		
		if(option.equals("create")) {
			System.out.println("OptionCreate");
			clientInfo.setUsuario_crea(session.getAttribute("username").toString());
			
			ClienteDTO userForm = this.clienteService.GetClientInfo(clientInfo.getDni());
			
			if(userForm != null) {
				model.addAttribute("title_error", "Cliente existe.");
				model.addAttribute("message_error", "Lo sentimos, no se puede crear el cliente <b>" + clientInfo.getDni() + "</b> dado que este ya existe" +
						".<br>Si cree que es un error, favor comunicarse con el administrador del sistema.");
				model.addAttribute("return_url", "/clientes");
				return "/GeneralError";
			}
			
			if(!this.clienteService.CreateClient(clientInfo)) {
				model.addAttribute("title_error", "Error creando cliente.");
				model.addAttribute("message_error", "Lo sentimos, ocurrió un error y no se logró crear el cliente <b>" + clientInfo.getDni() + "</b>."+ 
						"<br>Si el error persiste, favor comunicarse con el administrador del sistema.");
				model.addAttribute("return_url", "/cliente/create");
				return "/GeneralError";
			}
		} else if(option.equals("edit")) {
			System.out.println("OptionEdit");					
			
			if(!this.clienteService.UpdateClient(clientInfo)) {
				model.addAttribute("title_error", "Error actualizando cliente.");
				model.addAttribute("message_error", "Lo sentimos, ocurrió un error y no se logró actualizar la información del cliente <b>" + clientInfo.getDni() + "</b>."+
						"<br>Si el error persiste, favor comunicarse con el administrador del sistema.");
				model.addAttribute("return_url", "/cliente/edit/" + clientInfo.getDni());
				return "/GeneralError";
			}
		}
		
		return GetRedirectFinalActionRoute(option, "ok", clientInfo.getDni());
	}
	
	private String GetRedirectFinalActionRoute(String option, String result, String data) {
		return "redirect:/clientes?action="+ option +"&result=ok&data=" + Base64.getEncoder().encodeToString(data.getBytes());
	}
}
