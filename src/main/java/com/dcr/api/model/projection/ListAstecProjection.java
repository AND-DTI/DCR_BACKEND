package com.dcr.api.model.projection;

import java.util.List;

import com.dcr.api.model.dto.AstecDTO;

public class ListAstecProjection {

	//List<TipoProjection> tipos;	
	List<TipoProjection2> tipos;
	List<AstecDTO> produtos;

	public List<TipoProjection2> getTipos() {		return tipos;	}
	public void setTipos(List<TipoProjection2> tipos) {		this.tipos = tipos;	}

	public List<AstecDTO> getProdutos() {		return produtos;	}
	public void setProdutos(List<AstecDTO> produtos) {		this.produtos = produtos;
	}
    

}
