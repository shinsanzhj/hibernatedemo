package com.zhj.study.hibernate.demo.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_STUDENT_COURSE")
public class StudentCourseInfo {

	private String id;
	private Date chooseTime;
	private double score;
	
	private Student student;
	private Course course;
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the chooseTime
	 */
	@Column(name = "CHOOSE_TIME")
	public Date getChooseTime() {
		return chooseTime;
	}
	/**
	 * @param chooseTime the chooseTime to set
	 */
	public void setChooseTime(Date chooseTime) {
		this.chooseTime = chooseTime;
	}
	/**
	 * @return the score
	 */
	@Column(name = "SCORE")
	public double getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}
	/**
	 * @return the student
	 */
	@ForeignKey(name = "null")
	@ManyToOne
	@JoinColumn(name = "STUDENT_ID")
	public Student getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	/**
	 * @return the course
	 */
	@ForeignKey(name = "null")
	@ManyToOne
	@JoinColumn(name = "COURSE_ID")
	public Course getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
}
