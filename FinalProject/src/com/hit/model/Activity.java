package com.hit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activities")
public class Activity{

	// Table structure:
	// activities(id, user_name, exercise_name, workout_date, amount_of_sets, repeats, weight, duration, type)
	
	@Id
	@Column(name = "userId", length=10, nullable=false, unique=true)
	private int id;
	@Column(name = "user_name", length=20, nullable=false)
	private String userName;
	@Column(name = "exercise_name", length=20, nullable=false)
	private String exerciseName;
	@Column(name = "workout_date", length=20, nullable=false)
	private Date workoutDate;
	@Column(name = "amount_of_sets", length=2, nullable=true)
	private int amountOfSets;
	@Column(name = "amount_of_repeatition", length=2, nullable=true)
	private int amountOfRepeatition;
	@Column(name = "weights", precision=2, nullable=true)
	private float weight;
	@Column(name = "duration", precision=2, nullable=true)
	private float duration;
	@Column(name = "exercise_type", length=20, nullable=false)
	private String type; // TODO: consider changing to enum.
	
	/*
	 
	 before: 
		@Id
		@Column(name = "userId", length=10, nullable=false, unique=true)
		private ActivityId id;
	
		@Column(name = "numberOfSets", length = 5, nullable = false)
		private Integer set;
	
		@Column(name = "numberOfRepeat", length = 5, nullable = false)
		private Integer repeat;
	*/
	
	
	
	@Override
	public String toString() {
		return "Activity [id=" + id + ", userName=" + userName + ", activityName=" + exerciseName + ", workoutDate="
				+ workoutDate + ", amountOfSets=" + amountOfSets + ", amountOfRepeatition=" + amountOfRepeatition
				+ ", weight=" + weight + ", duration=" + duration + ", type=" + type + "]";
	}
	
	public Activity() {
		super();
	}
	public Activity(int id, String userName, String activityName, Date workoutDate, int amountOfSets,
			int amountOfRepeatition, float weight, float duration, String type) {
		super();
		this.id = id;
		this.userName = userName;
		this.exerciseName = activityName;
		this.workoutDate = workoutDate;
		this.amountOfSets = amountOfSets;
		this.amountOfRepeatition = amountOfRepeatition;
		this.weight = weight;
		this.duration = duration;
		this.type = type;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getActivityName() {
		return exerciseName;
	}
	public void setActivityName(String activityName) {
		this.exerciseName = activityName;
	}
	public Date getWorkoutDate() {
		return workoutDate;
	}
	public void setWorkoutDate(Date workoutDate) {
		this.workoutDate = workoutDate;
	}
	public int getAmountOfSets() {
		return amountOfSets;
	}
	public void setAmountOfSets(int amountOfSets) {
		this.amountOfSets = amountOfSets;
	}
	public int getAmountOfRepeatition() {
		return amountOfRepeatition;
	}
	public void setAmountOfRepeatition(int amountOfRepeatition) {
		this.amountOfRepeatition = amountOfRepeatition;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	


}
