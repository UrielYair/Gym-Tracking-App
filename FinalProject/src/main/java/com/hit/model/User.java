 package com.hit.model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
	
	// Table structure:
	// user(id,user_name,password) 

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "Id", length=10, nullable=false, unique=true)
	// will be auto-incremented. 
	// user will not have the ability to choose his id and the server will generate one for him.
	private Integer id;
	
	@Column(name = "username", length=20, nullable=false, unique=true)
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
		//you should set some id for the use of hibernate  
		this.setId(0); // will be assigned automatically by the auto-increment.
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
