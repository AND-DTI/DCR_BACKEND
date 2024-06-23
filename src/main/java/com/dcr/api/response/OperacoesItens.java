package com.dcr.api.response;

import java.util.List;

public class OperacoesItens {
	private String name;
	private String icon;
	private List<Object> items;
	private Integer id;
	private Integer idPai;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<Object> getItems() {
		return items;
	}
	public void setItems(List<Object> items) {
		this.items = items;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdPai() {
		return idPai;
	}
	public void setIdPai(Integer idPai) {
		this.idPai = idPai;
	}

}
