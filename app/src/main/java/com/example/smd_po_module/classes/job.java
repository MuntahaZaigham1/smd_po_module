package com.example.smd_po_module.classes;

public class job {
    String cid;
    String jid;
    String position;
    String Salary;
    String type_employement;
    String skill_Set_req;
    String responsibilities;

    public job() {
    }

    public job(String cid, String jid, String position, String salary, String type_employement, String skill_Set_req, String responsibilities) {
        this.cid = cid;
        this.jid = jid;
        this.position = position;
        Salary = salary;
        this.type_employement = type_employement;
        this.skill_Set_req = skill_Set_req;
        this.responsibilities = responsibilities;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getType_employement() {
        return type_employement;
    }

    public void setType_employement(String type_employement) {
        this.type_employement = type_employement;
    }

    public String getSkill_Set_req() {
        return skill_Set_req;
    }

    public void setSkill_Set_req(String skill_Set_req) {
        this.skill_Set_req = skill_Set_req;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
}
