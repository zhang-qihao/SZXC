package com.xfinsoft.szybgzdtgl.datamodel.szybgzdtgl.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class Ac01_fjDO   implements Serializable {
    @Id
    @Column(name = "AC01ID", length = 20, nullable = false)
    private String ac01id;//	varchar2(20)	y		附件id

    @Column(name = "FJ002", length = 20)
    private String fj002;//	varchar2(20)	y		附件类型（ryzp-人员照片）

    @Column(name = "FJ011", length = 20)
    private String fj011;//	varchar2(20)	y		附件格式（如 .jpg，.txt）

    @Column(name = "FJ012", length = 50)
    private String fj012;//	varchar2(50)	y		附件名称

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "FJ013", nullable = true)
    private byte[] fj013;//	blob	y		附件

    @Column(name = "FJ014", length = 50)
    private String fj014;//	varchar2(200)	y		附件大小

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AAE036", length = 10, nullable = true)
    private Date aae036;//	date	y		上传时间

    @Column(name = "FJ100", length = 50)
    private String  fj100;//	varchar2(200)	y		父类表id

    public String getAc01id() {
        return ac01id;
    }

    public void setAc01id(String ac01id) {
        this.ac01id = ac01id;
    }

    public String getFj002() {
        return fj002;
    }

    public void setFj002(String fj002) {
        this.fj002 = fj002;
    }

    public String getFj011() {
        return fj011;
    }

    public void setFj011(String fj011) {
        this.fj011 = fj011;
    }

    public String getFj012() {
        return fj012;
    }

    public void setFj012(String fj012) {
        this.fj012 = fj012;
    }

    public byte[] getFj013() {
        return fj013;
    }

    public void setFj013(byte[] fj013) {
        this.fj013 = fj013;
    }

    public String getFj014() {
        return fj014;
    }

    public void setFj014(String fj014) {
        this.fj014 = fj014;
    }

    public Date getAae036() {
        return aae036;
    }

    public void setAae036(Date aae036) {
        this.aae036 = aae036;
    }

    public String getFj100() {
        return fj100;
    }

    public void setFj100(String fj100) {
        this.fj100 = fj100;
    }
}
