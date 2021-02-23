
# Chef

* 特定の状態を定義する
* 冪等性 (変更がある部分のみ更新される)

# マシン用意

* Knife solo サーバ
* 適用サーバ

※ これ以降の内容は全て Knife solo サーバで行う。

# ChefDK

## インストール

* wget https://opscode-omnibus-packages.s3.amazonaws.com/el/6/x86_64/chefdk-0.9.0-1.el6.x86_64.rpm
* rpm -ivh chefdk-0.9.0-1.el6.x86_64.rpm
* export PATH=/opt/chefdk/embedded/bin:$PATH

# Ruby

## インストール

* yum -y install ruby-devel

# Knife solot

## インストール

* chef gem install knife-solo

## cook book

### リポジトリ作成

```
* knife solo init .
  ※ 以下ファイル・フォルダが用意される
     - Berksfile
     - cookbooks
     - data_bags
     - environments
     - nodes
     - roles
     - site-cookbooks

* knife cookbook create httpd -o site-cookbooks
  ※ httpd という cookbook 名で作成している。
  ※ site-cookbook 配下に recipes などが作成される
```

### レシピ作成

site-cookbooks/httpd/recipes/default.rb にレシピを書く

(例)
```
package 'httpd' do
  action :install
end

service 'httpd' do
  action [ :enable, :start]
  supports :status => true, :restart => true, :reload => true
end
```

### ノード設定

nodes/xxxxx.json に適用ノードを書く

(例)
```
{
  "run_list": [
    "recipe[httpd]"
  ],
  "automatic": {
    "ipaddress": "3.113.215.105"
  }
}
```

### ssh/hosts 設定

鍵生成 ～ 登録までは省略
↑で指定した xxxxx を展開する EC2 の EIP に曲げる

```
sudo /etc/hosts

3.113.215.105 test-web01
```

### 実行

* knife solo cook test-web01
  ※ -W を付けると dry run
