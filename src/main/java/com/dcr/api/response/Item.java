package com.dcr.api.response;

public class Item {
	private String name;
	private String to;
	private String icon;
	private Integer id;
	private Integer idPai;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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
