package com.dcr.api.response;

import java.util.List;

public class OperacoesResponse {
	private String titleName;
	private Integer id;
	private List<OperacoesItens> childrensItems;
	
	
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public List<OperacoesItens> getChildrensItems() {
		return childrensItems;
	}
	public void setChildrensItems(List<OperacoesItens> childrensItems) {
		this.childrensItems = childrensItems;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
