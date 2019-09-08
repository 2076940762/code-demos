-- sql的分类:
-- 	DDL:数据定义语言
-- 		操作对象:数据库和表
-- 		关键词:create alter drop
-- 	DML:数据操作语言
-- 		操作对象:记录
-- 	DQL:数据查询语言(非官方)
-- 	DCL:数据控制语言
-- 		操作对象:用户 事务 权限
-- 

-- 登录数据库: 
-- mysql -uroot  -p密码;


-- 1.DDL 数据定义语言

-- 显示所有数据库
SHOW DATABASES ;

-- 创建数据库
CREATE DATABASE mybase;

-- 删除数据库
DROP DATABASE mybase;

-- 打开数据库
USE mybase;

-- 创建表
CREATE TABLE usrs(id INT ,NAME VARCHAR(20));

-- 查看所有表
SHOW TABLES;

-- 查看表结构
DESC usrs

-- 查看建表语句
 SHOW CREATE TABLE usrs;

-- 修改表名:alter table 旧表名 rename to 新表名;
ALTER TABLE usrs RENAME TO users;

-- 添加字段
ALTER TABLE users ADD passwd VARCHAR(20);

USE  mybase;

DESC users;

-- 修改字段名
ALTER TABLE users CHANGE passwd pwd VARCHAR(20);

-- 修改字段描述
ALTER TABLE users MODIFY id VARCHAR(20);

-- 删除字段
ALTER TABLE users DROP pwd;

-- 删除表
CREATE TABLE usr0 LIKE users;
SHOW TABLES;
DROP TABLE user0

-- 2. DML:数据操作语言
-- DML:数据操作语言
-- 	操作对象:记录(行)
-- 	关键词:insert update delete
-- 	插入:
-- 		格式1:
-- 			insert into 表名 values(字段值1,字段值2...,字段值n);
-- 			注意:
-- 				默认插入全部字段,
-- 				必须保证values后面的内容的类型和顺序和表结构中的一致
-- 				若字段类型为数字,可以省略引号
-- 			例如:
-- 				insert into user values(1,'tom');
-- 				insert into user values('2','tom');
-- 				insert into user values('3');-- 错误的  
-- 		
-- 		格式2:
-- 			insert into 表名(字段名,字段名1...) values(字段值,字段值1...);
-- 			注意:
-- 				插入指定的字段
-- 				必须保证values后面的内容的类型和顺序和表名后面的字段的类型和顺序保持一致.
-- 			例如:
-- 				insert into user (username,id) values('jack',4);
-- 				insert into user (username) values('jack',5);-- 错误的
-- 			
-- 	修改:
-- 		格式:
-- 			update 表名 set 字段名=字段值,字段名1=字段值1... [where 条件];
-- 		例如:
-- 			update user set username='jerry' where username='jack';
-- 	删除:
-- 		格式:
-- 			delete from 表名 [where 条件];
-- 		例如:
-- 			delete from user where id = '2';
DESC users;

INSERT users VALUES( 1111,'sadfsa');

SELECT *FROM users;

ALTER TABLE users MODIFY id INT;

#当字段名为SQL关键字时要用反引号
INSERT users (`name`,id) VALUES('jack',123);


#修改
UPDATE users SET `name`='张三' WHERE id =1111;


#删除
-- insert users values( 1,'tom');
-- select *from users;
-- delete from users where id=1;
-- 
-- DQL:数据查询语言
-- 		关键词:select
-- 	格式:
-- 		select ... from 表名 where 条件 group by 分组字段 having 条件 order by 排序字段 ase|desc
-- 	
-- 	初始化环境:
-- 创建商品表
CREATE TABLE products(
	pid INT PRIMARY KEY AUTO_INCREMENT,
	pname VARCHAR(20),
	price DOUBLE,
	pnum INT,
	cno INT,
	pdate TIMESTAMP
);

INSERT INTO products VALUES (NULL,'泰国大榴莲',98,12,1,NULL);
INSERT INTO products VALUES (NULL,'新疆大枣',38,123,1,NULL);
INSERT INTO products VALUES (NULL,'新疆切糕',68,50,2,NULL);
INSERT INTO products VALUES (NULL,'十三香',10,200,3,NULL);
INSERT INTO products VALUES (NULL,'老干妈',20,180,3,NULL);
INSERT INTO products VALUES (NULL,'豌豆黄',20,120,2,NULL);

CREATE TABLE category (cid VARCHAR(20),c_name VARCHAR(50));

SHOW TABLES;

DESC products;

SELECT *FROM products;

SELECT price FROM products;
SELECT DISTINCT price FROM products;
SELECT price+10 AS pri FROM products;
 
-- where后的条件写法：
-- * > ,<,=,>=,<=,<>,!=
-- * like 使用占位符 _ 和 %  _代表一个字符 %代表任意个字符. 
-- * in在某个范围中获得值.
	* SELECT * FROM product WHERE pid IN (2,5,8);
-- * between 较小值 and 较大值
	SELECT * FROM products WHERE price BETWEEN 50 AND 70;
	
#聚合函数
#        对一列进行计算 返回值是一个,忽略null值
SELECT SUM(price) FROM products; 

SELECT MAX(price) FROM products;

SELECT MIN(price) FROM products;

#记录条数
SELECT COUNT(*) FROM products;

#平均价格，保留两位小数
SELECT ROUND(AVG(price),2) FROM products;


-- 分组：使用group by
-- 1.根据cno字段分组，分组后统计商品的个数.
	SELECT cno,COUNT(*) FROM products GROUP BY cno;
-- 2.根据cno分组，分组统计每组商品的总数量，并且总数量> 200;
	SELECT cno,SUM(pnum) FROM products  GROUP BY cno;
	SELECT cno,SUM(pnum) FROM products  GROUP BY cno HAVING SUM(pnum)>200;
-- 注意:
--    where和having区别:
-- 	1.where 是对分组前的数据进行过滤 ;having 是对分组后的数据进行过滤 
-- 	2.where 后面不能使用聚合函数,having可以



-- 数据类型:(了解)
-- java			mysql		
-- byte			tinyint			
-- short			smallint
-- int				int(★)
-- long			bigint
-- char/String		varchar(★)|char
-- 					varchar:可变长度 mysql的方言  varchar(20):  存放abc 只会占用三个
-- 					char:固定长度 char(20) 存放abc 占用20个
-- boolean			tinyint|int 代替
-- float|double	float|double
-- 					注意:
-- 						double(5,2):该小数长度为5个,小数占2个  最大值:999.99
-- 
-- 
-- java.sql.Date		date 日期
-- java.sql.Time		time 时间
-- java.sql.Timestamp	timestamp(★) 时间戳 若给定值为null,数据库会把当前的系统时间存放到数据库中
-- 					datetime(★) 日期+时间
-- 					
-- java.sql.Clob(长文本)	mysql的方言(text)
-- java.sql.Blob(二进制)	blob






-- 约束:
-- 	作用:
-- 		为了保证数据的有效性和完整性
-- 	mysql中常用的约束:主键约束(primary key)  唯一约束(unique) 非空约束(not null) 外键约束(foreign key)
-- 	主键约束:被修饰过的字段唯一非空
-- 		注意:一张表只能有一个主键,这个主键可以包含多个字段
-- 		方式1:建表的同时添加约束 格式: 字段名称 字段类型 primary key
-- 		方式2:建表的同时在约束区域添加约束 
-- 			所有的字段声明完成之后,就是约束区域了
-- 			格式: primary key(字段1,字段2)
-- 			
			CREATE TABLE pk01(
				id INT,
				username VARCHAR(20),
				PRIMARY KEY (id)
			);
			
			INSERT INTO pk01 VALUES(1,'tom');-- 成功
			INSERT INTO pk01 VALUES(1,'tom');-- 失败 Duplicate entry '1' for key 'PRIMARY'
			INSERT INTO pk01 VALUES(NULL,'tom');-- 失败  Column 'id' cannot be null
			
			CREATE TABLE pk01(
				id INT PRIMARY KEY,
				username VARCHAR(20),
				PRIMARY KEY (id)
			);-- 错误的 一张表只能有一个主键
-- 			
-- 		方式3:建表之后,通过修改表结构添加约束
			CREATE TABLE pk02(
				id INT,
				username VARCHAR(20)
			);
			
			ALTER TABLE pk02 ADD PRIMARY KEY(字段名1,字段名2..);
			ALTER TABLE pk02 ADD PRIMARY KEY(id,username);
			
			INSERT INTO pk02 VALUES(1,'tom');    -- 成功
			INSERT INTO pk02 VALUES(1,'tomcat'); -- 成功
			INSERT INTO pk02 VALUES(1,'tomcat'); -- 失败
-- 		
-- 	唯一约束:(了解)
-- 		被修饰过的字段唯一,对null不起作用
-- 		方式1:建表的同时添加约束 格式: 字段名称 字段类型 unique
			USE mybase;
			CREATE TABLE un(
				id INT UNIQUE,
				username VARCHAR(20) UNIQUE
			);
			
			DESC un;
			
			INSERT INTO un VALUE(NULL,NULL);
			
			SELECT *FROM un;
-- 			+------+----------+
-- 			| id   | username |
-- 			+------+----------+
-- 			| NULL | NULL     |
-- 			| NULL | NULL     |
-- 			| NULL | NULL     |
-- 			| NULL | NULL     |
-- 			+------+----------+

			
			DROP TABLE un;
			
			
			INSERT INTO un VALUE(10,'tom');-- 成功
			INSERT INTO un VALUE(10,'jack');-- 错误 Duplicate entry '10' for key 'id'
			INSERT INTO un VALUE(NULL,'jack');-- 成功
			INSERT INTO un VALUE(NULL,'rose');-- 成功
