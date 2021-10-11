package com.mintic.ciclo3.tiendawebService;

import java.util.List;

import com.mintic.ciclo3.tiendawebDto.ProveedorDTO;

public interface ProveedorService {
	boolean CreateProvider(ProveedorDTO providerInfo);
	boolean UpdateProvider(ProveedorDTO providerInfo);
	boolean DeleteProvider(String dni);
	List<ProveedorDTO> GetAllProviders();
	ProveedorDTO GetProviderInfo(String dni);
}
