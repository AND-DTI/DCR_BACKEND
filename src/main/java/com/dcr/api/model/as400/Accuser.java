package com.dcr.api.model.as400;
import static com.dcr.api.utils.Auxiliar.trimNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
@Table(name = "ACCUSER", schema = "HD4DCDHH")
@ApiModel
public class Accuser implements UserDetails {


	@Id
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "char(10)", unique = true)
    private String username;

	@TamanhoMaximo(100)
	@TamanhoMinimo(5)
    @Column(columnDefinition = "char(100)")
    private String name;

	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Long userid;

    @TamanhoMaximo(70)
    @TamanhoMinimo(1)
    @Column(columnDefinition = "char(70)")
    private String email;

    @TamanhoMaximo(20)
    @TamanhoMinimo(1)
    @Column(columnDefinition = "char(20)")
    private String idarea;

    @TamanhoMaximo(1)
    @TamanhoMinimo(1)
    @Column(columnDefinition = "char(1)")
    private String ativo;

    @Column(columnDefinition = "char(100)")
    private String password;

    @Column(columnDefinition = "char(200)")
    private String token;
    
    @Column(columnDefinition = "char(100)")
    private String cdvrfy;
    
    @Column(columnDefinition = "char(1)")
    private String tpfunc;
    
    @Column(name = "timevrfy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timevrfy;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumns({
        @JoinColumn(name = "username", referencedColumnName = "username")
    })
    private List<User_Role> roles;

    @Column(columnDefinition = "char(40)")
    private String itaudsys;

    @Column(columnDefinition = "char(10)")
    private String itaudusr;

    @Column(columnDefinition = "char(30)")
    private String itaudhst;

    @Column(columnDefinition = "char(8)")
    private String itauddt;

    @Column(columnDefinition = "char(8)")
    private String itaudhr;

    public Accuser(String username, String name, Long userid, String email, String idarea, String ativo,
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

    public Accuser() {

    }

    public String getUsername() {
        return trimNull(username);
    }
    
    public String getUsernameForUpdate() {
        return username;
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

    public List<User_Role> getRoles() {
    	return this.roles;
    }

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
        //return this.roles;
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

	public String getCdvrfy() {
		return cdvrfy;
	}

	public void setCdvrfy(String cdvrfy) {
		this.cdvrfy = cdvrfy;
	}

	public void setTimevrfy(Date timevrfy) {
		this.timevrfy = timevrfy;
	}

	public Date getTimevrfy() {
		return timevrfy;
	}

	public void setRoles(List<User_Role> roles) {
		this.roles = roles;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getTpfunc() {
		return tpfunc;
	}

	public void setTpfunc(String tpfunc) {
		this.tpfunc = tpfunc;
	}

}