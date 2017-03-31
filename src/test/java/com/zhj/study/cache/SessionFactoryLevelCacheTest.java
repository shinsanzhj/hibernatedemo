package com.zhj.study.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Test;

import com.zhj.study.BaseTest;
import com.zhj.study.hibernate.demo.po.Course;

/**
 * 测试Hibernate二级缓存
 * @author zhj
 *
 */
public class SessionFactoryLevelCacheTest extends BaseTest {

	@Test
	public void testCacheLevel2() {
		Course lisan = (Course) session.get(Course.class, "402872815b137ca9015b137cab360001");
		System.out.println(lisan.toString() + lisan.getName());
		
		session.close();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Course lisan2 = (Course) session.get(Course.class, "402872815b137ca9015b137cab360001");
		System.out.println(lisan2.toString() + lisan2.getName());
		
		Course lisan3 = (Course) session.get(Course.class, "402872815b137ca9015b137cab360001");
		System.out.println(lisan3.toString() + lisan3.getName());
		
		session.close();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Course lisan4 = (Course) session.get(Course.class, "402872815b137ca9015b137cab360001");
		System.out.println(lisan4.toString() + lisan4.getName());
		
		Course cacheObj = new Course();
		cacheObj.setName("存入缓存");
		System.out.println("存入缓存前：" + cacheObj);
		Element cacheElement = new Element("myCourse", cacheObj);
		CacheManager.create().getCache("com.zhj.study.hibernate.demo.po.Course").put(cacheElement);
		
		Course fromCache = (Course) CacheManager.create().getCache("com.zhj.study.hibernate.demo.po.Course").get("myCourse").getObjectValue();
		System.out.println("存入缓存后：" + fromCache);
		
		System.out.println("Hibernate存入缓存的不是Course对象，而是一个CacheEntry，结果如下");
		Object key = CacheManager.create().getCache("com.zhj.study.hibernate.demo.po.Course").getKeys().get(1);
		Object cacheObjEle = CacheManager.create().getCache("com.zhj.study.hibernate.demo.po.Course").get(key).getObjectValue();
		System.out.println(cacheObjEle);
	}
	
	@Test
	public void testReadWriteCache() {
		Course lisan = (Course) session.get(Course.class, "402872815b137ca9015b137cab360001");
		
//		printCacheObject();
		System.out.println(lisan.getName() + ":" + lisan.getLimitCount());
		
		lisan.setLimitCount(11);
		session.getTransaction().commit();
		
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
//		printCacheObject();
		
		Course lisan2 = (Course) session.get(Course.class, "402872815b137ca9015b137cab360001");
		System.out.println(lisan2.getName() + ":" + lisan2.getLimitCount());
		
//		printCacheObject();
	}
	
	private static void printCacheObject() {
		Object key = CacheManager.create().getCache("com.zhj.study.hibernate.demo.po.Course").getKeys().get(0);
		Object cacheObjEle = CacheManager.create().getCache("com.zhj.study.hibernate.demo.po.Course").get(key).getObjectValue();
		System.out.println("Hibernate存入缓存的CacheEntry:" + cacheObjEle);
	}
}
