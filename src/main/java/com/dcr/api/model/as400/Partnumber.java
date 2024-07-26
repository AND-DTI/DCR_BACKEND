package com.dcr.api.model.as400;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "VW_PARTNUMBER", schema = "HD4DCDHH")
@ApiModel
public class Partnumber {

    @Id
    @Column(columnDefinition = "char(25)")
    private String partnumber;

    @Column(columnDefinition = "char(30)")
    private String partdesc;

    public String getPartnumber() {
        return partnumber;
    }

    public void setPartnumber(String partnumber) {
        this.partnumber = partnumber;
    }

    public String getPartdesc() {
        return partdesc;
    }

    public void setPartdesc(String partdesc) {
        this.partdesc = partdesc;
    }
    
}
