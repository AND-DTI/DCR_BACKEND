package com.dcr.api.model.dto;

import java.util.List;

public record CreateUserDTO(String username, String password, String name, String email, String idArea, String ativo, List<String> roles, String tpfunc) {
	

}
