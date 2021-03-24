package com.example.demo.com.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * keybook实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="keybook")
public class Keybook implements Serializable{

	@Id
	private Integer keyBookId;//keyBookId


	
	private String value;//value
	private String name;//name
	private String type;//type
	private String typeName;//typeName

	public Integer getKeyBookId() {
		return keyBookId;
	}

	public void setKeyBookId(Integer keyBookId) {
		this.keyBookId = keyBookId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
