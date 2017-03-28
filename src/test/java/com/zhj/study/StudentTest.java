package com.zhj.study;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.zhj.study.hibernate.demo.po.Organization;
import com.zhj.study.hibernate.demo.po.SchoolClass;
import com.zhj.study.hibernate.demo.po.Student;


public class StudentTest extends BaseTest {
	
	@Test
	public void testAddStudent_Only() {
		Student student = new Student();
		student.setName("ZHJ4");
		
//		List<SchoolClass> schoolClass = session.createQuery("SELECT schoolClass FROM SchoolClass schoolClass WHERE schoolClass.grade = ? and schoolClass.classNum = ?").setInteger(0, 1).setInteger(1, 2).list();
//		student.setSchoolClass(schoolClass.get(0));
		
		// 设置student中的schoolclass的cascade为all或者persist
		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setId("402872815b10408c015b10408e2b0002");
		schoolClass.setGrade(300);
		schoolClass.setClassNum(300);
		student.setSchoolClass(schoolClass);
		
		session.save(student);
	}
	
	@Test
	public void testDeleteStudent_Cascade_SchoolClass() {
		Student student = (Student) session.get(Student.class, "402872815b106217015b106219b90001");
		session.delete(student);
	}
	
	@Test
	public void testAddStudent_And_SchoolClass() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Student student = new Student();
		student.setName("hdy1");
		
		List<SchoolClass> schoolClasses = session.createQuery("SELECT schoolClass FROM SchoolClass schoolClass WHERE schoolClass.grade = ? and schoolClass.classNum = ?")
													.setInteger(0, 111).setInteger(1, 2).list();
		SchoolClass schoolClass = schoolClasses.get(0);
		student.setSchoolClass(schoolClass);
		
		session.save(student);
		
		session.beginTransaction().commit();
	}
	
	@Test
	public void testGetStudent_And_Modify_SchoolClass() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Student std = (Student) session.get(Student.class, "8a2982375afe4e4c015afe4e4d9a0001");
		std.setName("被修改1");
		std.getSchoolClass().setGrade(111);
//		session.save(student);
		
		session.beginTransaction().commit();
	}
	
	@Test
	public void testAddStudent_And_addSchoolClass_1() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Student student = new Student();
		student.setName("HDY");
		
		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setGrade(2);
		schoolClass.setClassNum(2);
		
		student.setSchoolClass(schoolClass);
		// 先保存schoolClass
		session.save(schoolClass);
		// 再保存student
		session.save(student);
		
		session.beginTransaction().commit();
	}
	
	@Test
	public void testAddStudent_And_addSchoolClass_2() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Student student = new Student();
		student.setName("GJM");
		
		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setGrade(3);
		schoolClass.setClassNum(2);
		
		student.setSchoolClass(schoolClass);
		session.save(schoolClass);
		session.save(student);
		
		session.beginTransaction().commit();
	}
	
	
	@Test
	public void testHqlQueryStudent() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
//		List<Student> list = session.createQuery("SELECT student FROM Student student WHERE student.name = ?").setString(0, "HDY").list();
//		
//		Student hdy = list.get(0);
//		System.out.println(hdy.getName());
//		System.out.println(hdy.getSchoolClass().getGrade());
		
		Student std = (Student) session.get(Student.class, "8a2982375afde376015afde3adc70002");
		System.out.println(std.getSchoolClass().getId());
		System.out.println(std.getSchoolClass().getClassNum());
		session.beginTransaction().commit();
	}
	
	@Test
	public void testGetStudent_And_School() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Student std = (Student) session.get(Student.class, "8a2982375afe4e21015afe4e22aa0001");
		System.out.println(std.getSchoolClass().getClassNum());
		
		System.out.println(std.getSchoolClass().getStudents().size());
		Iterator<Student> it = std.getSchoolClass().getStudents().iterator();
		while(it.hasNext()) {
			Student temp = it.next();
			System.out.println(temp == std);
		}
		Student tt = new Student();
		tt.setName("哈哈哈哈2");
