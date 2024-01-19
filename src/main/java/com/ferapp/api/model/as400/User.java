package com.ferapp.api.model.as400;

import java.util.Collection;
import java.util.List;
import static com.ferapp.api.utils.Auxiliar.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "ctpuser")
public class User implements UserDetails {

    @Id
    @Column(columnDefinition = "char(10)", unique = true)
    private String username;

    @Column(columnDefinition = "char(100)")
    private String name;

    @Column(columnDefinition = "int")
    private Integer userid;

    @Column(columnDefinition = "char(70)")
    private String email;

    @Column(columnDefinition = "char(20)")
    private String idarea;

    @Column(columnDefinition = "char(1)")
    private String ativo;

    @Column(columnDefinition = "char(100)")
    private String password;

    @Column(columnDefinition = "char(200)")
    private String token;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<User_Role> roles;

    @Column(columnDefinition = "char(40)")
    private String itaudsys;

    @Column(columnDefinition = "char(10)")
    private String itaudusr;

    @Column(columnDefinition = "char(20)")
    private String itaudhst;

    @Column(columnDefinition = "char(8)")
    private String itauddt;

    @Column(columnDefinition = "char(8)")
    private String itaudhr;

    public User(String username, String name, Integer userid, String email, String idarea, String ativo,
            String password, String token, List<User_Role> roles) {
        this.username = username;
        this.name = name;
        this.userid = userid;
        this.email = email;
        this.idarea = idarea;
        this.ativo = ativo;
        this.password = password;
        this.token = token;
        this.roles = roles;
    }

    public User() {

    }

    public String getUsername() {
        return trimNull(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return trimNull(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdarea() {
        return idarea;
    }

    public void setIdarea(String idarea) {
        this.idarea = idarea;
    }

    public String getAtivo() {
        return trimNull(ativo);
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getPassword() {
        return trimNull(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return trimNull(token);
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getItaudsys() {
        return trimNull(itaudsys);
    }

    public void setItaudsys(String itaudsys) {
        this.itaudsys = itaudsys;
    }

    public String getItaudusr() {
        return itaudusr;
    }

    public void setItaudusr(String itaudusr) {
        this.itaudusr = itaudusr;
    }

    public String getItaudhst() {
        return trimNull(itaudhst);
    }

    public void setItaudhst(String itaudhst) {
        this.itaudhst = itaudhst;
    }

    public String getItauddt() {
        return itauddt;
    }

    public void setItauddt(String itauddt) {
        this.itauddt = itauddt;
    }

    public String getItaudhr() {
        return itaudhr;
    }

    public void setItaudhr(String itaudhr) {
        this.itaudhr = itaudhr;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;

    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;

    }

    @Override
    public boolean isEnabled() {

        return this.getAtivo().equals("S");

    }

}