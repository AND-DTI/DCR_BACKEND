package com.dcr.api.model;

public class ApiResponseCST {

    public enum Status {
        OK(200, "Success"),
        SERVER_ERROR(500, "Internal Server Error"),
        NOT_FOUND(400, "Register not found");

        private final int valor;
        private final String descricao;

        Status(int valor, String descricao) {
            this.valor = valor;
            this.descricao = descricao;
        }

        public int getValor() {
            return this.valor;
        }

        public String getDescricao() {
            return this.descricao;
        }

    }

    private Status status;
    private String statusDescSOAP;
    private int statusCodeSOAP;
    private String msg;
    private String recordKey;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStatusDescSOAP() {
        return statusDescSOAP;
    }

    public void setStatusDescSOAP(String statusDescSOAP) {
        this.statusDescSOAP = statusDescSOAP;
    }

    public int getStatusCodeSOAP() {
        return statusCodeSOAP;
    }

    public void setStatusCodeSOAP(int statusCodeSOAP) {
        this.statusCodeSOAP = statusCodeSOAP;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(String recordKey) {
        this.recordKey = recordKey;
    }

    public ApiResponseCST(Status status, String statusDescSOAP, int statusCodeSOAP, String msg, String recordKey) {

        this.status = status;
        this.statusDescSOAP = statusDescSOAP;
        this.statusCodeSOAP = statusCodeSOAP;
        this.msg = msg;
        this.recordKey = recordKey;

    }

    public ApiResponseCST(int status, String statusDescSOAP, int statusCodeSOAP, String msg, String recordKey) {

        switch (status) {
            case 200:
                this.status = Status.OK;
                break;
            case 400:
                this.status = Status.OK;
                break;
            default:
                this.status = Status.SERVER_ERROR;
        }

        this.statusDescSOAP = statusDescSOAP;
        this.statusCodeSOAP = statusCodeSOAP;
        this.msg = msg;
        this.recordKey = recordKey;

    }

};