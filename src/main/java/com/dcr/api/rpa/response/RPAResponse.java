package com.dcr.api.rpa.response;

public class RPAResponse {
    

    public enum Status {

        OK(200,"Success"),
        SERVER_ERROR(500, "Internal Server Error"),
        NOT_FOUND(400, "Register not found");

        private final int valor;
        private final String descricao;

        Status(int valor, String descricao) {
            this.valor = valor;
            this.descricao = descricao;
        }

        public int getValor() { return this.valor; }
        public String getDescricao() { return this.descricao; }

    }

    private Status status;
    private String statusDesc;
    private int statusCode;        
    private String msg;
    private String recordKey;
    private Object reponseEntity; //Object? old String



    public RPAResponse() {
    }

    //Default constructor
    public RPAResponse(Status status, String statusDesc, int statusCode, String msg, String recordKey, String reponseEntity){
        
        this.status = status;
        this.statusDesc = statusDesc;
        this.statusCode = statusCode;
        this.msg = msg;        
        this.recordKey = recordKey;
        this.reponseEntity = reponseEntity;

    }

    public RPAResponse(int status, String statusDesc, int statusCode, String msg, String recordKey, String reponseEntity){
        
        switch(status){
            case 200: this.status = Status.OK; break;
            case 400: this.status = Status.OK; break;
            default:  this.status = Status.SERVER_ERROR;
        }
               
        this.statusDesc = statusDesc;
        this.statusCode = statusCode;
        this.msg = msg;        
        this.recordKey = recordKey;
        this.reponseEntity = reponseEntity;

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        this.status = statusCode==200?Status.OK:statusCode==400?Status.NOT_FOUND:Status.SERVER_ERROR;
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

    public Object getReponseEntity() {        return reponseEntity;    }
    public void setReponseEntity(Object reponseEntity) {        this.reponseEntity = reponseEntity;    }

    //public String getReponseEntity() {        return reponseEntity;    }
    //public void setReponseEntity(String reponseEntity) {        this.reponseEntity = reponseEntity;    }



}