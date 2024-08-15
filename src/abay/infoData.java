/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abay;

import java.sql.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Yalem
 */
public class infoData {

    private String customerid;
    private String consumedunit;
    private String selected;
    private String payamount;
    private Date date;

    public infoData(String customerid, String consumedunit, String selected, String payamount, Date date) {
        this.customerid = customerid;
        this.consumedunit = consumedunit;
        this.selected = selected;
        this.payamount = payamount;
        this.date = date;
    }

    /**
     * @return the customerid
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * @param customerid the customerid to set
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    /**
     * @return the consumedunit
     */
    public String getConsumedunit() {
        return consumedunit;
    }

    /**
     * @param consumedunit the consumedunit to set
     */
    public void setConsumedunit(String consumedunit) {
        this.consumedunit = consumedunit;
    }

    /**
     * @return the paymethod
     */
    public String getSelected() {
        return selected;
    }

    /**
     * @param paymethod the paymethod to set
     */
    public void setPaymethod(String paymethod) {
        this.selected = selected;
    }

    /**
     * @return the payamount
     */
    public String getPayamount() {
        return payamount;
    }

    /**
     * @param payamount the payamount to set
     */
    public void setPayamount(String payamount) {
        this.payamount = payamount;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    

}
