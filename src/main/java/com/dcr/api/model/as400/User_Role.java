package com.dcr.api.model.as400;

import static com.dcr.api.utils.Auxiliar.*;

import org.springframework.security.core.GrantedAuthority;

import com.dcr.api.model.keys.User_RoleKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "ACCUSERRL", schema = "HD4DCDHH", uniqueConstraints = { @UniqueConstraint(columnNames = { "username", "roleid" }) })
@IdClass(User_RoleKey.class)
@ApiModel
public class User_Role implements GrantedAuthority {


	@Id
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "char(10)")
    private String username;

    @Id
	@TamanhoMaximo(10)
    @TamanhoMinimo(1)
    @Column(columnDefinition = "int", unique = true)
    private Integer roleid;

    @Column(columnDefinition = "char(20)")
    private String rolename;

    @Column(columnDefinition = "char(8)")
    private String dtacad;

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

    public User_Role() {

    }

    public User_Role(String username, Integer roleid, String rolename, String dtacad) {
        super();
        this.username = username;
        this.roleid = roleid;
        this.rolename = rolename;
        this.dtacad = dtacad;
    }

    public String getUsername() {        return trimNull(username);    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRoleid() {        return roleid;    }
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getDtacad() {        return dtacad;    }
    public void setDtacad(String dtacad) {
        this.dtacad = dtacad;
    }

    @Override
    public String getAuthority() {        
        return trimNull(this.rolename);
    }

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getItaudsys() {
		return itaudsys;
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
		return itaudhst;
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

}