Hibernate二级缓存
	
1.	
	每次从缓存取出来的都是一个新对象，只不过对象属性一样而已，因为Hibernate并不是把整个对象存放到EhCache缓存中。
	实际存入缓存的内容如下：
		CacheEntry(com.zhj.study.hibernate.demo.po.Course)[5,离散数学,402872815b137ca9015b137cab360001]
	如果存入缓存的内容是个对象，应该如下：
		com.zhj.study.hibernate.demo.po.Course@3fb6772a
	所以导致每次从缓存取，都会构造一个新对象给session的一级缓存中，如果一级缓存中有该对象了，不会再从二级缓存取。
	参考：http://www.cnblogs.com/WJ-163/p/5845446.html

2.
	在NONSTRICT_READ_WRITE策略下，缓存的内容是一个CacheEntry，如果对应的对象被修改，会使该缓存被移除，待下次从数据库获取后再次存入二级缓存
	在READ_WRITE策略下，缓存的内容是一个ReadWriteCacheEntry，如果对应的对象被修改，会立即反映到缓存中，而不是移除该缓存