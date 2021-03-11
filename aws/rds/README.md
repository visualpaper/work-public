
# RDS

* 最初に root ユーザ/パスワードを決める
* サブネット/セキュリティグループ設定をミスると繋がらないので注意
  (パブリックアクセスを許可すると別 VPC でもアクセス可能)

* リードレプリカは後から追加できる。
  ※ マスタは "プライマリ"、リードレプリカは "レプリカ" と表示される。
  (リードレプリカも同様に Endpoint が払い出される)

* マイナーバージョンの自動更新にチェックを付けると特定タイミングで勝手に更新される

## Client

```
sudo yum update
sudo yum install yum-utils
wget https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm
sudo yum localinstall mysql80-community-release-el7-3.noarch.rpm

sudo yum repolist all | grep mysql
sudo yum-config-manager --disable mysql80-community
sudo yum-config-manager --enable mysql56-community

sudo yum install mysql-community-client

mysql -h umejima-mysql.cbnnayxzw8fz.ap-northeast-1.rds.amazonaws.com -u admin -p
↑で接続できる。
```


## DB 作成

```
CREATE DATABASE storage;
USE storage;

CREATE TABLE master_table (
  id INT(11) AUTO_INCREMENT NOT NULL, 
  name VARCHAR(512) NOT NULL,
  create_date datetime NOT NULL,
  update_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE trun_number_table (
  id INT(11) AUTO_INCREMENT NOT NULL, 
  master_id INT(11) NOT NULL, 
  big_int_value BIGINT(20) DEFAULT NULL,
  tyny_int_value TINYINT(4) DEFAULT NULL,
  small_int_value SMALLINT(6) DEFAULT NULL,
  small_int_unsigned_value SMALLINT(6) unsigned DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE trun_string_table (
  id INT(11) AUTO_INCREMENT NOT NULL, 
  master_id INT(11) NOT NULL, 
  var_char_value VARCHAR(32) binary DEFAULT NULL,
  var_binary_value VARBINARY(10) DEFAULT NULL,
  medium_blob_value MEDIUMBLOB DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO master_table(id, name, create_date, update_date) VALUES (1, "aaa", now(), now());
INSERT INTO master_table(id, name, create_date, update_date) VALUES (2, "bbb", now(), now());
INSERT INTO master_table(id, name, create_date, update_date) VALUES (3, "ccc", now(), now());
INSERT INTO master_table(id, name, create_date, update_date) VALUES (4, "ddd", now(), now());
INSERT INTO master_table(id, name, create_date, update_date) VALUES (5, "eee", now(), now());
```

# Apache

## インストール

* yum -y install httpd

## Apache 起動

* service httpd start


# Java
## インストール

* yum -y install java-1.8.0-openjdk-devel

## JAVA_HOME 設定

* echo "export JAVA_HOME=$(readlink -e $(which java)|sed 's:/bin/java::')" > /etc/profile.d/java.sh
* echo "PATH=\$PATH:\$JAVA_HOME/bin" >> /etc/profile.d/java.sh
* source /etc/profile


# Tomcat
## ユーザ作成

* useradd -s /sbin/nologin tomcat

## セットアップ

* curl -O http://ftp.yz.yamagata-u.ac.jp/pub/network/apache/tomcat/tomcat-9/v9.0.43/bin/apache-tomcat-9.0.43.tar.gz
* tar xvzf apache-tomcat-9.0.43.tar.gz -C /opt
* ln -s /opt/apache-tomcat-9.0.43 /opt/apache-tomcat
* chown -R tomcat:tomcat /opt/apache-tomcat-9.0.43

## CATALINA_HOME 設定

* echo "export CATALINA_HOME=/opt/apache-tomcat" > /etc/profile.d/tomcat.sh
* source /etc/profile

## tomcat.service 作成

```
[Unit]
Description=Apache Tomcat 9
After=syslog.target network.target

[Service]
User=tomcat
Group=tomcat
Type=oneshot
PIDFile=/opt/apache-tomcat/tomcat.pid
RemainAfterExit=yes

ExecStart=/opt/apache-tomcat/bin/startup.sh
ExecStop=/opt/apache-tomcat/bin/shutdown.sh
ExecReStart=/opt/apache-tomcat/bin/shutdown.sh;/opt/apache-tomcat/bin/startup.sh

[Install]
WantedBy=multi-user.target
```

## tomcat 起動

* sudo service tomcat start

# APP 配置
## apache

* application.conf を配置
  ※ proxy pass

## tomcat

* /opt/apache-tomcat/webapps/ 配下に server.war を配置
* /opt/apache-tomcat/libs/ 配下に mysql-connector-java.jar を配置
  ※ jdbc 5.1.39

* /opt/apache-tomcat/conf/context.xml を配置
  ※ jndi

※ 上記配置ファイルは一律 tomcat:tomcat 所有者にすること。

# API コール

* read
  - http://umejima-vpc-elb-mysql-1134160612.ap-northeast-1.elb.amazonaws.com/app/rds/read/{id}
