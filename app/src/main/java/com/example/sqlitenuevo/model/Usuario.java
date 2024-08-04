package com.example.sqlitenuevo.model;

public class Usuario {
    private Integer _id;
    private String nombres;
    private String user;
    private String clave;

    public Usuario() {
    }

    public Usuario(Integer _id, String nombres, String user, String clave) {
        this._id = _id;
        this.nombres = nombres;
        this.user = user;
        this.clave = clave;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
