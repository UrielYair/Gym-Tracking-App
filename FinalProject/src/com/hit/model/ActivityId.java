package com.hit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActivityId implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "userId", length=10, nullable=false)
	private Integer id;
		
	@Column(name = "name", length = 20, nullable = false)
	private String name;
	
	public ActivityId() {
		super();
	}
	
	public ActivityId(Integer id, String name) {
		this.setId(id);
		this.setName(name);
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
	
	public String toString() {

		return "userId: " + this.id + ", activityName: " + this.name;
	}
}
