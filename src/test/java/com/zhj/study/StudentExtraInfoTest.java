package com.zhj.study;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.junit.Test;

import com.zhj.study.hibernate.demo.po.Student;
import com.zhj.study.hibernate.demo.po.StudentExtraInfo;


public class StudentExtraInfoTest extends BaseTest {
	
	@Test
	public void testAddExtraInfo_Only_MetaData() {
		StudentExtraInfo extraInfo = new StudentExtraInfo();
		extraInfo.setSignature("这个人很懒，什么都没有留下");
		
		session.save(extraInfo);
	}
	
	@Test
	public void testAddExtraInfo_Only_And_Introduction() {
		StudentExtraInfo extraInfo = new StudentExtraInfo();
		extraInfo.setSignature("这个人很懒，什么都没有留下");
		
		extraInfo.setIntroduction("这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下");
		
		session.save(extraInfo);
	}
	
	@Test
	public void testAddExtraInfo_Only_And_Introduction_And_HeadImg() {
		StudentExtraInfo extraInfo = new StudentExtraInfo();
		extraInfo.setSignature("这个人很懒，什么都没有留下");
		extraInfo.setIntroduction("这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下");
		
		try {
			FileInputStream fis = new FileInputStream(new File("D:/article_img1.jpg"));
			// 以byte数组的形式，缺点：内容大的时候，占用大量内存
			byte[] buf = new byte[fis.available()];
			fis.read(buf);
			extraInfo.setHeadImg(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		session.save(extraInfo);
	}
	
	@Test
	public void testAddExtraInfo_Only_And_IntroductionText() {
		StudentExtraInfo extraInfo = new StudentExtraInfo();
		extraInfo.setSignature("这个人很懒，什么都没有留下");
		
		Clob introductionText = Hibernate.createClob("这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下");
		extraInfo.setIntroductionText(introductionText);
		
		session.save(extraInfo);
	}
	
	@Test
	public void testAddExtraInfo_Only_And_IntroductionText_And_HeadPhoto() {
		StudentExtraInfo extraInfo = new StudentExtraInfo();
		extraInfo.setSignature("这个人很懒，什么都没有留下");
		
		Clob introductionText = Hibernate.createClob("这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下");
		extraInfo.setIntroductionText(introductionText);
		
		try {
			Blob headPhoto = Hibernate.createBlob(new FileInputStream("D:/article_img1.jpg"));
			extraInfo.setHeadPhoto(headPhoto);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		session.save(extraInfo);
	}
	
	@Test
	public void testAddExtraInfo_AllBasicInfo() {
		StudentExtraInfo extraInfo = new StudentExtraInfo();
		extraInfo.setSignature("aaaaaaaaaaaaaa");
		
		Clob introductionText = Hibernate.createClob("这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下这个人很懒，什么都没有留下");
		extraInfo.setIntroductionText(introductionText);
		
		try {
			Blob headPhoto = Hibernate.createBlob(new FileInputStream("D:/article_img1.jpg"));
			extraInfo.setHeadPhoto(headPhoto);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		extraInfo.setIntroduction("query(sql).list()时，只有lazy和eager的区别，忽略optional配置，忽略fetchMode配置，加载子对象时都是用类似select策略\nget时，只有fetchMode为join[此时lazy无效]，或者只配置了eager时，会通过join的方式加载子对象（optional=true时用left outer join；option=false时用inner join）");
		
		try {
			FileInputStream fis = new FileInputStream(new File("D:/article_img1.jpg"));
			// 以byte数组的形式，缺点：内容大的时候，占用大量内存
			byte[] buf = new byte[fis.available()];
			fis.read(buf);
			extraInfo.setHeadImg(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		session.save(extraInfo);
	}
	
	@Test
	public void testGetExtraInfo() {
		StudentExtraInfo extraInfo = (StudentExtraInfo) session.get(StudentExtraInfo.class, "402872815b130c2b015b130c2d640001");
		System.out.println(extraInfo.getSignature());
		try {
			// String类型【数据库对应Clob类型】
			System.out.println(extraInfo.getIntroduction());
			// byte[]类型【数据库对应Blob类型】
			FileOutputStream bfos = new FileOutputStream(new File("D:/bytes.jpg"));
			bfos.write(extraInfo.getHeadImg());
			
			// Clob类型
			Reader reader = extraInfo.getIntroductionText().getCharacterStream();
			int i = 0;
			char[] buf = new char[100];
			StringBuffer sb = new StringBuffer();
			while((i = reader.read(buf)) != -1) {
				sb.append(buf, 0, i);
			}
			System.out.println(sb.toString());
			
			// Blob类型
			InputStream is = extraInfo.getHeadPhoto().getBinaryStream();
			FileOutputStream fos = new FileOutputStream(new File("D:/headPhoto.jpg"));
			byte[] photoBuf = new byte[100];
			while((i = is.read(photoBuf)) != -1) {
				fos.write(photoBuf, 0, i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		session.save(extraInfo);
	}
	
	@Test
	public void testOneToOne_Student_Add_ExtraInfo() {
		StudentExtraInfo extraInfo = new StudentExtraInfo();
		extraInfo.setSignature("这个人很懒，什么都没有留下");
		
		Student student = new Student();
		student.setName("雷迪克");
		session.save(student);
		
		extraInfo.setStudent(student);
		session.save(extraInfo);
	}
	
}
