package com.example.smd_po_module.classes;

public class selected {
    String id, sid,jid,j_name,cid,c_name;

    public selected() {
    }

    public selected(String id, String sid, String jid, String j_name, String cid, String c_name) {
        this.id = id;
        this.sid = sid;
        this.jid = jid;
        this.j_name = j_name;
        this.cid = cid;
        this.c_name = c_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getJ_name() {
        return j_name;
    }

    public void setJ_name(String j_name) {
        this.j_name = j_name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
}
