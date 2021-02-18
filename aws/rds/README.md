
# RDS

* 最初に root ユーザ/パスワードを決める
* サブネット/セキュリティグループ設定をミスると繋がらないので注意
  (パブリックアクセスを許可すると別 VPC でもアクセス可能)

* リードレプリカは後から追加できる。マスタは "プライマリ"、リードレプリカは "レプリカ" と表示される。
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


## DB 作成 (サンプル)

CREATE DATABASE storage;
USE storage;
CREATE TABLE sample(
  id INT(11) NOT NULL, 
  value VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);

DESC sample;

INSERT INTO sample(id, value) VALUES (1, "aaa");
INSERT INTO sample(id, value) VALUES (2, "bbb");


# Apache

## 


# Tomcat

## 

# API コール

