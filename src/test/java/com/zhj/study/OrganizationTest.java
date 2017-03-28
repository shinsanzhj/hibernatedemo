package com.zhj.study;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.zhj.study.hibernate.demo.po.Organization;
import com.zhj.study.hibernate.demo.po.SchoolClass;
import com.zhj.study.hibernate.demo.po.Student;


public class OrganizationTest extends BaseTest {
	
	@Test
	public void testAddOrganization_Only() {
		Organization organization = new Organization();
//		organization.setType(0);
		organization.setName("舞影协会");
		session.save(organization);
	}
	
	
	@Test
	public void testOrganizationAddStudents() {
		Organization organization = (Organization) session.get(Organization.class, "402872815b106da5015b106da71a0001");
		
		Student student = (Student) session.get(Student.class, "8a2982375b0d7553015b0d7554340001");
		
//		organization.getStudents().add(student);//关系维护主体在student端，所以这边这样添加student是无效的
		student.getOrganizations().add(organization);
	}
	
	@Test
	public void testOrganizationRemoveStudent() {
		Student student = (Student) session.get(Student.class, "8a2982375b0d7553015b0d7554340001");
		printStudentOrganization(student);
		String sql = "DELETE TSO FROM T_STUDENT_ORGANIZATION TSO LEFT JOIN T_ORGANIZATION TOG ON TSO.ORGANIZATION_ID = TOG.ID LEFT JOIN T_STUDENT TS ON TSO.STUDENT_ID = TS.ID WHERE TOG.NAME = ? AND TS.NAME LIKE ?";
		session.createSQLQuery(sql).setParameter(0, "篮球协会").setParameter(1, "hdy%").executeUpdate();
		// 一定要刷新，否则session中的数据还是旧的
		session.refresh(student);
		printStudentOrganization(student);
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
