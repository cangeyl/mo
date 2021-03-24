package com.example.demo.com.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * actor实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="actor")
public class Actor implements Serializable{

	@Id
	private Integer actorid;//actorid


	
	private String actorname;//actorname

	public Integer getActorid() {
		return actorid;
	}

	public void setActorid(Integer actorid) {
		this.actorid = actorid;
	}

	public String getActorname() {
		return actorname;
	}

	public void setActorname(String actorname) {
		this.actorname = actorname;
	}
}
