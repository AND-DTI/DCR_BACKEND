package com.dcr.api.model.dto;

import java.util.List;

public record UpdateUserDTO(String username, String name, String email, String tpfunc, String ativo, String idarea, String password, String changePassword, List<String> roles) {

}
