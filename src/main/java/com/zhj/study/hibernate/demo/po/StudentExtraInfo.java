package com.zhj.study.hibernate.demo.po;

import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_STUDENT_EXTRAINFO")
public class StudentExtraInfo {

	private String id;
	private String signature;
	private String introduction;
	private byte[] headImg;
	
	private Clob introductionText;
	private Blob headPhoto;
	
	private Student student;

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
	 * @return the signature
	 */
	@Column(name = "SIGNATURE")
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the introduction
	 */
	@Lob
	@Column(name = "INTRODUCTION")
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * @return the headImg
	 */
	@Lob
	@Column(name = "HEAD_IMG")
	public byte[] getHeadImg() {
		return headImg;
	}

	/**
	 * @param headImg the headImg to set
	 */
	public void setHeadImg(byte[] headImg) {
		this.headImg = headImg;
	}

	/**
	 * @return the student
	 */
	@ForeignKey(name = "null")
	@OneToOne
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

	@Lob
	@Column(name = "INTRODUCTION_TEXT")
	public Clob getIntroductionText() {
		return introductionText;
	}

	public void setIntroductionText(Clob introductionText) {
		this.introductionText = introductionText;
	}

	@Column(name = "HEAD_PHOTO")
	public Blob getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(Blob headPhoto) {
		this.headPhoto = headPhoto;
	}
}
