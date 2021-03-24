package com.example.demo.com.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * clazz实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="clazz")
public class Clazz implements Serializable{

	@Id
	private Integer id;//id


	
	private String name;//name

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
