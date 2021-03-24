package com.example.demo.com.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class wer {
    @Id
    private String username;
    private String actorname;
    private String purview;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActorname() {
        return actorname;
    }

    public void setActorname(String actorname) {
        this.actorname = actorname;
    }

    public String getPurview() {
        return purview;
    }

    public void setPurview(String purview) {
        this.purview = purview;
    }
}
