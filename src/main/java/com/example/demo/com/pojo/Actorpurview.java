package com.example.demo.com.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * actorpurview实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="actorpurview")
public class Actorpurview implements Serializable{

	@Id
	private Integer actorpurviewid;//actorpurviewid


	
	private Integer actorid;//actorid
	private Integer purviewid;//purviewid

	public Integer getActorpurviewid() {
		return actorpurviewid;
	}

	public void setActorpurviewid(Integer actorpurviewid) {
		this.actorpurviewid = actorpurviewid;
	}

	public Integer getActorid() {
		return actorid;
	}

	public void setActorid(Integer actorid) {
		this.actorid = actorid;
	}

	public Integer getPurviewid() {
		return purviewid;
	}

	public void setPurviewid(Integer purviewid) {
		this.purviewid = purviewid;
	}
}
