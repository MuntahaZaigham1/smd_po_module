package com.example.smd_po_module.classes;

public class message {
    String id, s_uid, s_name, r_uid, r_name;

    String msg;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public message(String id, String s_uid, String s_name, String r_uid, String r_name, String msg) {
        this.id = id;
        this.s_uid = s_uid;
        this.s_name = s_name;
        this.r_uid = r_uid;
        this.r_name = r_name;
        this.msg = msg;
    }

    public message() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getS_uid() {
        return s_uid;
    }

    public void setS_uid(String s_uid) {
        this.s_uid = s_uid;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getR_uid() {
        return r_uid;
    }

    public void setR_uid(String r_uid) {
        this.r_uid = r_uid;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }
}
