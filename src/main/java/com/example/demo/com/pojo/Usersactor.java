package com.example.demo.com.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * usersactor实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="usersactor")
public class Usersactor implements Serializable{

	@Id
	private Integer useractorid;//useractorid


	
	private Integer usersid;//usersid
	private Integer actorid;//actorid

	public Integer getUseractorid() {
		return useractorid;
	}

	public void setUseractorid(Integer useractorid) {
		this.useractorid = useractorid;
	}

	public Integer getUsersid() {
		return usersid;
	}

	public void setUsersid(Integer usersid) {
		this.usersid = usersid;
	}

	public Integer getActorid() {
		return actorid;
	}

	public void setActorid(Integer actorid) {
		this.actorid = actorid;
	}
}
