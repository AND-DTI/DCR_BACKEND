package com.dcr.api.configs.security;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Auditoria {

    private String data_audit;

    private String hora_audit;

    public String getData_audit() {
        return data_audit;
    }

    public String getHora_audit() {
        return hora_audit;
    }

    public Auditoria() {

        LocalDateTime agora = LocalDateTime.now();
        this.data_audit = agora.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.hora_audit = agora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

    }

}