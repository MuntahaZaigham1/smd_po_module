package com.example.smd_po_module.classes;

public class company {
    String cid;

    String name;
    String cmmi;
    String ceo;
    String address;
    String no_recruits;
    String email;
    String phone;

    public company() {
    }

    public company(String cid, String name, String cmmi, String ceo, String address, String no_recruits, String email, String phone) {
        this.name = name;
        this.cmmi = cmmi;
        this.ceo = ceo;
        this.address = address;
        this.no_recruits = no_recruits;
        this.email = email;
        this.phone = phone;

        this.cid=cid;
    }



    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmmi() {
        return cmmi;
    }

    public void setCmmi(String cmmi) {
        this.cmmi = cmmi;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNo_recruits() {
        return no_recruits;
    }

    public void setNo_recruits(String no_recruits) {
        this.no_recruits = no_recruits;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
