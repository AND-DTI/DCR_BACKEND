package com.dcr.api.model.as400;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "PSTRUC" /* , schema = "AMFLIBD"*/)
@ApiModel
public class Pstruc {


	@Id
	@Column(columnDefinition = "char(25)")
	private String pinbr;

    @Column(columnDefinition = "char(4)")
	private String usrs1;

    @Column(columnDefinition = "char(25)")
	private String cinbr;

    @Column(columnDefinition = "decimal(8,0)")
	private String edatm;

    @Column(columnDefinition = "decimal(8,0)")
	private String edato;

    @Column(columnDefinition = "decimal(8,0)")
	private String mdate;


    public String getPinbr() {        return pinbr;    }
    public void setPinbr(String pinbr) {        this.pinbr = pinbr;    }

    public String getUsrs1() {        return usrs1;    }
    public void setUsrs1(String usrs1) {        this.usrs1 = usrs1;    }

    public String getCinbr() {        return cinbr;    }
    public void setCinbr(String cinbr) {        this.cinbr = cinbr;    }

    public String getEdatm() {        return edatm;    }
    public void setEdatm(String edatm) {        this.edatm = edatm;    }

    public String getEdato() {        return edato;    }
    public void setEdato(String edato) {        this.edato = edato;    }

    public String getMdate() {        return mdate;    }
    public void setMdate(String mdate) {        this.mdate = mdate;    }


}