//		tt.setSchoolClass(std.getSchoolClass());
		session.save(tt);
		
		std.getSchoolClass().getStudents().add(tt);
		session.beginTransaction().commit();
	}
	
	@Test
	public void testSqlQueryStudent() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Object> list = session.createSQLQuery("SELECT * FROM STUDY_STUDENT T LEFT JOIN STUDY_BOOK TT ON T.ID = TT.STUDENT_ID WHERE TT.ID IS NOT NULL AND T.AGE > 22").addEntity(Student.class).list();
		
		for(Object obj : list) {
			System.out.println(obj.getClass());
		}
		
		session.beginTransaction().commit();
	}
	
	// 多对一，测试加载类型和加载策略
	@Test
	public void testGetFetchType_And_FetchMode() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Student student = (Student) session.get(Student.class, "8a2982375b0d745d015b0d745f0b0001");
		System.out.println("学生信息：" + student.getName());
		System.out.println("所在班级信息：" + student.getSchoolClass());
		
		session.beginTransaction().commit();
	}
	
	// 多对一，测试加载类型和加载策略
	@Test
	public void testQueryFetchType_And_FetchMode() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		List<Student> students = session.createQuery("SELECT st FROM Student st WHERE st.name like ?").setParameter(0, "zhj%").list();
		System.out.println("得到学生数：" + students.size());
		
		for(Student student : students) {
			SchoolClass schoolClass = student.getSchoolClass();
			System.out.println(student.getName() + "在" + schoolClass);
		}
		
		session.beginTransaction().commit();
	}
	
	// 测试多对多关系维护
	@Test
	public void testStudentEnterOrganizations() {
		Organization organization1 = (Organization) session.get(Organization.class, "8a2982375b0e798d015b0e798e5a0001");
		Organization organization2 = (Organization) session.get(Organization.class, "8a2982375b0e79cc015b0e79ccf90001");
		Organization organization3 = (Organization) session.get(Organization.class, "8a2982375b0e79e5015b0e79e5d80001");
		
		Student student = (Student) session.get(Student.class, "8a2982375b0d7553015b0d7554340001");
		
		student.getOrganizations().add(organization1);
		student.getOrganizations().add(organization2);
		student.getOrganizations().add(organization3);
	}
	
	@Test
	public void testStudentQuitOrganizations() {
		Student student = (Student) session.get(Student.class, "8a2982375b0d7553015b0d7554340001");
		// 移除一个：发送N句SQL
//		student.getOrganizations().remove((Organization) session.get(Organization.class, "8a2982375b0e798d015b0e798e5a0001"));
		
		// 移除部分：发送N句SQL
		Organization organization1 = (Organization) session.get(Organization.class, "8a2982375b0e798d015b0e798e5a0001");
		Organization organization2 = (Organization) session.get(Organization.class, "8a2982375b0e79cc015b0e79ccf90001");
		List<Organization> removedList = new ArrayList<Organization>();
		removedList.add(organization1);
		removedList.add(organization2);
		student.getOrganizations().removeAll(removedList);
		
		// 移除全部：只会发送一句SQL
//		student.getOrganizations().clear();
	}
	
	// 学生转组织的情况
	@Test
	public void testStudentQuitAndEnterOrganization() {
		Student student = (Student) session.get(Student.class, "8a2982375b0d7553015b0d7554340001");
		System.out.println("开始状态...");
		printStudentOrganization(student);
		// 移除部分
		Organization organization1 = (Organization) session.get(Organization.class, "8a2982375b0e798d015b0e798e5a0001");
		Organization organization2 = (Organization) session.get(Organization.class, "8a2982375b0e79cc015b0e79ccf90001");
		List<Organization> removedList = new ArrayList<Organization>();
		removedList.add(organization1);
		removedList.add(organization2);
		student.getOrganizations().removeAll(removedList);
		
		// 添加部分
		Organization organization3 = (Organization) session.get(Organization.class, "8a2982375b0e7a56015b0e7a57330001");
		Organization organization4 = (Organization) session.get(Organization.class, "8a2982375b0e7aa8015b0e7aa9810001");
		List<Organization> addedList = new ArrayList<Organization>();
		addedList.add(organization3);
		addedList.add(organization4);
		student.getOrganizations().addAll(addedList);
		System.out.println("结束状态...");
		printStudentOrganization(student);
	}
	
	@Test
	public void testStudent_add_Organization_cascade() {
		Student student = (Student) session.get(Student.class, "402872815b10485d015b104869870001");
		
//		Organization organization = new Organization();
//		organization.setType(1);
//		organization.setName("自律会");
//		student.getOrganizations().add(organization);
		
		session.delete(student);
	}
	
	// 测试一对一的对象加载
	@Test
	public void testGetExtraInfo() {
		Student student = (Student) session.get(Student.class, "402872815b135bef015b135bf1350001");
		System.out.println("学生:" + student.getName());
		System.out.println(student.getStudentExtraInfo().getSignature());
	}
	
	// 打印学生参加的组织情况
	private void printStudentOrganization(Student student) {
		Set<Organization> organizations = student.getOrganizations();
		StringBuffer sb = new StringBuffer("学生：").append(student.getName()).append("参加的组织：【");
		for(Organization organization : organizations) {
			sb.append(organization.getName()).append("，");
		}
		sb.deleteCharAt(sb.length() - 1).append("】");
		System.out.println(sb.toString());
	}
	
}
