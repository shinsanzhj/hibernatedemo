package com.zhj.study;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.zhj.study.hibernate.demo.po.SchoolClass;
import com.zhj.study.hibernate.demo.po.Student;


public class SchoolClassTest extends BaseTest {
	
	@Test
	public void testAddSchoolClass() {
		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setGrade(5);
		schoolClass.setClassNum(5);
		session.save(schoolClass);
	}
	
	@Test
	public void testGetSchoolClass_Student_Lazy() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		SchoolClass schoolClass = (SchoolClass) session.get(SchoolClass.class, "8a2982375afe4c15015afe4c169e0001");
		System.out.println(schoolClass.getGrade());
		Set<Student> students = schoolClass.getStudents();
		Iterator<Student> it = students.iterator();
		while(it.hasNext()) {
			Student student = it.next();
			System.out.println(student.getName());
		}
		
		
		session.beginTransaction().commit();
	}
	
	// 测试延迟加载，查询对象时1+n的问题 TODO
	@Test
	public void testQuerySchoolClass_Student_Lazy_N_1() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		// 1条SQL语句
		List<SchoolClass> schoolClasses = session.createQuery("SELECT sc FROM SchoolClass sc WHERE sc.grade = ?").setInteger(0, 111).list();
		System.out.println("得到班级个数：" + schoolClasses.size());
		
		// N次循环，每次1条，总共N条SQL语句
		for(SchoolClass schoolClass : schoolClasses) {
			// 发出SQL语句
			Set<Student> students = schoolClass.getStudents();
			System.out.println(schoolClass.getGrade() + "年" + schoolClass.getClassNum() + "班，人数：" + students.size() + "人");
			Iterator<Student> it = students.iterator();
			while(it.hasNext()) {
				Student student = it.next();
				System.out.println("\t" + student.getName());
				// 名字修改也能反映到数据库，因为student已经在session缓存中
				student.setName("清一色111");
			}
		}
		session.beginTransaction().commit();
	}
	
	@Test
	public void testQueryFetchType_And_FetchMode() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		List<SchoolClass> schoolClasses = session.createQuery("SELECT sc FROM SchoolClass sc WHERE sc.grade = ?").setInteger(0, 111).list();
		System.out.println("得到班级个数：" + schoolClasses.size());
		
		// N次循环，每次1条，总共N条SQL语句
		for(SchoolClass schoolClass : schoolClasses) {
			// 发出SQL语句
			Set<Student> students = schoolClass.getStudents();
			System.out.println(schoolClass.getGrade() + "年" + schoolClass.getClassNum() + "班，人数：" + students.size() + "人");
			Iterator<Student> it = students.iterator();
			while(it.hasNext()) {
				Student student = it.next();
				System.out.println("\t" + student.getName());
			}
		}
		
		session.beginTransaction().commit();
	}
	
	@Test
	public void testGetFetchType_And_FetchMode() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		SchoolClass schoolClass = (SchoolClass) session.get(SchoolClass.class, "8a2982375afa627c015afa627d020001");
		System.out.println("班级信息：" + schoolClass.getGrade() + "年" + schoolClass.getClassNum() + "班");
		
		Set<Student> students = schoolClass.getStudents();
		System.out.println("人数：" + students.size() + "人");
		Iterator<Student> it = students.iterator();
		while(it.hasNext()) {
			Student student = it.next();
			System.out.println("\t" + student.getName());
		}
		
		session.beginTransaction().commit();
	}
	
	@Test
	public void testDeleteStudentSet_Some() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		SchoolClass schoolClass = (SchoolClass) session.get(SchoolClass.class, "8a2982375afe4c15015afe4c169e0001");
		System.out.println("年级" + schoolClass.getGrade());
		Set<Student> students = schoolClass.getStudents();
		Iterator<Student> it = students.iterator();
		while(it.hasNext()) {
			Student student = it.next();
			if(student.getName().equals("哈哈哈哈2")) {
				session.delete(student);
			}
		}
		
		session.beginTransaction().commit();
	}
	
	@Test
	public void testDeleteStudentSet_All() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		session.createQuery("DELETE Student AS st WHERE st.schoolClass.id = ?").setString(0, "8a2982375afe4c15015afe4c169e0001").executeUpdate();
		SchoolClass schoolClass = (SchoolClass) session.get(SchoolClass.class, "8a2982375afe4c15015afe4c169e0001");
		System.out.println("年级" + schoolClass.getGrade());
		Set<Student> students = schoolClass.getStudents();
		System.out.println("学生个数：" + students.size());
		
		Student tt = new Student();
		tt.setName("哈哈哈哈4");
		tt.setSchoolClass(schoolClass);
		session.save(tt);
		
		session.beginTransaction().commit();
	}
	
	// one-to-many情况下：
	// 1.找出对应班级中的所有学生
	// 2.根据条件删除部分学生
	// 3.添加部分新的学生
	@Test
	public void testDeleteStudentSet_Part() {
		
	}
	
}
