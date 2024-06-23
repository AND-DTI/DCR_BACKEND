package com.dcr.api.model.projection;

import java.util.List;

public class TpprdProjection {
	List<TipoProjection> tipos;
	//TipoProjection[] tipos;
	List<Nivel1Projection> produtos;
    

	//public TipoProjection[] getTipos() {		return tipos;	}
	//public void setTipos(TipoProjection[] tipos) {		this.tipos = tipos;	}
	
	public List<Nivel1Projection> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Nivel1Projection> produtos) {
		this.produtos = produtos;
	}

	public List<TipoProjection> getTipos() {		return tipos;	}
	public void setTipos(List<TipoProjection> tipos) {		this.tipos = tipos;	}
}
