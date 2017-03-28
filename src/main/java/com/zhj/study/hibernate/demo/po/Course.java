package com.zhj.study.hibernate.demo.po;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_COURSE")
public class Course {

	private String id;
	private String name;
	private int limitCount;
	
	private Set<StudentCourseInfo> studentCourseInfos;

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
	 * @return the name
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the limitCount
	 */
	@Column(name = "LIMIT_COUNT")
	public int getLimitCount() {
		return limitCount;
	}

	/**
	 * @param limitCount the limitCount to set
	 */
	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}

	/**
	 * @return the studentCourseInfos
	 */
	@OneToMany(mappedBy = "course")
	@Fetch(FetchMode.SUBSELECT)
	public Set<StudentCourseInfo> getStudentCourseInfos() {
		return studentCourseInfos;
	}

	/**
	 * @param studentCourseInfos the studentCourseInfos to set
	 */
	public void setStudentCourseInfos(Set<StudentCourseInfo> studentCourseInfos) {
		this.studentCourseInfos = studentCourseInfos;
	}
}
