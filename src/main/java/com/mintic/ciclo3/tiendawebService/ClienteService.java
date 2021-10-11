package com.mintic.ciclo3.tiendawebService;

import java.util.List;

import com.mintic.ciclo3.tiendawebDto.ClienteDTO;

public interface ClienteService {
	boolean CreateClient(ClienteDTO clientInfo);
	boolean UpdateClient(ClienteDTO clientInfo);
	boolean DeleteClient(String dni);
	List<ClienteDTO> GetAllClients();
	ClienteDTO GetClientInfo(String dni);
}
