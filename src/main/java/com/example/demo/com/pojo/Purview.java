package com.example.demo.com.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * purview实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="purview")
public class Purview implements Serializable{

	@Id
	private Integer purviewid;//purviewid


	
	private String purviewname;//purviewname
	private String parentId;//parentId
	private String url;//url

	public Integer getPurviewid() {
		return purviewid;
	}

	public void setPurviewid(Integer purviewid) {
		this.purviewid = purviewid;
	}

	public String getPurviewname() {
		return purviewname;
	}

	public void setPurviewname(String purviewname) {
		this.purviewname = purviewname;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