-- 			
-- 		方式2:建表的同时在约束区域添加约束 
-- 			所有的字段声明完成之后,就是约束区域了
-- 			unique(字段1,字段值2...)
-- 		方式3:建表之后,通过修改表结构添加约束
			ALTER TABLE 表名 ADD UNIQUE(字段1,字段2);-- 添加的联合唯一
			ALTER TABLE 表名 ADD UNIQUE(字段1);-- 给一个添加唯一
			ALTER TABLE 表名 ADD UNIQUE(字段2);-- 给另一个添加唯一
			
			////////////////
				CREATE TABLE un01(
					id INT,
					username VARCHAR(20)
				); 
				ALTER TABLE un01 ADD UNIQUE(id,username);
				INSERT INTO un01 VALUES(1,'tom');-- 成功
				INSERT INTO un01 VALUES(1,'jack');-- 成功
  	 					INSERT INTO un01 VALUES(1,'tom');-- 失败  Duplicate entry '1-tom' for key 'id'

-- 	非空约束(了解)
-- 		特点:被修饰过的字段非空
-- 		方式:
			CREATE TABLE nn(
				id INT NOT NULL,
				username VARCHAR(20) NOT NULL
			);
			
			INSERT INTO nn VALUES(NULL,'tom');--  错误的 Column 'id' cannot be null


-- truncate 清空表 ★
-- 	格式:
-- 		truncate 表名; 干掉表,重新创建一张空表
-- 	和delete from 区别:
-- 		delete属于DML语句  truncate属于DDL语句
-- 		delete逐条删除	truncate干掉表,重新创建一张空表
-- 		
-- auto_increment 自增
-- 	要求:
-- 		1.被修饰的字段类型支持自增. 一般int
-- 		2.被修饰的字段必须是一个key 一般是primary key

SELECT * FROM mybase.users ;



-- 一对多:
-- 	用户和订单	
-- 	-- 创建用户表
-- 	create  table user(
-- 		id int primary key auto_increment,
-- 		username varchar(20)
-- 	);
-- 	
-- 	-- 创建订单表
-- 	create  table orders(
-- 		id int primary key auto_increment,
-- 		totalprice double,
-- 		user_id int
-- 	);
-- 	
-- 	为了保证数据的有效性和完整性,添加约束(外键约束).
-- 		在多表的一方添加外键约束
-- 			格式:
-- 				alter table 多表名称 add foreign key(外键名称) references 一表名称(主键);
-- 			例如:
-- 				alter table orders add foreign key(user_id) references user(id);
-- 	添加了外键约束之后有如下特点:★
-- 		1.主表中不能删除从表中已引用的数据
-- 		2.从表中不能添加主表中不存在的数据
-- 	开发中处理一对多:★
-- 		在多表中添加一个外键,名称一般为主表的名称_id,字段类型一般和主表的主键的类型保持一致,
-- 		为了保证数据的有效性和完整性,在多表的外键上添加外键约束即可.

CREATE DATABASE myStore;
USE mystore;

CREATE TABLE mystore.usrs(uid INT PRIMARY KEY AUTO_INCREMENT,uname VARCHAR(20));
INSERT INTO mystore.usrs VALUES(NULL,"张三");
INSERT INTO mystore.usrs VALUES(NULL,"李四");
INSERT INTO mystore.usrs VALUES(NULL,"王麻子");
SELECT * FROM mystore.usrs;

SHOW CREATE TABLE usrs;


CREATE TABLE orders(
oid INT PRIMARY KEY AUTO_INCREMENT ,
totalprice DOUBLE(20,2),
uid INT
)

INSERT INTO orders VALUES(NULL,11.23,1);
INSERT INTO orders VALUES(NULL,110.38,1);

INSERT INTO orders VALUES(NULL,43.89,2);
INSERT INTO orders VALUES(NULL,34.34,2);

INSERT INTO orders VALUES(NULL,23345.89,2);
INSERT INTO orders VALUES(NULL,854552.32,2);

SELECT * FROM orders;


ALTER TABLE orders ADD FOREIGN KEY orders(uid) REFERENCES usrs(uid);

