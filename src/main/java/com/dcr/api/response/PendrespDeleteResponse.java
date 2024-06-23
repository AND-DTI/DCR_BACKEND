package com.dcr.api.response;

import java.util.List;

import com.dcr.api.model.dto.PendrespDeleteDTO;

public class PendrespDeleteResponse {
	private String msgErro;
	private List<PendrespDeleteDTO> erros;
	
	public String getMsgErro() {
		return msgErro;
	}
	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}
	public List<PendrespDeleteDTO> getErros() {
		return erros;
	}
	public void setErros(List<PendrespDeleteDTO> erros) {
		this.erros = erros;
	}
	

}
