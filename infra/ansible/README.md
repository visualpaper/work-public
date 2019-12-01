[Ansible]

冪等性を持つ環境構築ツール
※ こうあるべき、という環境をテンプレートに記載する。

[利用手順]

1. インストール
   > sudo yum update
   > sudo yum -y install python-devel openssl-devel gcc git
   > sudo easy_install pip
   > sudo pip install ansible
   > ansible --version
     ⇒ バージョンが表示されれば OK

2. hosts ファイル作成
   ※ ini ファイルで記載しているが、別に yaml でも良い (https://qiita.com/hiroyuki_onodera/items/e6d0d308eb44e26fa03f)
   ※ localhost (自身) のマシンに実施するも↑リンクに記載されている (localhost ansible_connection=local)

   Playbook で指定する hosts のリファレンスとなる。

3. Playbook 作成
   ※ 別リポジトリでやるため、割愛

4. 実施
   > ansible-playbook -i inventory/hosts playbook.yml
   ※ --extra-vars "hosts=all user=root" で group_vars に定義していないものを差し込むこともできる。
