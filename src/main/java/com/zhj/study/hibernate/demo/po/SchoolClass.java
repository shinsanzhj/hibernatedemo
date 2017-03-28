package com.zhj.study.hibernate.demo.po;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_SCHOOL_CLASS")
public class SchoolClass {

	private String id;
	private int grade;
	private int classNum;
	
	private Set<Student> students;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false)
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
	 * @return the grade
	 */
	@Column(name = "GRADE")
	public int getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}

	/**
	 * @return the classNum
	 */
	@Column(name = "CLASS_NUM")
	public int getClassNum() {
		return classNum;
	}

	/**
	 * @param classNum the classNum to set
	 */
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}

	/**
	 * @return the students
	 */
	@OneToMany(mappedBy = "schoolClass", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public Set<Student> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	@Override
	public String toString() {
		return this.grade + "年" + this.classNum + "班";
	}
}
