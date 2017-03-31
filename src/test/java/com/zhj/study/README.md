Hibernate二级缓存
	
1.	jdbc日期类型问题
	注意：jdbc编程，如果要设置时间字段，只能设置java.sql.Date或者java.sql.TimeStamp或者java.sql.Time类型的时间对象，这几个类都是
		java.util.Date的子类，例子如下。
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//			pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));//数据库该字段的值：2017-04-01 00:00:00
			pstmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));//数据库该字段的值：2017-04-01 00:13:26
			
		从数据库中的时间字段转成java中的时间类型，转成的对象肯定也是java.sql.*中的时间类型，po中的Date字段是一个引用，该引用指向的对象
			其实是java.sql.*下面的时间对象。
		
		hibernate中对po的时间直接设置java.util.Date类型的时间字段；
		spring中的jdbcTemplate也可以设置java.util.Date类型的时间字段；
			以上两种情况都是内部有进行转换，最终调用jdbc设置时间字段时，用的肯定都是java.sql.*中的时间类型
			
		参考：
			http://blog.sina.com.cn/s/blog_880b5d840101qwdv.html
			http://bbs.csdn.net/topics/390836342