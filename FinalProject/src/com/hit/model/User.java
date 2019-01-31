 package com.hit.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	// Table structure:
	// user(id,user_name,password) 

	@Id
	@Column(name = "userId", length=10, nullable=false, unique=true)
	// will be auto-incremented. 
	// user will not have the ability to choose his id and the server will generate one for him.
	private Integer id;
	
	
	@Column(name = "user_name", length=20, nullable=false)
	private String userName;
	
	@Column(name = "password", length=20, nullable=false)
	private String password;
	
	public User()
	{
		super();
	}
	
	//Integer ID removed by Vadim 31.01.19
	public User(String name, String password)
	{
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
		return userName;
	}

	public void setName(String name) {
		this.userName = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		
		return "User Id: " + this.id + ", User name: " + this.userName + ", password: " + this.password ; 
	}
}
