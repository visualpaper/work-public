
# RDS

* 最初に root ユーザ/パスワードを決める
* サブネット/セキュリティグループ設定をミスると繋がらないので注意
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

