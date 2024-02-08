package com.dcr.api.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class ErrorResponse {
	private Boolean isValid;
	private String msg;
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
