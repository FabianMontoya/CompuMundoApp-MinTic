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

import com.mintic.ciclo3.tiendawebDto.ProveedorDTO;
import com.mintic.ciclo3.tiendawebService.ProveedorService;
import com.mintic.ciclo3.tiendawebService.ProveedorServiceImpl;

@Controller
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ProveedoresController {

	private ProveedorService proveedorService;
	
	@RequestMapping("/proveedores")
	public String InitLogin(Model model) {
		
		this.proveedorService = new ProveedorServiceImpl();
		
		List<ProveedorDTO> providersList = this.proveedorService.GetAllProviders();
		model.addAttribute("providersList", providersList);
		
		return "/AdminProviders";
	}
	
	@GetMapping("/proveedor/create")
	public String CreateProvider(Model model) {
		model.addAttribute("InfoForm", new ProveedorDTO());	
		model.addAttribute("option", "create");		
		return "/ProviderForm";
	}
	
	@GetMapping("/proveedor/edit/{dni}")
	public String EditProvider(@PathVariable("dni")String dni, Model model) {
		this.proveedorService = new ProveedorServiceImpl();
		ProveedorDTO providerForm = this.proveedorService.GetProviderInfo(dni);
		
		if(providerForm == null) {
			model.addAttribute("title_error", "Proveedor no existe.");
			model.addAttribute("message_error", "Lo sentimos, el proveedor <b>" + dni + "</b> no es un proveedor valido para el sistema.<br>" +
					"Si cree que es un error, favor comunicarse con el administrador del sistema.");
			model.addAttribute("return_url", "/proveedores");
			return "/GeneralError";
		}
		
		model.addAttribute("InfoForm", providerForm);	
		model.addAttribute("option", "edit");		
		return "/ProviderForm";
	}
	
	@GetMapping("/proveedor/delete/{dni}")
	public String DeleteProvider(@PathVariable("dni")String dni, Model model) {
		this.proveedorService = new ProveedorServiceImpl();
		ProveedorDTO userForm = this.proveedorService.GetProviderInfo(dni);
		
		if(userForm == null) {
			model.addAttribute("title_error", "Proveedor no existe.");
			model.addAttribute("message_error", "Lo sentimos, el proveedor <b>" +dni + "</b> no es un proveedor valido para el sistema.<br>" +
					"Si cree que es un error, favor comunicarse con el administrador del sistema.");
			model.addAttribute("return_url", "/proveedores");
			return "/GeneralError";
		}
		
		
		if(!this.proveedorService.DeleteProvider(dni)) {
			model.addAttribute("title_error", "Error eliminando proveedor");
			model.addAttribute("message_error", "Lo sentimos, no se logró eliminar el proveedor <b>" + dni + "</b>." +
					"<br>Si cree que es un error, favor comunicarse con el administrador del sistema.");
			model.addAttribute("return_url", "/proveedores");
			return "/GeneralError";
		}
		
		return GetRedirectFinalActionRoute("delete", "ok", dni);
	}
	
	@PostMapping("/providerInfo")
	public String ProcessProviderInfo(@ModelAttribute("InfoForm") ProveedorDTO providerInfo,  Model model, HttpSession session) {
		this.proveedorService = new ProveedorServiceImpl();
		
		String option = providerInfo.getOption();
		
		if(option.equals("create")) {
			System.out.println("OptionCreate");
			providerInfo.setUsuario_crea(session.getAttribute("username").toString());
			
			ProveedorDTO userForm = this.proveedorService.GetProviderInfo(providerInfo.getDni());
			
			if(userForm != null) {
				model.addAttribute("title_error", "Proveedor existe.");
				model.addAttribute("message_error", "Lo sentimos, no se puede crear el proveedor <b>" + providerInfo.getDni() + "</b> dado que este ya existe" +
						".<br>Si cree que es un error, favor comunicarse con el administrador del sistema.");
				model.addAttribute("return_url", "/proveedores");
				return "/GeneralError";
			}
			
			if(!this.proveedorService.CreateProvider(providerInfo)) {
				model.addAttribute("title_error", "Error creando proveedor.");
				model.addAttribute("message_error", "Lo sentimos, ocurrió un error y no se logró crear el proveedor <b>" + providerInfo.getDni() + "</b>."+ 
						"<br>Si el error persiste, favor comunicarse con el administrador del sistema.");
				model.addAttribute("return_url", "/proveedor/create");
				return "/GeneralError";
			}
		} else if(option.equals("edit")) {
			System.out.println("OptionEdit");					
			
			if(!this.proveedorService.UpdateProvider(providerInfo)) {
				model.addAttribute("title_error", "Error actualizando proveedor.");
				model.addAttribute("message_error", "Lo sentimos, ocurrió un error y no se logró actualizar la información del proveedor <b>" + providerInfo.getDni() + "</b>."+
						"<br>Si el error persiste, favor comunicarse con el administrador del sistema.");
				model.addAttribute("return_url", "/proveedor/edit/" + providerInfo.getDni());
				return "/GeneralError";
			}
		}
		
		return GetRedirectFinalActionRoute(option, "ok", providerInfo.getDni());
	}
	
	private String GetRedirectFinalActionRoute(String option, String result, String data) {
		return "redirect:/proveedores?action="+ option +"&result=ok&data=" + Base64.getEncoder().encodeToString(data.getBytes());
	}
}
