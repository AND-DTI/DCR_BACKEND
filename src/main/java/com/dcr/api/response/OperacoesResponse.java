package com.dcr.api.response;

import java.util.List;

public class OperacoesResponse {
	private String titleName;
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
}
