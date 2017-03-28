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
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_ORGANIZATION")
public class Organization {

	private String id;
	private int type;
	private String name;
	
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
	 * @return the type
	 */
	@Column(name = "TYPE")
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
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
	 * @return the students
	 */
	@ManyToMany(mappedBy = "organizations", fetch = FetchType.LAZY)
	@JoinTable(name = "T_STUDENT_ORGANIZATION",
			joinColumns = {@JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ID")},
			inverseJoinColumns = {@JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")})
	public Set<Student> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
