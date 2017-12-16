package com.mobile.mobilecustomer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ProductMB") // table ProductMB dau
public class Mobile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="mobileid")
    private int mobileid;
    @Column(name="name_mobile")
    private String namemobile;
    @Column(name="description")
    private String decription;
    @Column(name = "cost")
    private int cost;
    @Column (name = "image")
    private String image;

    public void setMobileid(int mobileid) {
        this.mobileid = mobileid;
    }

    public void setNamemobile(String namemobile) {
        this.namemobile = namemobile;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMobileid() {
        return mobileid;
    }

    public String getNamemobile() {
        return namemobile;
    }

    public String getDecription() {
        return decription;
    }

    public int getCost() {
        return cost;
    }

    public String getImage() {
        return image;
    }
}
