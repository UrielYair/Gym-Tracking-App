 package com.hit.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "userId", length=10, nullable=false, unique=true)
	private Integer id;
	
	@Column(name = "name", length=20, nullable=false)
	private String name;
	
	@Column(name = "password", length=20, nullable=false)
	private String password;
	
	public User()
	{
		super();
	}
	
	public User(Integer id, String name, String password)
	{
		this.setId(id);
		this.setName(name);
		this.setPassword(password);
	}


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		
		return "User Id: " + this.id + ", User name: " + this.name + ", password: " + this.password ; 
	}
}
