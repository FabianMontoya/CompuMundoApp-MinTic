package com.mintic.ciclo3.tiendawebService;

import java.util.List;

import com.mintic.ciclo3.tiendawebDao.ProveedoresDAO;
import com.mintic.ciclo3.tiendawebDto.ProveedorDTO;

public class ProveedorServiceImpl implements ProveedorService {

	private ProveedoresDAO proveedoresDao;
	
	public ProveedorServiceImpl() {
		this.proveedoresDao = new ProveedoresDAO();
	}
	
	@Override
	public boolean CreateProvider(ProveedorDTO providerInfo) {
		return this.proveedoresDao.InsertInfo(providerInfo);
	}

	@Override
	public boolean UpdateProvider(ProveedorDTO providerInfo) {
		return this.proveedoresDao.UpdateInfo(providerInfo);
	}

	@Override
	public boolean DeleteProvider(String dni) {
		return this.proveedoresDao.DeleteInfo(dni);
	}

	@Override
	public List<ProveedorDTO> GetAllProviders() {
		return this.proveedoresDao.getAll();
	}

	@Override
	public ProveedorDTO GetProviderInfo(String dni) {
		return this.proveedoresDao.getById(dni);
	}

}
