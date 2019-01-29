package com.hit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activities")
public class Activity{

	@Id
	@Column(name = "userId", length=10, nullable=false, unique=true)
	private ActivityId id;

	@Column(name = "numberOfSets", length = 5, nullable = false)
	private Integer set;

	@Column(name = "numberOfRepeat", length = 5, nullable = false)
	private Integer repeat;

	public Activity() {
		super();
	}

	public Activity(Integer id, String name, Integer set, Integer repeat) {
		this.id = new ActivityId(id, name);
		this.setSet(set);
		this.setRepeat(repeat);
	}

	public ActivityId getId() {
		return id;
	}
	
	public void setId(ActivityId id) {
		this.id = id;
	}

	public Integer getSet() {
		return set;
	}

	public void setSet(Integer set) {
		this.set = set;
	}

	public Integer getRepeat() {
		return repeat;
	}

	public void setRepeat(Integer repeat) {
		this.repeat = repeat;
	}

	public String toString() {

		return this.id.toString() + ", numberOfSets: " + this.set + ", numberOfRepeat: " + this.repeat;
	}
}
