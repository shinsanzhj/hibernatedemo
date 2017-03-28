package com.zhj.study;

import java.util.Date;

import org.junit.Test;

import com.zhj.study.hibernate.demo.po.Course;
import com.zhj.study.hibernate.demo.po.Student;
import com.zhj.study.hibernate.demo.po.StudentCourseInfo;


public class StudentCourseInfoTest extends BaseTest {
	
	// 学生选课：方式1
	@Test
	public void testStudentChooseCourse_Style_1() {
		Student student = (Student) session.get(Student.class, "402872815b135bef015b135bf1350001");
		Course course = (Course) session.get(Course.class, "402872815b137ca9015b137cab360001");
		
		StudentCourseInfo info = new StudentCourseInfo();
		info.setStudent(student);
		info.setCourse(course);
		info.setChooseTime(new Date());
		
		session.save(info);
		session.flush();
		
		if(course.getStudentCourseInfos().size() + 1 > course.getLimitCount()) {
			throw new RuntimeException("该课程已经超出人数限制，请选择其他课程");
		}
		
		System.out.println(course.getName() + "限制人数：" + course.getLimitCount());
		System.out.println(course.getName() + "当前人数：" + course.getStudentCourseInfos().size());
	}
	
	// 学生选课：方式2会出现超出人数的问题
	@Test
	public void testStudentChooseCourse_Style_2() {
		Student student = (Student) session.get(Student.class, "402872815b135bef015b135bf1350001");
		Course course = (Course) session.get(Course.class, "402872815b137ca9015b137cab360001");
		
		if(course.getStudentCourseInfos().size() + 1 > course.getLimitCount()) {
			throw new RuntimeException("该课程已经超出人数限制，请选择其他课程");
		}
		
		StudentCourseInfo info = new StudentCourseInfo();
		info.setStudent(student);
		info.setCourse(course);
		info.setChooseTime(new Date());
		
		session.save(info);
	}
}
