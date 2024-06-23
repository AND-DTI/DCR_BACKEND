package com.dcr.api.response;

import java.util.List;

import com.dcr.api.model.dto.PendrespDTO;

public class PendrespResponse {
	private String msgErro;
	private List<PendrespDTO> erros;
	
	public String getMsgErro() {
		return msgErro;
	}
	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}
	public List<PendrespDTO> getErros() {
		return erros;
	}
	public void setErros(List<PendrespDTO> erros) {
		this.erros = erros;
	}


}
