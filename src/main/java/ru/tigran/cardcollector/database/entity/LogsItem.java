package ru.tigran.cardcollector.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "count_logs")
public class LogsItem {
    @Id
    @Column private Date date;
    @Column private int pciottt = 0;
    @Column private int pcimt = 0;
    @Column private int pcdt = 0;
    @Column private int pssoomt = 0;
    @Column private int pd = 0;
    @Column private int ppsta = 0;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPciottt() {
        return pciottt;
    }

    public void setPciottt(int pciottt) {
        this.pciottt = pciottt;
    }

    public int getPcimt() {
        return pcimt;
    }

    public void setPcimt(int pcimt) {
        this.pcimt = pcimt;
    }

    public int getPcdt() {
        return pcdt;
    }

    public void setPcdt(int pcdt) {
        this.pcdt = pcdt;
    }

    public int getPssoomt() {
        return pssoomt;
    }

    public void setPssoomt(int pssoomt) {
        this.pssoomt = pssoomt;
    }

    public int getPd() {
        return pd;
    }

    public void setPd(int pd) {
        this.pd = pd;
    }

    public int getPpsta() {
        return ppsta;
    }

    public void setPpsta(int ppsta) {
        this.ppsta = ppsta;
    }
}
