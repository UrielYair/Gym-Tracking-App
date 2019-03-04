package com.hit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activities")
public class Activity{

	// Table structure:
	// activities(id, user_name, exercise_name, workout_date, amount_of_sets, repeats, weight, duration, type)
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "activityId", length=10, nullable=false, unique=true)
	private Integer id;
	@Column(name = "user_name", length=20, nullable=false)
	private String userName;
	@Column(name = "exercise_name", length=20, nullable=false)
	private String exerciseName;
	@Column(name = "workout_date", length=20, nullable=false)
	private String workoutDate;
	@Column(name = "amount_of_sets", length=2, nullable=true)
	private Integer amountOfSets;
	@Column(name = "amount_of_repeatition", length=2, nullable=true)
	private Integer amountOfRepeatition;
	@Column(name = "weights", precision=2, nullable=true)
	private Float weight;
	@Column(name = "duration", precision=2, nullable=true)
	private Float duration;
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
	
	
	
	
	
	
	public Activity() {
		super();
	}
	public Activity(String userName, String activityName, String workoutDate, Integer amountOfSets,
			Integer amountOfRepeatition, Float weight, Float duration, String type) {
		super();
		
		//you should set some id for the use of hibernate  
		this.id = 0; // will be assigned automatically by the auto-increment.
		this.userName = userName;
		this.exerciseName = activityName;
		this.workoutDate = workoutDate;
		this.amountOfSets = amountOfSets;
		this.amountOfRepeatition = amountOfRepeatition;
		this.weight = weight;
		this.duration = duration;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", userName=" + userName + ", exerciseName=" + exerciseName + ", workoutDate="
				+ workoutDate + ", amountOfSets=" + amountOfSets + ", amountOfRepeatition=" + amountOfRepeatition
				+ ", weight=" + weight + ", duration=" + duration + ", type=" + type + "]";
	}

	public String getExerciseName() {
		return exerciseName;
	}
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	public String getWorkoutDate() {
		return workoutDate;
	}
	public void setWorkoutDate(String workoutDate) {
		this.workoutDate = workoutDate;
	}
	public Integer getAmountOfSets() {
		return amountOfSets;
	}
	public void setAmountOfSets(Integer amountOfSets) {
		this.amountOfSets = amountOfSets;
	}
	public Integer getAmountOfRepeatition() {
		return amountOfRepeatition;
	}
	public void setAmountOfRepeatition(Integer amountOfRepeatition) {
		this.amountOfRepeatition = amountOfRepeatition;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(Float duration) {
		this.duration = duration;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	


}
