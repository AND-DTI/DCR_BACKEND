package com.dcr.api.model.as400;
import static com.dcr.api.utils.Auxiliar.*;
import org.springframework.security.core.GrantedAuthority;
import com.dcr.api.model.keys.User_RoleKey;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;




@Entity
@Table(name = "ACCUSERRL", schema = "HD4DCDHH")
@ApiModel
public class User_Role implements GrantedAuthority {


	@EmbeddedId
	private User_RoleKey key;

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

    public String getDtacad() {        return dtacad;    }
    public void setDtacad(String dtacad) {
        this.dtacad = dtacad;
    }

    @Override
    public String getAuthority() {        
        return trimNull(this.rolename);
    }

	public String getRolename() {
		return trimNull(this.rolename);
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}


	public void setItaudsys(String itaudsys) {
		this.itaudsys = itaudsys;
	}

	public void setItaudusr(String itaudusr) {
		this.itaudusr = itaudusr;
	}


	public void setItaudhst(String itaudhst) {
		this.itaudhst = itaudhst;
	}

	public void setItauddt(String itauddt) {
		this.itauddt = itauddt;
	}

	public void setItaudhr(String itaudhr) {
		this.itaudhr = itaudhr;
	}

	public User_RoleKey getKey() {
		return key;
	}

	public void setKey(User_RoleKey key) {
		this.key = key;
	}

}