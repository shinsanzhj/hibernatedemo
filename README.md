hibernate大字段的操作
	Blob
		po的对应字段可以定义为Blob，或者byte[]
	Clob
		po的对应字段可以定义为Clob，或者String

一对一
	主键关联，可以延迟加载
	外键关联，不能延迟加载
	加载非关系维护方对象时，一对一的属性默认fetch是proxy，可以延迟加载；反而显示指定fetch为lazy，不能延迟加载
	
java代理
	Class.newInstance()：只能调用无参构造函数，而且必须是public
	Constructor.newInstance()：能调用所有构造函数，即使是private
	
Hibernate提供三种查询
	Criterial：面向对象的查询
	createQuery：面向HQL的查询
	createSQLQuery：面向SQL的查询
	
Hibernate对象的状态转化
	Trasient
		通过save关联到session中，由Transient转变成Persistent
	Persistent
		Persistent对象在tx.commit后，或者session关闭后，由Persistent转变成Detached
		Persistent对象通过session.delete方法由Persistent转变成Transient
	Detached
		Detached对象通过关联到新的session【session.update】，便可以从Detached转变成Persistent
		
Java中判断对象是否一致
	如果确定该对象不用放入集合（比如：Set中），那么只需重写equals方法即可
	如果该对象有可能会放入集合，那么一定要重写equals和hashcode方法，因为集合判断两个对象是否相等，会先调用hashcode方法，如果相等，再调用equals方法
	所以综上所述，如果要确定两个对象是否相等，一定要同时重写equals和hashCode方法，并使其语义一致。防止出现集合中两个对象hashcode不一样，却出现equals为true的情况。

Hibernate根据ID查找会先通过一级缓存，然后再找二级缓存，最后才找数据库。
Query.list()不是通过ID查找的，不会在缓存中找
Query.iterator()是先找出id列表，然后再循环load(id)，所以会先在缓存中查找
	
	
扩展
------------------------
redis事务中的WATCH命令和基于CAS的乐观锁
	http://blog.sina.com.cn/s/blog_ae8441630101cgy3.html
redis使用watch完成秒杀抢购功能
	http://blog.csdn.net/e421083458/article/details/49741451
【问底】徐汉彬：Web系统大规模并发——电商秒杀与抢购
	http://www.csdn.net/article/2014-11-28/2822858
3万个商品高并发下秒杀有什么方案实现超卖、少卖
	http://bbs.csdn.net/topics/391953209?page=1
mysql处理高并发，防止库存超卖
	http://www.cnblogs.com/wuyifu/p/4065664.html
大神们，如何解决“整点抢购”功能中的超卖问题及大并发下的用户等待问题？
	http://www.oschina.net/question/244978_2151439?p=1
一个幻象读的例子
	http://blog.csdn.net/bluishglc/article/details/6215547
	