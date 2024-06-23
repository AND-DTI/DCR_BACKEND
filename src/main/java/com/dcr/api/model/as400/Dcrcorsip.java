package com.dcr.api.model.as400;

import com.dcr.api.model.keys.DcrcorsipKey;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRCORSIP", schema = "HD4DCDHH")
@ApiModel
public class Dcrcorsip {
	
	@EmbeddedId
	private DcrcorsipKey key;
	
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

	public DcrcorsipKey getKey() {
		return key;
	}

	public void setKey(DcrcorsipKey key) {
		this.key = key;
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
}
