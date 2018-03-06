package com.example.krima.chanakynititingujarati.Mybean;

import java.io.Serializable;

/**
 * Created by KRIMA on 12-08-2017.
 */

public class MyBean implements Serializable{
    String cId,cTitle,cdesc;
    String IMEI,ImagePath;

    @Override
    public String toString() {
        return "MyBean{" +
                "cId='" + cId + '\'' +
                ", cTitle='" + cTitle + '\'' +
                ", cdesc='" + cdesc + '\'' +
                ", IMEI='" + IMEI + '\'' +
                '}';
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }
}
