package com.mintic.ciclo3.tiendawebService;

import java.util.List;

import com.mintic.ciclo3.tiendawebDao.ClientesDAO;
import com.mintic.ciclo3.tiendawebDto.ClienteDTO;

public class ClienteServiceImpl implements ClienteService {

	private ClientesDAO clientesDao;
	
	public ClienteServiceImpl() {
		this.clientesDao = new ClientesDAO();
	}	
	
	@Override
	public boolean CreateClient(ClienteDTO clientInfo) {		
		return this.clientesDao.InsertInfo(clientInfo);
	}

	@Override
	public boolean UpdateClient(ClienteDTO clientInfo) {
		return this.clientesDao.UpdateInfo(clientInfo);
	}

	@Override
	public boolean DeleteClient(String dni) {
		return this.clientesDao.DeleteInfo(dni);
	}

	@Override
	public List<ClienteDTO> GetAllClients() {
		return this.clientesDao.getAll();
	}

	@Override
	public ClienteDTO GetClientInfo(String dni) {
		return this.clientesDao.getById(dni);
	}

}
