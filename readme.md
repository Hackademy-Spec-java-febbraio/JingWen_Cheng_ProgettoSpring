# 一些代码

# 如何开启网页 找到ProjectjavaApplication.java 然后使用运行和调和，在网页的链接处搜索 http://localhost:8080

# mvn dependency:tree

# mvn clean install

# mvn clean test -X

# mvn spring-boot:run

# mysql -u root -p
# 没有密码的用户 mysql -u root

# 数据库密码 6643
# 输入设备密码 Password:
# 输入数据库密码 Enter Password:

# mysql的一些操作及知识
# 启动数据库 sudo mysql.server start
# 连接数据库 mysql -u root -p
# 退出客服端 EXIT;
# 停止数据库的运行 sudo mysql.server stop
# 创建一个新的用户和密码 CREATE USER 'new_project_user'@'localhost' IDENTIFIED BY 'your_unique_password';
# 基于用户的权限 GRANT ALL PRIVILEGES ON new_project.* TO 'new_project_user'@'localhost';FLUSH PRIVILEGES;
# 检查mysql是否在运行 ps aux | grep mysql
# 使用数据库 USE DatabaseId;
# 创建新的数据库 CREATE DATABASE DatabaseId;
# 删除数据库 DROP DATABASE DatabaseId;
# 查看数据库 SHOW DATABASES;
# 查阅数据 SELECT * FROM users;
# 修改数据 UPDATE users SET name = 'Bob' WHERE id = 1;
# 删除数据 DELETE FROM users WHERE id = 1;

# SOURCE /Users/riicardo/jv/progetto_finale_jingwencheng/sql/drop.sql;
# SOURCE /Users/riicardo/jv/progetto_finale_jingwencheng/sql/create.sql;
# SOURCE /Users/riicardo/jv/progetto_finale_jingwencheng/sql/insert.sql;
# SHOW TABLES;
# SELECT * FROM users;
# SELECT * FROM roles;
# SELECT * FROM users_roles;
# SELECT * FROM categories;

# brew services start mysql
# brew services stop mysql
# brew services list
# brew services restart mysql
# mysql.server start
# sudo rm -f /opt/homebrew/var/mysql/*.pid
# mysql_secure_installation
# /opt/homebrew/opt/mysql/bin/mysqld_safe --datadir=/opt/homebrew/var/mysql
# sudo pkill mysqld


# sudo lsof -i :3306
# sudo kill -9 <PID>
# sudo kill -9 27885

# brew update
# brew upgrade