SHOW CREATE TABLE usrs;
CREATE TABLE `usrs` (
  `uid` INT(11) NOT NULL AUTO_INCREMENT,
  `uname` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

CREATE TABLE `usrs` (
  `uid` INT(11) NOT NULL AUTO_INCREMENT,
  `uname` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

SHOW CREATE TABLE orders;
CREATE TABLE `orders` (
  `oid` INT(11) NOT NULL AUTO_INCREMENT,
  `totalprice` DOUBLE(20,2) DEFAULT NULL,
  `uid` INT(11) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8

CREATE TABLE `orders` (
  `oid` INT(11) NOT NULL AUTO_INCREMENT,
  `totalprice` DOUBLE(20,2) DEFAULT NULL,
  `uid` INT(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `orders` (`uid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `usrs` (`uid`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8

SELECT * FROM usrs;
DELETE FROM usrs WHERE uid =1;
#Cannot delete or update a parent row: a foreign key constraint fails (`mystore`.`orders`, CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `usrs` (`uid`))
INSERT INTO mystore.usrs VALUES(1,"张三");

SELECT * FROM orders;
INSERT INTO orders VALUES(NULL,78.54, 5)
#Cannot add or update a child row: a foreign key constraint fails (`mystore`.`orders`, CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `usrs` (`uid`))


-- 开发中处理多对多:★
-- 	引入一张中间表,存放两张表的主键,一般会将这两个字段设置为联合主键,这样就可以将多对多的关系拆分
-- 	成两个一对多了
-- 	为了保证数据的有效性和完整性
-- 		需要在中间表上添加两个外键约束即可.

CREATE TABLE products(
pid INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(120),
price DOUBLE(10,2)
);

-- 创建中间表
CREATE TABLE orderitem(
	oid INT,
	pid INT
);

ALTER TABLE orderitem ADD FOREIGN KEY (oid) REFERENCES orders(oid);
ALTER TABLE orderitem ADD FOREIGN KEY (pid) REFERENCES products(pid);

SHOW TABLES;

SHOW CREATE TABLE orderitem ;
CREATE TABLE `orderitem` (
  `oid` INT(11) DEFAULT NULL,
  `pid` INT(11) DEFAULT NULL,
  KEY `pid` (`pid`),
  KEY `oid` (`oid`),
  CONSTRAINT `orderitem_ibfk_3` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `products` (`pid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

ALTER TABLE orderitem DROP FOREIGN KEY orderitem_ibfk_3;

CREATE TABLE `orderitem` (
  `oid` INT(11) DEFAULT NULL,
  `pid` INT(11) DEFAULT NULL,
  KEY `pid` (`pid`),
  KEY `oid` (`oid`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `products` (`pid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8


-- 内连接:★
-- 	格式1:显式的内连接
-- 		select a.*,b.* from a [inner] join b on ab的连接条件
-- 	格式2:隐式的内连接
-- 		select a.*,b.* from a,b where ab的连接条件
-- 外连接:★
-- 	左外连接:★
-- 		select a.*,b.* from a left [outer] join b on 连接条件;
-- 		意思:
-- 			先展示join左边的(a)表的所有数据,根据条件关联查询 join右边的表(b),符合条件则展示出来,不符合以null值展示.
-- 	右外连接:
-- 		select a.*,b.* from b right [outer] join a on 连接条件;
-- 		意思:
-- 			先展示jion右边的表(a)表的所有数据,根据条件关联查询join左边的表(b),符合条件则展示出来,不符合以null值展示.
-- 子查询:★
-- 	一个查询依赖另一个查询.

SHOW TABLES;

DESC orders;
DESC usrs;

SELECT * FROM usrs;

#隐式内连接
SELECT * FROM usrs ,orders WHERE usrs.uid=orders.uid;

#显示内连接
SELECT * FROM usrs A JOIN orders B ON A.uid=B.uid;

#左外连接
SELECT * FROM usrs A LEFT JOIN  orders B ON A.uid=B.uid;
--    uid  uname         oid  totalprice     uid  
-- ------  ---------  ------  ----------  --------
--      1  张三              1       11.23         1
--      1  张三              2      110.38         1
--      2  李四              3       43.89         2
--      2  李四              4       34.34         2
--      2  李四              5    23345.89         2
--      2  李四              6   854552.32         2
--      3  王麻子        (NULL)      (NULL)    (NULL)

SHOW CREATE TABLE usrs;
-- 	CREATE TABLE `usrs` (
-- 	  `uid` int(11) NOT NULL AUTO_INCREMENT,
-- 	  `uname` varchar(20) DEFAULT NULL,
-- 	  PRIMARY KEY (`uid`)
-- 	) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

SHOW CREATE TABLE orders;
-- 	CREATE TABLE `orders` (
-- 	  `oid` int(11) NOT NULL AUTO_INCREMENT,
-- 	  `totalprice` double(20,2) DEFAULT NULL,
-- 	  `uid` int(11) DEFAULT NULL,
-- 	  PRIMARY KEY (`oid`),
-- 	  KEY `orders` (`uid`),
-- 	  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `usrs` (`uid`)
-- 	) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8

ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_1;
SHOW CREATE TABLE orders;
-- 	CREATE TABLE `orders` (
-- 	  `oid` int(11) NOT NULL AUTO_INCREMENT,
-- 	  `totalprice` double(20,2) DEFAULT NULL,
-- 	  `uid` int(11) DEFAULT NULL,
-- 	  PRIMARY KEY (`oid`),
-- 	  KEY `orders` (`uid`)
-- 	) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8
DESC orders;
INSERT INTO orders VALUES(NULL,234.89,123);

#右外连接
SELECT * FROM usrs A RIGHT JOIN orders  B ON A.uid=B.uid;
--    uid  uname      oid  totalprice     uid  
-- ------  ------  ------  ----------  --------
--      1  张三           1       11.23         1
--      1  张三           2      110.38         1
--      2  李四           3       43.89         2
--      2  李四           4       34.34         2
--      2  李四           5    23345.89         2
--      2  李四           6   854552.32         2
-- (NULL)  (NULL)       8      234.89       123
     
#子查询
SELECT * FROM usrs A WHERE A.uid IN ( SELECT B.uid FROM orders B );

DESC orders;
SELECT * FROM usrs A ,orders B WHERE A.uid=B.uid AND B.totalprice>100;

SELECT * FROM usrs A ,(SELECT * FROM orders WHERE totalprice >100) B WHERE A.uid=B.uid;
--    uid  uname      oid  totalprice     uid  
-- ------  ------  ------  ----------  --------
--      1  张三           2      110.38         1
--      2  李四           5    23345.89         2
--      2  李四           6   854552.32         2

-- 扩展:
-- 	以后开发中很少使用delete,数据无价,删除有物理和逻辑(常用),
-- 		逻辑删除一般会在表中添加一个字段(isdel:若值为1,代表删除了;若为0代表没有删除),
-- 		此时的删除操作变成了更新操作.


SHOW DATABASES;
USE mystore;
SHOW TABLES;

DESC orders;

INSERT INTO orders VALUES(NULL,12.34,02)

DELETE FROM orders WHERE oid=10;
SELECT * FROM orders;

USE mystore
SHOW TABLES
DESC orders

SELECT * FROM orders

SELECT COUNT(*) FROM orders;




CREATE DATABASE mydb;
USE mydb;
CREATE TABLE usrs(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(20),
	PASSWORD VARCHAR(20),
	email VARCHAR(20),
	NAME VARCHAR(20),
	sex VARCHAR(10),
	birthday DATE,
	hobby VARCHAR(50)
);

INSERT INTO usrs VALUES (1,'tom','123','tom@126.com','tom','1','1988-01-01',NULL);
SELECT* FROM usrs;

DELETE  FROM usrs;
DROP TABLE mydb.user;
DROP DATABASE mydb;


SELECT username,`password` FROM usrs WHERE username='tom' AND `password`='123';

INSERT  INTO `usrs`(`id`,`username`,`password`,`email`,`name`,`sex`,`birthday`,`hobby`) VALUES 
(1,'tom','123','tom@126.com','tom','1','1988-01-01',NULL);


INSERT  INTO `usrs`(`id`,`username`,`password`,`email`,`name`,`sex`,`birthday`,`hobby`) VALUES 	(NULL,NULL,'','123@qq.com','123', '男',** NOT SPECIFIED **,** NOT SPECIFIED **,); 




 INSERT  INTO `usrs`(id,`username`,`password`,`email`,`name`,`sex`,`birthday`,`hobby`) VALUES(11,'tom','aassa','asdf@qq.com','asdf', '男','2019-07-03','eat',); 

DESC usrs

FIELD     TYPE         NULL    KEY     DEFAULT  Extra           
--------  -----------  ------  ------  -------  ----------------
id        INT(11)      NO      PRI     (NULL)   AUTO_INCREMENT  
username  VARCHAR(20)  YES             (NULL)                   
PASSWORD  VARCHAR(20)  YES             (NULL)                   
email     VARCHAR(20)  YES             (NULL)                   
NAME      VARCHAR(20)  YES             (NULL)                   
sex       VARCHAR(10)  YES             (NULL)                   
birthday  DATE         YES             (NULL)                   
hobby     VARCHAR(50)  YES             (NULL)   


CREATE TABLE `usrs` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) DEFAULT NULL,
  `password` VARCHAR(20) DEFAULT NULL,
  `email` VARCHAR(20) DEFAULT NULL,
  `name` VARCHAR(20) DEFAULT NULL,
  `sex` VARCHAR(10) DEFAULT NULL,
  `birthday` VARCHAR(20) DEFAULT NULL,
  `hobby` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE usrs;

SELECT * FROM usrs;

DELETE FROM usrs;


CREATE DATABASE products;
USE products;
CREATE TABLE product(
	id INT PRIMARY KEY AUTO_INCREMENT,
	pname VARCHAR(20),
	price DOUBLE,
	pdesc VARCHAR(20)
);

INSERT INTO product VALUES (NULL,'电视机',3200,'液晶曲面大电视');
INSERT INTO product VALUES (NULL,'韭菜盒子',3,'味重请小心食用');
INSERT INTO product VALUES (NULL,'益达',10,'韭菜伴侣');
INSERT INTO product VALUES (NULL,'十三香',12,'守义牌');

SELECT * FROM  products.product;

SELECT username , `password` FROM usrs;



USE mydb;
CREATE TABLE accounts(
`name` VARCHAR(20) PRIMARY KEY,
 money DOUBLE
)

INSERT INTO accounts VALUES("aaa",100);
INSERT INTO accounts VALUES("bbb",1000);


SELECT * FROM accounts;
UPDATE accounts SET money=1000 WHERE `name`='aaa';
UPDATE accounts SET money=10000;



CREATE DATABASE webstore;
USE webstore;
CREATE TABLE `product` (
	`pid` VARCHAR (96),
	`pname` VARCHAR (150),
	`market_price` DOUBLE ,
	`shop_price` DOUBLE ,
	`pimage` VARCHAR (600),
	`pdate` DATE ,
	`pdesc` VARCHAR (765)
); 

INSERT INTO `product` VALUES('1','小米 4c 标准版','1399','1299','products/1/c_0001.jpg','2015-11-02','小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待');
INSERT INTO `product` VALUES('10','华为 Ascend Mate7','2699','2599','products/1/c_0010.jpg','2015-11-02','华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！');
INSERT INTO `product`  VALUES('11','vivo X5Pro','2399','2298','products/1/c_0014.jpg','2015-11-02','移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术');
INSERT INTO `product`  VALUES('12','努比亚（nubia）My 布拉格','1899','1799','products/1/c_0013.jpg','2015-11-02','努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！');
INSERT INTO `product`  VALUES('13','华为 麦芒4','2599','2499','products/1/c_0012.jpg','2015-11-02','华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖');
INSERT INTO `product`  VALUES('14','vivo X5M','1899','1799','products/1/c_0011.jpg','2015-11-02','vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV');
INSERT INTO `product`  VALUES('15','Apple iPhone 6 (A1586)','4399','4288','products/1/c_0015.jpg','2015-11-02','Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！');

SELECT * FROM product;



DELETE FROM product WHERE pname IS NULL;

ALTER TABLE product MODIFY pname VARCHAR(150) NOT NULL UNIQUE;

SELECT pname ,COUNT(*) AS num FROM product GROUP BY pname HAVING num >1;

SELECT pname  FROM product GROUP BY pname HAVING COUNT(*) >1;


SELECT  DISTINCT * FROM product;

SELECT pid FROM product WHERE pname IN(
SELECT pname  FROM product GROUP BY pname HAVING COUNT(*) >1
)

SELECT MIN(pid) FROM product WHERE pname IN(
SELECT pname  FROM product GROUP BY pname HAVING COUNT(*) >1
)

DELETE FROM product WHERE pname IN(
SELECT pname  FROM product GROUP BY pname HAVING COUNT(*) >1
)
AND pid NOT IN (
	SELECT MIN(pid) FROM product WHERE pname IN(
	SELECT pname  FROM product GROUP BY pname HAVING COUNT(*) >1
	)
)




DELETE FROM product WHERE pname IN(
SELECT pname  FROM product GROUP BY pname HAVING COUNT(*) >1
)
AND pid NOT IN (
'363ae780-885a-4d4c-b4dd-e13b5da59eb0'
)

DELETE FROM product WHERE pid IN (
'7d94c2f8-905a-4032-a27f-b65dacf69211',
'363ae780-885a-4d4c-b4dd-e13b5da59eb0'
)




SELECT * FROM product WHERE pid=15





DELETE FROM product WHERE pid IN ('840041ea-30b5-4173-8f0e-c5be634ceac9',
'15','14','11','12','10','13','1','2f01091f-4d89-473b-8a11-68698ecb2c81');


SELECT * FROM product WHERE 1=1  AND NAME LIKE '%华为%'   ; 
SELECT COUNT(*) FROM product;





CREATE TABLE `user` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`username` VARCHAR(20) DEFAULT NULL,
`password` VARCHAR(20) DEFAULT NULL,
`email` VARCHAR(20) DEFAULT NULL,
`name` VARCHAR(20) DEFAULT NULL,
`sex` VARCHAR(10) DEFAULT NULL,
`birthday` DATE DEFAULT NULL,
`hobby` VARCHAR(50) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
INSERT  INTO `user`(`id`,`username`,`password`,`email`,`name`,`sex`,`birthday`,`hobby`) VALUES 
(1,'bbb','123','123@163.com','张三','男','1992-01-02','篮球, 足球, 排球'),
(2,'ccc','123','ccc@itcast.cn','李四','女','1992-03-20','排球, 乒乓球'),
(3,'aaa','123','aaa@itcast.cn','王守义','男','1990-08-11','足球, 排球')
,(5,'tom','123','haha@qq.com','提莫','男',NULL,'篮球');


SELECT * FROM `user` WHERE username ='提莫' LIMIT 1;


DROP TABLE IF EXISTS `keywords`;

CREATE TABLE `keywords` (
  `kw` VARCHAR(30) DEFAULT NULL,
  UNIQUE KEY `kw` (`kw`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

SELECT * FROM keywords WHERE kw LIKE '%a%' ORDER BY kw;


  
CREATE TABLE Province(ProvinceID INT PRIMARY KEY ,NAME VARCHAR(50));
CREATE TABLE City(CityID INT PRIMARY KEY ,ProvinceID INT ,NAME VARCHAR(50));
INSERT INTO Province(ProvinceID,NAME)VALUES('1','北京');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('1','东城区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('2','西城区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('3','宣武区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('4','崇文区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('5','朝阳区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('6','海淀区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('7','丰台区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('8','石景山区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('9','门头沟区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('10','昌平区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('11','大兴区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('12','怀柔区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('13','密云县','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('14','平谷区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('15','顺义区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('16','通州区','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('17','延庆县','1');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('103','房山区','1');
INSERT INTO Province(ProvinceID,NAME)VALUES('2','上海');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('18','黄浦区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('19','南市区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('20','卢湾区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('21','徐汇区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('22','长宁区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('23','静安区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('24','普陀区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('25','金山区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('26','闸北区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('27','虹口区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('28','杨浦区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('29','宝山区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('30','闵行区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('31','嘉定区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('32','松江区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('33','浦东新区','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('34','青浦县','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('35','奉贤县','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('36','南汇县','2');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('37','崇明县','2');
INSERT INTO Province(ProvinceID,NAME)VALUES('3','天津');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('38','和平区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('39','河东区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('40','河西区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('41','河北区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('42','南开区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('43','红桥区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('44','塘沽区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('45','汉沽区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('105','大港区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('106','东丽区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('107','西青区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('108','津南区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('109','北辰区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('110','武清区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('111','宝坻区','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('112','蓟 县','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('113','宁河县','3');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('114','静海县','3');
INSERT INTO Province(ProvinceID,NAME)VALUES('4','重庆');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('46','永川市','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('47','黔江区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('48','涪陵区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('49','万洲区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('115','渝中区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('116','大渡口区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('117','江北区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('118','沙坪坝区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('119','九龙坡区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('120','南岸区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('121','北碚区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('122','万盛区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('123','双桥区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('124','渝北区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('125','巴南区','4');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('126','长寿区','4');
INSERT INTO Province(ProvinceID,NAME)VALUES('5','黑龙江');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('50','哈尔滨','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('51','齐齐哈尔','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('52','牡丹江','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('127','鹤岗','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('128','双鸭山','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('129','鸡西','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('130','大庆','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('131','伊春','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('132','佳木斯','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('133','七台河','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('134','黑河','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('135','绥化','5');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('136','大兴安岭地区','5');
INSERT INTO Province(ProvinceID,NAME)VALUES('6','吉林');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('53','长春','6');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('54','吉林','6');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('137','四平','6');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('138','辽源','6');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('139','通化','6');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('140','白山','6');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('141','松原','6');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('142','白城','6');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('143','延边朝鲜族自治州','6');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('449','高新','6');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('450','延吉','6');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('451','梅河口','6');
INSERT INTO Province(ProvinceID,NAME)VALUES('7','辽宁');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('55','沈阳','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('56','大连','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('57','锦州','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('144','鞍山','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('145','抚顺','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('146','本溪','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('147','丹东','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('148','葫芦岛','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('149','营口','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('150','盘锦','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('151','阜新','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('152','辽阳','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('153','铁岭','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('154','朝阳','7');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('467','瓦房店','7');


INSERT INTO Province(ProvinceID,NAME)VALUES('8','内蒙古');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('58','呼和浩特','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('59','包头','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('155','乌海','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('156','赤峰','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('157','通辽','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('158','鄂尔多斯','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('160','乌兰察布盟','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('161','锡林郭勒盟','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('162','巴彦淖尔盟','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('163','阿拉善盟','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('164','兴安盟','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('468','巴彦淖尔','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('469','呼伦贝尔','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('470','集宁','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('471','乌兰浩特','8');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('472','锡林浩特','8');
INSERT INTO Province(ProvinceID,NAME)VALUES('9','宁夏');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('60','银川','9');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('165','石嘴山','9');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('166','吴忠','9');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('167','固原','9');
INSERT INTO Province(ProvinceID,NAME)VALUES('10','新疆');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('61','乌鲁木齐','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('168','克拉玛依','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('169','吐鲁番地区','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('170','哈密地区','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('171','和田地区','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('172','阿克苏地区','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('173','喀什地区','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('174','克孜勒苏柯尔克孜自治州','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('175','巴音郭楞蒙古自治州','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('176','昌吉回族自治州','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('177','博尔塔拉蒙古自治州','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('178','伊犁哈萨克自治州','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('500','阿克苏','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('501','昌吉','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('502','哈密','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('503','和田','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('504','喀什','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('505','克拉马依','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('506','库尔勒','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('507','石河子','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('508','吐鲁番','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('509','乌市','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('510','奎屯','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('511','伊犁','10');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('512','伊宁','10');
INSERT INTO Province(ProvinceID,NAME)VALUES('11','青海');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('62','西宁','11');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('179','海东地区','11');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('180','海北藏族自治州','11');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('181','黄南藏族自治州','11');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('182','海南藏族自治州','11');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('183','果洛藏族自治州','11');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('184','玉树藏族自治州','11');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('185','海西蒙古族藏族自治州','11');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('473','格尔木','11');
INSERT INTO Province(ProvinceID,NAME)VALUES('12','甘肃');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('63','兰州','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('64','天水','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('186','金昌','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('187','白银','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('188','嘉峪关','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('189','武 威 ','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('190','张掖','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('191','平凉','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('192','酒泉','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('193','庆阳','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('194','定西地区','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('195','陇南地区','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('196','甘南藏族自治州','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('197','临夏回族自治州','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('422','嘉峪','12');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('423','武威','12');
INSERT INTO Province(ProvinceID,NAME)VALUES('13','陕西');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('65','西安','13');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('66','宝鸡','13');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('67','延安','13');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('198','铜川','13');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('199','咸阳','13');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('200','渭南','13');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('201','汉中','13');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('202','榆林','13');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('203','安康','13');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('204','商洛','13');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('496','韩城','13');
INSERT INTO Province(ProvinceID,NAME)VALUES('14','河北');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('68','石家庄','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('69','保定','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('205','唐山','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('206','秦皇岛','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('207','邯郸','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('208','邢台','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('209','张家口','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('210','承德','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('211','沧州','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('212','廊坊','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('213','衡水','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('436','霸州','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('437','青县','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('438','任丘','14');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('439','涿州','14');
INSERT INTO Province(ProvinceID,NAME)VALUES('15','河南');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('70','郑州','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('71','洛阳','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('214','开封','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('215','平顶山','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('216','焦作','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('217','鹤壁','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('218','新乡','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('219','安阳','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('220','濮阳','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('221','许昌','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('222','漯河','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('223','三门峡','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('224','南阳','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('225','商丘','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('226','信阳','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('227','周口','15');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('228','驻马店','15');

INSERT INTO Province(ProvinceID,NAME)VALUES('16','山东');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('72','济南','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('73','青岛','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('74','烟台','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('229','淄博','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('230','枣庄','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('231','东营','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('232','潍坊','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('233','威海','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('234','济宁','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('235','泰安','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('236','日照','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('237','莱芜','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('238','德州','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('239','临沂','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('240','聊城','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('241','滨州','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('242','菏泽','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('474','高密','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('475','荷泽','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('476','淮坊','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('477','即墨','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('478','胶南','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('479','莱州','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('480','林沂','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('481','临忻','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('482','龙口','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('483','蓬莱','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('484','青州','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('485','乳山','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('486','寿光','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('487','滕州','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('488','文登','16');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('489','招远','16');
INSERT INTO Province(ProvinceID,NAME)VALUES('17','山西');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('75','太原','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('76','大同','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('243','朔州','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('244','阳泉','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('245','长治','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('246','晋城','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('247','忻州','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('248','晋中','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('249','临汾','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('250','运城','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('251','吕梁地区','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('491','河津','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('492','侯马','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('494','孝义','17');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('495','榆次','17');
INSERT INTO Province(ProvinceID,NAME)VALUES('18','湖北');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('77','武汉','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('252','黄石','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('253','襄樊','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('254','十堰','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('255','荆州','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('256','宜昌','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('257','荆门','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('258','鄂州','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('259','孝感','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('260','黄冈','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('261','咸宁','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('262','随州','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('263','恩施土家族苗族自治州','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('440','安陆','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('441','恩施','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('442','汉口','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('443','汉阳','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('444','潜江','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('445','仙桃','18');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('446','株州','18');
INSERT INTO Province(ProvinceID,NAME)VALUES('19','湖南');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('78','长沙','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('264','株洲','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('265','湘潭','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('266','衡阳','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('267','邵阳','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('268','岳阳','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('269','常德','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('270','张家界','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('271','益阳','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('272','郴州','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('273','永州','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('274','怀化','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('275','娄底','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('276','湘西土家族苗族自治州','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('447','株州','19');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('448','邵东','19');
INSERT INTO Province(ProvinceID,NAME)VALUES('20','安徽');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('79','合肥','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('80','芜湖','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('277','蚌埠','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('278','淮南','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('279','马鞍山','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('280','淮北','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('281','铜陵','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('282','安庆','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('283','黄山','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('284','滁州','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('285','阜阳','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('286','宿州','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('287','巢湖','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('288','六安','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('289','亳州','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('290','池州','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('291','宣城','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('412','蒙城','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('413','宁国','20');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('414','桐城','20');
INSERT INTO Province(ProvinceID,NAME)VALUES('21','江苏');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('81','南京','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('292','徐州','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('293','连云港','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('294','淮安','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('295','宿迁','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('296','盐城','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('297','扬州','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('298','泰州','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('299','南通','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('300','镇江','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('301','常州','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('302','无锡','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('303','苏州','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('452','常熟','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('453','丹阳','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('454','海门','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('455','江都','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('456','江阴','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('457','靖江','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('458','昆山','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('459','溧阳','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('460','太仓','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('461','泰州华','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('462','吴江','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('463','吴县','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('464','宜兴','21');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('465','张家港','21');

INSERT INTO Province(ProvinceID,NAME)VALUES('22','浙江');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('82','杭州','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('304','宁波','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('305','温州','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('306','嘉兴','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('307','湖州','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('308','绍兴','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('309','金华','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('310','衢州','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('311','舟山','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('312','台州','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('313','丽水','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('514','慈溪','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('515','东阳','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('516','奉化','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('517','乐清','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('518','临安','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('519','临海','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('520','平湖','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('521','瑞安','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('522','上虞','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('523','嵊州','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('524','温岭','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('525','义乌','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('526','永康','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('527','余姚','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('528','诸暨','22');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('529','新昌','22');
INSERT INTO Province(ProvinceID,NAME)VALUES('23','江西');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('83','南昌','23');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('314','景德镇','23');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('315','萍乡','23');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('316','新余','23');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('317','九江','23');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('318','鹰潭','23');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('319','赣州','23');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('320','吉安','23');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('321','宜春','23');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('322','抚州','23');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('323','上饶','23');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('466','高安','23');
INSERT INTO Province(ProvinceID,NAME)VALUES('24','广东');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('84','广州','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('85','深圳','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('324','珠海','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('325','汕头','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('326','韶关','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('327','河源','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('328','梅州','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('329','惠州','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('330','汕尾','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('331','东莞','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('332','中山','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('333','江门','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('334','佛山','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('335','阳江','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('336','湛江','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('337','茂名','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('338','肇庆','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('339','清远','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('340','潮州','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('341','揭阳','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('342','云浮','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('424','花都','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('425','开平','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('426','南海','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('427','顺德','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('428','台山','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('429','增城','24');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('431','市梅','24');
INSERT INTO Province(ProvinceID,NAME)VALUES('25','广西');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('86','南宁','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('343','柳州','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('344','桂林','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('345','梧州','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('346','北海','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('347','防城港','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('348','钦州','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('349','贵港','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('350','玉林','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('351','百色','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('352','贺州','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('353','河池','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('354','来宾','25');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('355','崇左','25');
INSERT INTO Province(ProvinceID,NAME)VALUES('26','福建');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('87','福州','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('88','厦门','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('356','三明','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('357','莆田','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('358','泉州','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('359','漳州','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('360','南平','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('361','龙岩','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('362','宁德','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('415','福清','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('416','建瓯','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('417','晋江','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('418','南安','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('419','邵武','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('420','石狮','26');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('421','仙游','26');

INSERT INTO Province(ProvinceID,NAME)VALUES('27','四川');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('89','成都','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('363','自贡','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('364','攀枝花','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('365','泸州','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('366','德阳','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('367','绵阳','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('368','广元','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('369','遂宁','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('370','内江','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('371','乐山','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('372','南充','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('373','宜宾','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('374','广安','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('375','达州','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('376','巴中','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('377','雅安','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('378','眉山','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('379','资阳','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('380','阿坝藏族羌族自治州','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('381','甘孜藏族自治州','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('382','凉山彝族自治州','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('497','广汉','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('498','锦阳','27');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('499','西昌','27');
INSERT INTO Province(ProvinceID,NAME)VALUES('28','云南');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('90','昆明','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('383','曲靖','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('384','玉溪','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('385','保山','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('386','昭通','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('387','思茅地区','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('388','临沧地区','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('389','丽江','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('390','文山壮族苗族自治州','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('391','红河哈尼族彝族自治州','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('392','西双版纳傣族自治州','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('393','楚雄彝族自治州','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('394','大理白族自治州','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('395','德宏傣族景颇族自治州','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('396','怒江傈傈族自治州','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('397','迪庆藏族自治州','28');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('513','大理','28');
INSERT INTO Province(ProvinceID,NAME)VALUES('29','贵州');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('91','贵阳','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('398','六盘水','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('399','遵义','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('400','安顺','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('401','铜仁地区','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('402','毕节地区','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('403','黔西南布依族苗族自治州','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('404','黔东南苗族侗族自治州','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('405','黔南布依族苗族自治州','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('432','都匀','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('433','贵恙','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('434','凯里','29');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('435','铜仁','29');
INSERT INTO Province(ProvinceID,NAME)VALUES('30','西藏');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('92','拉萨','30');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('406','那曲地区','30');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('407','昌都地区','30');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('408','山南地区','30');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('409','日喀则地区','30');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('410','阿里地区','30');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('411','林芝地区','30');
INSERT INTO Province(ProvinceID,NAME)VALUES('31','海南');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('93','海口','31');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('94','三亚','31');
INSERT INTO Province(ProvinceID,NAME)VALUES('32','香港');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('95','香港','32');
INSERT INTO Province(ProvinceID,NAME)VALUES('33','澳门');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('96','澳门','33');
INSERT INTO Province(ProvinceID,NAME)VALUES('34','台湾');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('97','台北','34');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('98','高雄','34');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('99','台中','34');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('100','台南','34');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('101','基隆','34');
INSERT INTO City(CityID,NAME,ProvinceID)VALUES('102','新竹','34');


SELECT * FROM province;


SELECT * FROM city WHERE ProvinceID=1;


SELECT * FROM `user` WHERE username='tom' AND `password`=123 LIMIT 1;


#=================================================================================

DROP DATABASE store_v1;

CREATE DATABASE store_v1;
USE store_v1;
		
CREATE TABLE `user` (
  `uid` VARCHAR(32) NOT NULL,
  `username` VARCHAR(20) DEFAULT NULL,
  `password` VARCHAR(100) DEFAULT NULL,
  `name` VARCHAR(20) DEFAULT NULL,
  `email` VARCHAR(30) DEFAULT NULL,
  `telephone` VARCHAR(20) DEFAULT NULL,
  `birthday` DATE DEFAULT NULL,
  `sex` VARCHAR(10) DEFAULT NULL,
  `state` INT(11) DEFAULT NULL,
  `code` VARCHAR(64) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES ('373eb242933b4f5ca3bd43503c34668b','ccc','ccc','aaa','bbb@store.com','15723689921','2015-11-04','男',0,'9782f3e837ff422b9aee8b6381ccf927bdd9d2ced10d48f4ba4b9f187edf7738'),('3ca76a75e4f64db2bacd0974acc7c897','bb','bb','张三','bbb@store.com','15723689921','1990-02-01','男',0,'1258e96181a9457987928954825189000bae305094a042d6bd9d2d35674684e6'),('62145f6e66ea4f5cbe7b6f6b954917d3','cc','cc','张三','bbb@store.com','15723689921','2015-11-03','男',0,'19f100aa81184c03951c4b840a725b6a98097aa1106a4a38ba1c29f1a496c231'),('c95b15a864334adab3d5bb6604c6e1fc','bbb','bbb','老王','bbb@store.com','15712344823','2000-02-01','男',0,'71a3a933353347a4bcacff699e6baa9c950a02f6b84e4f6fb8404ca06febfd6f'),('f55b7d3a352a4f0782c910b2c70f1ea4','aaa','aaa','小王','aaa@store.com','15712344823','2000-02-01','男',1,NULL);		
			
		
SELECT * FROM USER WHERE `code` =	'e211499d81ed4446947ce81e70eb75b6' LIMIT 1	
		
		
UPDATE USER SET birthday='2019-08-01' WHERE uid='BFDCD261B46A40718EDC478612EE0742'

UPDATE USER SET username=? , `password`=? , `name`=?  ,  email=?   , telephone=? ,sex=?,state=?,`code`=? WHERE uid =?  ; 		
		
SELECT * FROM USER WHERE username=? AND `password`=? ;		
		
		
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` VARCHAR(32) NOT NULL,
  `cname` VARCHAR(20) DEFAULT NULL ,
  PRIMARY KEY (`cid`)
) 
		
		

LOCK TABLES `category` WRITE;
INSERT INTO `category` (cid,cname) VALUES
('1','手机数码'),
 ('172934bd636d485c98fd2d3d9cccd409','运动户外'),
 ('2','电脑办公'),
 ('3','家具家居'),
 ('4','鞋靴箱包'),
 ('5','图书音像'),
 ('59f56ba6ccb84cb591c66298766b83b5','aaaa'),
 ('6','母婴孕婴'),
 ('afdba41a139b4320a74904485bdb7719','汽车用品');
UNLOCK TABLES;		
		
		
		
CREATE TABLE `product` (
  `pid` VARCHAR(32) NOT NULL,
  `pname` VARCHAR(50) DEFAULT NULL,
  `market_price` DOUBLE DEFAULT NULL,
  `shop_price` DOUBLE DEFAULT NULL,
  `pimage` VARCHAR(200) DEFAULT NULL,
  `pdate` DATE DEFAULT NULL,
  `is_hot` INT(11) DEFAULT NULL,
  `pdesc` VARCHAR(255) DEFAULT NULL,
  `pflag` INT(11) DEFAULT NULL,
  `cid` VARCHAR(32) DEFAULT NULL,
  PRIMARY KEY (`pid`)
  #KEY `sfk_0001` (`cid`),
  #CONSTRAINT `sfk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `product` VALUES ('1','小米 4c 标准版',1399,1299,'products/1/c_0001.jpg','2015-11-02',1,'小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待',0,'1'),('10','华为 Ascend Mate7',2699,2599,'products/1/c_0010.jpg','2015-11-02',1,'华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！',0,'1'),('11','vivo X5Pro',2399,2298,'products/1/c_0014.jpg','2015-11-02',1,'移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术',0,'1'),('12','努比亚（nubia）My 布拉格',1899,1799,'products/1/c_0013.jpg','2015-11-02',0,'努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！',0,'1'),('13','华为 麦芒4',2599,2499,'products/1/c_0012.jpg','2015-11-02',1,'华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖',0,'1'),('14','vivo X5M',1899,1799,'products/1/c_0011.jpg','2015-11-02',0,'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV',0,'1'),('15','Apple iPhone 6 (A1586)',4399,4288,'products/1/c_0015.jpg','2015-11-02',1,'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！',0,'1'),('16','华为 HUAWEI Mate S 臻享版',4200,4087,'products/1/c_0016.jpg','2015-11-03',0,'华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版',0,'1'),('17','索尼(SONY) E6533 Z3+',4099,3999,'products/1/c_0017.jpg','2015-11-02',0,'索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G',0,'1'),('18','HTC One M9+',3599,3499,'products/1/c_0018.jpg','2015-11-02',0,'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！',0,'1'),('19','HTC Desire 826d 32G 臻珠白',1599,1469,'products/1/c_0020.jpg','2015-11-02',1,'后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！',0,'1'),('2','中兴 AXON',2899,2699,'products/1/c_0002.jpg','2015-11-05',1,'中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待',0,'1'),('20','小米 红米2A 增强版 白色',649,549,'products/1/c_0019.jpg','2015-11-02',0,'新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！',0,'1'),('21','魅族 魅蓝note2 16GB 白色',1099,999,'products/1/c_0021.jpg','2015-11-02',0,'现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！',0,'1'),('22','三星 Galaxy S5 (G9008W) 闪耀白',2099,1999,'products/1/c_0022.jpg','2015-11-02',1,'5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素',0,'1'),('23','sonim XP7700 4G手机',1799,1699,'products/1/c_0023.jpg','2015-11-09',1,'三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗',0,'1'),('24','努比亚（nubia）Z9精英版 金色',3988,3888,'products/1/c_0024.jpg','2015-11-02',1,'移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！',0,'1'),('25','Apple iPhone 6 Plus (A1524) 16GB 金色',5188,4988,'products/1/c_0025.jpg','2015-11-02',0,'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力',0,'1'),('26','Apple iPhone 6s (A1700) 64G 玫瑰金色',6388,6088,'products/1/c_0026.jpg','2015-11-02',0,'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力',0,'1'),('27','三星 Galaxy Note5（N9200）32G版',5588,5388,'products/1/c_0027.jpg','2015-11-02',0,'旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！',0,'1'),('28','三星 Galaxy S6 Edge+（G9280）32G版 铂光金',5999,5888,'products/1/c_0028.jpg','2015-11-02',0,'赠移动电源+自拍杆+三星OTG金属U盘+无线充电器+透明保护壳',0,'1'),('29','LG G4（H818）陶瓷白 国际版',3018,2978,'products/1/c_0029.jpg','2015-11-02',0,'李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！',0,'1'),('3','华为荣耀6',1599,1499,'products/1/c_0003.jpg','2015-11-02',0,'荣耀 6 (H60-L01) 3GB内存标准版 黑色 移动4G手机',0,'1'),('30','微软(Microsoft) Lumia 640 LTE DS (RM-1113)',1099,999,'products/1/c_0030.jpg','2015-11-02',0,'微软首款双网双卡双4G手机，5.0英寸高清大屏，双网双卡双4G！',0,'1'),('31','宏碁（acer）ATC705-N50 台式电脑',2399,2299,'products/1/c_0031.jpg','2015-11-02',0,'爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！',0,'2'),('32','Apple MacBook Air MJVE2CH/A 13.3英寸',6788,6688,'products/1/c_0032.jpg','2015-11-02',0,'宽屏笔记本电脑 128GB 闪存',0,'2'),('33','联想（ThinkPad） 轻薄系列E450C(20EH0001CD)',4399,4199,'products/1/c_0033.jpg','2015-11-02',0,'联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)',0,'2'),('34','联想（Lenovo）小新V3000经典版',4599,4499,'products/1/c_0034.jpg','2015-11-02',0,'14英寸超薄笔记本电脑（i7-5500U 4G 500G+8G SSHD 2G独显 全高清屏）黑色满1000減100，狂减！火力全开，横扫3天！',0,'2'),('35','华硕（ASUS）经典系列R557LI',3799,3699,'products/1/c_0035.jpg','2015-11-02',0,'15.6英寸笔记本电脑（i5-5200U 4G 7200转500G 2G独显 D刻 蓝牙 Win8.1 黑色）',0,'2'),('36','华硕（ASUS）X450J',4599,4399,'products/1/c_0036.jpg','2015-11-02',0,'14英寸笔记本电脑 （i5-4200H 4G 1TB GT940M 2G独显 蓝牙4.0 D刻 Win8.1 黑色）',0,'2'),('37','戴尔（DELL）灵越 飞匣3000系列',3399,3299,'products/1/c_0037.jpg','2015-11-03',0,' Ins14C-4528B 14英寸笔记本（i5-5200U 4G 500G GT820M 2G独显 Win8）黑',0,'2'),('38','惠普(HP)WASD 暗影精灵',5699,5499,'products/1/c_0038.jpg','2015-11-02',0,'15.6英寸游戏笔记本电脑(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G独显 Win10)',0,'2'),('39','Apple 配备 Retina 显示屏的 MacBook',11299,10288,'products/1/c_0039.jpg','2015-11-02',0,'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存',0,'2'),('4','联想 P1',2199,1999,'products/1/c_0004.jpg','2015-11-02',0,'联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！',0,'1'),('40','机械革命（MECHREVO）MR X6S-M',6799,6599,'products/1/c_0040.jpg','2015-11-02',0,'15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色',0,'2'),('41','神舟（HASEE） 战神K660D-i7D2',5699,5499,'products/1/c_0041.jpg','2015-11-02',0,'15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色',0,'2'),('42','微星（MSI）GE62 2QC-264XCN',6199,5999,'products/1/c_0042.jpg','2015-11-02',0,'15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色',0,'2'),('43','雷神（ThundeRobot）G150S',5699,5499,'products/1/c_0043.jpg','2015-11-02',0,'15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金',0,'2'),('44','惠普（HP）轻薄系列 HP',3199,3099,'products/1/c_0044.jpg','2015-11-02',0,'15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰',0,'2'),('45','未来人类（Terrans Force）T5',10999,9899,'products/1/c_0045.jpg','2015-11-02',0,'15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑',0,'2'),('46','戴尔（DELL）Vostro 3800-R6308 台式电脑',3299,3199,'products/1/c_0046.jpg','2015-11-02',0,'（i3-4170 4G 500G DVD 三年上门服务 Win7）黑',0,'2'),('47','联想（Lenovo）H3050 台式电脑',5099,4899,'products/1/c_0047.jpg','2015-11-11',0,'（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸',0,'2'),('48','Apple iPad mini 2 ME279CH/A',2088,1888,'products/1/c_0048.jpg','2015-11-02',0,'（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）',0,'2'),('49','小米（MI）7.9英寸平板',1399,1299,'products/1/c_0049.jpg','2015-11-02',0,'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色',0,'2'),('5','摩托罗拉 moto x（x+1）',1799,1699,'products/1/c_0005.jpg','2015-11-01',0,'摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！',0,'1'),('50','Apple iPad Air 2 MGLW2CH/A',2399,2299,'products/1/c_0050.jpg','2015-11-12',0,'（9.7英寸 16G WLAN 机型 银色）',0,'2'),('6','魅族 MX5 16GB 银黑色',1899,1799,'products/1/c_0006.jpg','2015-11-02',0,'魅族 MX5 16GB 银黑色 移动联通双4G手机 双卡双待送原厂钢化膜+保护壳+耳机！5.5英寸大屏幕，3G运行内存，2070万+500万像素摄像头！长期省才是真的省！',0,'1'),('7','三星 Galaxy On7',1499,1398,'products/1/c_0007.jpg','2015-11-14',0,'三星 Galaxy On7（G6000）昂小七 金色 全网通4G手机 双卡双待新品火爆抢购中！京东尊享千元良机！5.5英寸高清大屏！1300+500W像素！评价赢30元话费券！',0,'1'),('8','NUU NU5',1288,1190,'products/1/c_0008.jpg','2015-11-02',0,'NUU NU5 16GB 移动联通双4G智能手机 双卡双待 晒单有礼 晨光金香港品牌 2.5D弧度前后钢化玻璃 随机附赠手机套+钢化贴膜 晒单送移动电源+蓝牙耳机',0,'1'),('9','乐视（Letv）乐1pro（X800）',2399,2299,'products/1/c_0009.jpg','2015-11-02',0,'乐视（Letv）乐1pro（X800）64GB 金色 移动联通4G手机 双卡双待乐视生态UI+5.5英寸2K屏+高通8核处理器+4GB运行内存+64GB存储+1300万摄像头！',0,'1');		

SELECT * FROM product WHERE is_hot=1	ORDER BY pdate ASC LIMIT 9;	
		
		
SELECT * FROM product 	WHERE pid=1;
		
SELECT * FROM product		
		
		
SHOW VARIABLES LIKE 'character%';		
		
ALTER DATABASE store_v1 CHARACTER SET utf8;		
		
		
#太多连接
SHOW VARIABLES LIKE '%max_connections%';
SET GLOBAL max_connections=10000;		
		
#订单表		
CREATE TABLE `orders` (
  `oid` VARCHAR(32) NOT NULL,
  `ordertime` DATETIME DEFAULT NULL,
  `total` DOUBLE DEFAULT NULL,
  `state` INT(11) DEFAULT NULL,
  `address` VARCHAR(30) DEFAULT NULL,
  `name` VARCHAR(20) DEFAULT NULL,
  `telephone` VARCHAR(20) DEFAULT NULL,
  `uid` VARCHAR(32) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;	

INSERT INTO orders (oid,ordertime,total,state,address,`name`,telephone,uid) VALUES ()	
		
		
#订单项表
CREATE TABLE `orderitem` (
  `itemid` VARCHAR(32) NOT NULL,
  `count` INT(11) DEFAULT NULL,
  `subtotal` DOUBLE DEFAULT NULL,
  `pid` VARCHAR(32) DEFAULT NULL,
  `oid` VARCHAR(32) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `fk_0001` (`pid`),
  KEY `fk_0002` (`oid`),
  CONSTRAINT `fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;		

INSERT INTO (itemid,`count`,subtotal,pid,oid) VALUES();		

SHOW CREATE TABLE orders;
TABLE   CREATE TABLE                                                                                                                                                                                                                                                                                                                                                             
------  -----------------------------------------
orders  CREATE TABLE `orders` (                                                                                                                                                                                                                                                                                                                                                  
          `oid` VARCHAR(32) NOT NULL,                                                                                                                                                                                                                                                                                                                                            
          `ordertime` DATETIME DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                     
          `total` DOUBLE DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                           
          `state` INT(11) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                          
          `address` VARCHAR(30) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                    
          `name` VARCHAR(20) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                       
          `telephone` VARCHAR(20) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                  
          `uid` VARCHAR(32) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                        
          PRIMARY KEY (`oid`)                                                                                                                                                                                                                                                                                                                                                    
        ) ENGINE=INNODB DEFAULT CHARSET=utf8    
        
#================================================================================================                                                                                                                                                                                                                                                                                                                                         	

SELECT * FROM `user`
#2A821CD1CD06424482BA35129A369871  pikaqiu 

SELECT * FROM orders WHERE uid='2A821CD1CD06424482BA35129A369871' ;
#oid                               ordertime             total   state  address  NAME    telephone  uid                               
#--------------------------------  -------------------  ------  ------  -------  ------  ---------  ----------------------------------
#1608c776-4032-4b74-b5fe-3d2b9e68  2019-08-04 10:34:23   47362       0  (NULL)   (NULL)  (NULL)     2A821CD1CD06424482BA35129A369871  
#29c082e9-c678-4968-aa12-648cd23b  2019-08-04 10:32:39    2299       0  (NULL)   (NULL)  (NULL)     2A821CD1CD06424482BA35129A369871  
#931a92e2-c8e8-4bca-bb5d-b1d563f6  2019-08-04 10:34:39    5999       0  (NULL)   (NULL)  (NULL)     2A821CD1CD06424482BA35129A369871  
#93b56f41-9f42-4dff-825d-df3c0f00  2019-08-04 10:34:58    3999       0  (NULL)   (NULL)  (NULL)     2A821CD1CD06424482BA35129A369871  
#a745487e-ad02-4c1b-bdc9-8af71f72  2019-08-04 10:35:25    3999       0  (NULL)   (NULL)  (NULL)     2A821CD1CD06424482BA35129A369871  
#bebfbb99-a11e-4d8b-8ac8-94c5a742  2019-08-04 10:35:48    5499       0  (NULL)   (NULL)  (NULL)     2A821CD1CD06424482BA35129A369871  
#cde7c4f2-4f62-43bb-9b68-70d44c98  2019-08-04 10:34:49    2699       0  (NULL)   (NULL)  (NULL)     2A821CD1CD06424482BA35129A369871  
#cf1bcd99-f120-4187-916d-390e0eec  2019-08-04 10:35:35    3999       0  (NULL)   (NULL)  (NULL)     2A821CD1CD06424482BA35129A369871  
#d609d3ca-b54e-40ec-adbb-969b353d  2019-08-04 10:35:07    4288       0  (NULL)   (NULL)  (NULL)     2A821CD1CD06424482BA35129A369871  
#df2cb5a2-cf86-4d02-931b-ce97fdf3  2019-08-04 10:34:33    4199       0  (NULL)   (NULL)  (NULL)     2A821CD1CD06424482BA35129A369871  


SELECT * FROM orderitem WHERE oid =  '1608c776-4032-4b74-b5fe-3d2b9e68' ;
#itemid                             count  subtotal  pid     oid                               
#--------------------------------  ------  --------  ------  ----------------------------------
#1227d3f2-8dd8-44f3-a93d-f66c77d5       1      4199  33      1608c776-4032-4b74-b5fe-3d2b9e68  
#3cee1825-142c-4b6d-a9c6-e2f89d67       1      4087  16      1608c776-4032-4b74-b5fe-3d2b9e68  
#55c42345-44fb-4dee-b978-f8947652      20     25980  1       1608c776-4032-4b74-b5fe-3d2b9e68  
#719db1fb-af58-40d4-97d1-0b577ee0       1      3699  35      1608c776-4032-4b74-b5fe-3d2b9e68  
#8a7b47a1-0b04-470b-8253-5d271660       1      4499  34      1608c776-4032-4b74-b5fe-3d2b9e68  
#c7a77f7e-7f2e-4e6b-8c20-4e14c39d       1      2299  31      1608c776-4032-4b74-b5fe-3d2b9e68  
#e97424f3-fc99-4329-81ef-8ddd6906       1      2599  10      1608c776-4032-4b74-b5fe-3d2b9e68  

SELECT *FROM product  WHERE pid = 33


SELECT * FROM orderitem A, product B WHERE A.pid=B.pid  AND A.oid ='d609d3ca-b54e-40ec-adbb-969b353d' ORDER BY itemid;

SELECT COUNT(*) FROM orders WHERE uid='2A821CD1CD06424482BA35129A369871' ;

SELECT * FROM orders WHERE oid='0d81dea1-0676-4f09-b5f0-2b6dd7cb' ;

INSERT INTO category (cid,cname) VALUES () ;


SELECT * FROM category WHERE cid =1;

UPDATE category SET cname= ? WHERE cid =? ; 

UPDATE product SET cid = NULL WHERE cid = ? ;


DELETE FROM category WHERE cid = '6EE42D6651C844988B33BD9A16075B41';

SELECT * FROM product A JOIN category B ON A.cid=B.cid;

INSERT  INTO `product`(`pid`,`pname`,`market_price`,    `shop_price`,`pimage`,`pdate`,  
        `is_hot`,`pdesc`,`pflag`   ,`cid`) 
        VALUES ( ? , ? , ? , ? ,             ? , ? , ? , ? ,           ? , ? , ) 
        Parameters: [03C315D8AF6F409492F68770D1BD635C, 123, 55.0, 5.0, /products/1/15deb65966f94fdd8cf8db5011608d30, Mon Aug 05 21:57:19 CST 2019, 1, sdfghjk, 0, 1]

SELECT uid FROM `user` WHERE username ='tom' 



SELECT * FROM orders WHERE 1=1 

SELECT * FROM orders WHERE 1=1   AND state = 0 LIMIT 3


CREATE DATABASE hello_hibernate;
USE hello_hibernate;
CREATE TABLE `cst_customer` (
  `cust_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  `cust_name` VARCHAR(32) NOT NULL COMMENT '客户名称(公司名称)',
  `cust_user_id` BIGINT(32) DEFAULT NULL COMMENT '负责人id',
  `cust_create_id` BIGINT(32) DEFAULT NULL COMMENT '创建人id',
  `cust_source` VARCHAR(32) DEFAULT NULL COMMENT '客户信息来源',
  `cust_industry` VARCHAR(32) DEFAULT NULL COMMENT '客户所属行业',
  `cust_level` VARCHAR(32) DEFAULT NULL COMMENT '客户级别',
  `cust_linkman` VARCHAR(64) DEFAULT NULL COMMENT '联系人',
  `cust_phone` VARCHAR(64) DEFAULT NULL COMMENT '固定电话',
  `cust_mobile` VARCHAR(16) DEFAULT NULL COMMENT '移动电话',
  PRIMARY KEY (`cust_id`)
) ENGINE=INNODB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

DROP TABLE cst_customer;
DESC cst_customer;

SHOW VARIABLES LIKE "character%";
SET GLOBAL character_set_database = utf8;
SET GLOBAL character_set_server = utf8;
SET character_set_connection = utf8;

TRUNCATE cst_customer;

CREATE TABLE `cst_linkman` (
  `lkm_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',
  `lkm_name` VARCHAR(16) DEFAULT NULL COMMENT '联系人姓名',
  `lkm_cust_id` BIGINT(32) NOT NULL COMMENT '客户id',
  `lkm_gender` CHAR(1) DEFAULT NULL COMMENT '联系人性别',
  `lkm_phone` VARCHAR(16) DEFAULT NULL COMMENT '联系人办公电话',
  `lkm_mobile` VARCHAR(16) DEFAULT NULL COMMENT '联系人手机',
  `lkm_email` VARCHAR(64) DEFAULT NULL COMMENT '联系人邮箱',
  `lkm_qq` VARCHAR(16) DEFAULT NULL COMMENT '联系人qq',
  `lkm_position` VARCHAR(16) DEFAULT NULL COMMENT '联系人职位',
  `lkm_memo` VARCHAR(512) DEFAULT NULL COMMENT '联系人备注',
  PRIMARY KEY (`lkm_id`),
  KEY `FK_cst_linkman_lkm_cust_id` (`lkm_cust_id`),
  CONSTRAINT `FK_cst_linkman_lkm_cust_id` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DESC `hello_hibernate``cst_linkman`;
DESC cst_customer;


SELECT lkm_cust_id FROM cst_linkman
SELECT * FROM cst_customer

DELETE FROM cst_customer WHERE cust_id NOT IN ( SELECT lkm_cust_id FROM cst_linkman );


SELECT cust_id ,cust_name,lkm_id,lkm_name FROM cst_customer A,cst_linkman B WHERE A.`cust_id`=B.`lkm_cust_id`;


SET GLOBAL general_log='ON';
SHOW VARIABLES LIKE 'general%';
SHOW VARIABLES LIKE 'general_log';
SET GLOBAL general_log='OFF';

SHOW CREATE TABLE cst_linkman;
CREATE TABLE `cst_linkman` (                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
   `lkm_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
   `lkm_name` VARCHAR(16) DEFAULT NULL COMMENT '联系人姓名',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
   `lkm_cust_id` BIGINT(32) NOT NULL COMMENT '客户id',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
   `lkm_gender` CHAR(1) DEFAULT NULL COMMENT '联系人性别',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
   `lkm_phone` VARCHAR(16) DEFAULT NULL COMMENT '联系人办公电话',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
   `lkm_mobile` VARCHAR(16) DEFAULT NULL COMMENT '联系人手机',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
   `lkm_email` VARCHAR(64) DEFAULT NULL COMMENT '联系人邮箱',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
   `lkm_qq` VARCHAR(16) DEFAULT NULL COMMENT '联系人qq',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
   `lkm_position` VARCHAR(16) DEFAULT NULL COMMENT '联系人职位',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
   `lkm_memo` VARCHAR(512) DEFAULT NULL COMMENT '联系人备注',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
   `version` INT(11) NOT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
   PRIMARY KEY (`lkm_id`),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
   KEY `FK_cst_linkman_lkm_cust_id` (`lkm_cust_id`),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
   CONSTRAINT `FK_cst_linkman_lkm_cust_id` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
   CONSTRAINT `FKh9yp1nql5227xxcopuxqx2e7q` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
 ) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 


DESCRIBE `hello_hibernate`.`cst_linkman`
SHOW INDEX FROM `hello_hibernate`.`cst_linkman`
SHOW CREATE TABLE `hello_hibernate`.`cst_linkman`
SHOW FULL FIELDS FROM `hello_hibernate`.`cst_linkman`
SHOW KEYS FROM `hello_hibernate`.`cst_linkman`
SELECT * FROM `hello_hibernate`.`cst_customer` WHERE `hello_hibernate`.`cst_customer`.`cust_id` = '4' LIMIT 0, 25
SELECT * FROM `hello_hibernate`.`cst_customer` WHERE `hello_hibernate`.`cst_customer`.`cust_id` = '4' LIMIT 0, 25
UPDATE `hello_hibernate`.`cst_linkman` SET `lkm_cust_id` = '7' 	WHERE 	`lkm_id` = '15'

UPDATE cst_customer SET VERSION=?, cust_name=?, cust_user_id=?, cust_create_id=?, cust_source=?, cust_industry=?, cust_level=?, cust_linkman=?, cust_phone=?, cust_mobile=? WHERE cust_id=? AND VERSION=?
UPDATE cst_customer SET VERSION=2, cust_name='马云云', cust_user_id=NULL, cust_create_id=NULL, cust_source=NULL, cust_industry=NULL, cust_level=NULL, cust_linkman=NULL, cust_phone=NULL, cust_mobile=NULL WHERE cust_id=4 AND VERSION=1
	
UPDATE cst_linkman SET VERSION=?, lkm_name=?, lkm_gender=?, lkm_phone=?, lkm_mobile=?, lkm_email=?, lkm_qq=?, lkm_position=?, lkm_memo=?, lkm_cust_id=? WHERE lkm_id=? AND VERSION=?
UPDATE cst_linkman SET VERSION=2, lkm_name='杨过', lkm_gender=NULL, lkm_phone=NULL, lkm_mobile=NULL, lkm_email=NULL, lkm_qq=NULL, lkm_position=NULL, lkm_memo=NULL, lkm_cust_id=4 WHERE lkm_id=15 AND VERSION=1
	
UPDATE cst_linkman SET lkm_cust_id=? WHERE lkm_id=?
UPDATE cst_linkman SET lkm_cust_id=4 WHERE lkm_id=15

SHOW CREATE TABLE cst_linkman;
SHOW TABLES;
DROP TABLE cst_linkman;             
DROP TABLE cst_customer;  


CREATE TABLE cst_customer (
  cust_id BIGINT NOT NULL AUTO_INCREMENT,
  VERSION INTEGER NOT NULL,
  cust_name VARCHAR (255),
  cust_user_id BIGINT,
  cust_create_id BIGINT,
  cust_source VARCHAR (255),
  cust_industry VARCHAR (255),
  cust_level VARCHAR (255),
  cust_linkman VARCHAR (255),
  cust_phone VARCHAR (255),
  cust_mobile VARCHAR (255),
  PRIMARY KEY (cust_id)
)

 CREATE TABLE cst_linkman (
  lkm_id BIGINT NOT NULL AUTO_INCREMENT,
  VERSION INTEGER NOT NULL,
  lkm_name VARCHAR (255),
  lkm_gender VARCHAR (255),
  lkm_phone VARCHAR (255),
  lkm_mobile VARCHAR (255),
  lkm_email VARCHAR (255),
  lkm_qq VARCHAR (255),
  lkm_position VARCHAR (255),
  lkm_memo VARCHAR (255),
  lkm_cust_id BIGINT,
  PRIMARY KEY (lkm_id)
)

#====================================
CREATE TABLE cst_customer (cust_id BIGINT NOT NULL AUTO_INCREMENT, VERSION INTEGER NOT NULL, cust_name VARCHAR(255), cust_user_id BIGINT, cust_create_id BIGINT, cust_source VARCHAR(255), cust_industry VARCHAR(255), cust_level VARCHAR(255), cust_linkman VARCHAR(255), cust_phone VARCHAR(255), cust_mobile VARCHAR(255), PRIMARY KEY (cust_id))

CREATE TABLE cst_linkman (lkm_id BIGINT NOT NULL AUTO_INCREMENT, VERSION INTEGER NOT NULL, lkm_name VARCHAR(255), lkm_gender VARCHAR(255), lkm_phone VARCHAR(255), lkm_mobile VARCHAR(255), lkm_email VARCHAR(255), lkm_qq VARCHAR(255), lkm_position VARCHAR(255), lkm_memo VARCHAR(255), lkm_cust_id BIGINT, PRIMARY KEY (lkm_id))

ALTER TABLE cst_linkman ADD CONSTRAINT FKh9yp1nql5227xxcopuxqx2e7q FOREIGN KEY (lkm_cust_id) REFERENCES cst_customer (cust_id)


//		cust_id  cust_name  lkm_id  lkm_name   
//		-------  ---------  ------  -----------
//		      7  韦小宝             1  令狐冲  


SHOW CREATE TABLE cst_linkman;

SELECT cust_id ,cust_name,lkm_id,lkm_name FROM cst_customer A,cst_linkman B WHERE A.`cust_id`=B.`lkm_cust_id`;

SHOW CREATE TABLE t_cousrse;
CREATE TABLE `t_cousrse` (                                                                                                                                              
`cid` BIGINT(20) NOT NULL AUTO_INCREMENT,                                                                                                                             
`cname` VARCHAR(255) DEFAULT NULL,                                                                                                                                    
PRIMARY KEY (`cid`)                                                                                                                                                   
) ENGINE=INNODB DEFAULT CHARSET=utf8

SHOW CREATE TABLE  t_cousrse;
CREATE TABLE `t_cousrse` (                                                                                                                                              
`cid` BIGINT(20) NOT NULL AUTO_INCREMENT,                                                                                                                             
`cname` VARCHAR(255) DEFAULT NULL,                                                                                                                                    
PRIMARY KEY (`cid`)                                                                                                                                                   
) ENGINE=INNODB DEFAULT CHARSET=utf8 
           
SHOW CREATE TABLE  elective_list;
CREATE TABLE `elective_list` (                                                                                                                                                                                                                                                                                                                                                                        
`cid` BIGINT(20) NOT NULL,                                                                                                                                                                                                                                                                                                                                                                          
`sid` BIGINT(20) NOT NULL,                                                                                                                                                                                                                                                                                                                                                                          
PRIMARY KEY (`sid`,`cid`),                                                                                                                                                                                                                                                                                                                                                                          
KEY `FK716xwi4f3nf8ikel4r253w6pk` (`cid`),                                                                                                                                                                                                                                                                                                                                                          
CONSTRAINT `FK716xwi4f3nf8ikel4r253w6pk` FOREIGN KEY (`cid`) REFERENCES `t_cousrse` (`cid`),                                                                                                                                                                                                                                                                                                        
CONSTRAINT `FKjw67x5rqx4ttkgmg75qxkrbdc` FOREIGN KEY (`sid`) REFERENCES `t_student` (`sid`)                                                                                                                                                                                                                                                                                                         
) ENGINE=INNODB DEFAULT CHARSET=utf8    

SELECT * FROM  t_cousrse A ,elective_list B ,t_student C WHERE A.cid=B.cid AND B.sid=C.sid;
   cid  cname            cid     sid     sid  sname   
------  ------------  ------  ------  ------  --------
     2  高等数学               2       3       3  小明  


SELECT cust_id ,cust_name,lkm_id,lkm_name FROM cst_customer A,cst_linkman B WHERE A.`cust_id`=B.`lkm_cust_id`;
cust_id  cust_name  lkm_id  lkm_name   
-------  ---------  ------  -----------
     11  马云云             7  熊大     
     11  马云云             8  王建立  
     12  韦小宝             9  令狐冲  
     12  韦小宝            10  杨过     

SET GLOBAL general_log='ON';
SHOW VARIABLES LIKE 'general%';
SHOW VARIABLES LIKE 'general_log';
SET GLOBAL general_log='OFF';


CREATE DATABASE spring;
USE spring;
CREATE TABLE t_account(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20),
	money DOUBLE
);

DROP DATABASE spring_day03;	

INSERT INTO t_account VALUES(NULL,"张三","10000")











