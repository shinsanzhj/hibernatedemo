package com.zhj.study.hibernate.demo.po;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_STUDENT")
public class Student {

	private String id;
	// TODO 按规则生成：入学号+班级号+座号
//	private int code;
	private String name;
	
	private StudentExtraInfo studentExtraInfo;
	private SchoolClass schoolClass;
	private Set<Organization> organizations;
	private Set<StudentCourseInfo> studentCourseInfos;
	
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
//	/**
//	 * @return the code
//	 */
//	public int getCode() {
//		return code;
//	}
//	/**
//	 * @param code the code to set
//	 */
//	public void setCode(int code) {
//		this.code = code;
//	}
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
	 * @return the studentExtraInfo
	 */
	@OneToOne(mappedBy = "student")
	public StudentExtraInfo getStudentExtraInfo() {
		return studentExtraInfo;
	}
	/**
	 * @param studentExtraInfo the studentExtraInfo to set
	 */
	public void setStudentExtraInfo(StudentExtraInfo studentExtraInfo) {
		this.studentExtraInfo = studentExtraInfo;
	}
	/**
	 * @return the schoolClass
	 */
	@ForeignKey(name = "null")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHOOL_CLASS")
	public SchoolClass getSchoolClass() {
		return schoolClass;
	}
	/**
	 * @param schoolClass the schoolClass to set
	 */
	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}
	/**
	 * @return the organizations
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "T_STUDENT_ORGANIZATION",
			joinColumns = {@JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")},
			inverseJoinColumns = {@JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ID")})
	public Set<Organization> getOrganizations() {
		return organizations;
	}
	/**
	 * @param organizations the organizations to set
	 */
	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}
	/**
	 * @return the studentCourseInfos
	 */
	@OneToMany(mappedBy = "student")
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
