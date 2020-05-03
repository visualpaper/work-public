# terraform

↓を見れば大体理解できる  
(参照) https://qiita.com/Chanmoro/items/55bf0da3aaf37dc26f73  
(参照) https://dev.classmethod.jp/articles/terraform-getting-started-with-aws/


## インストール

> sudo yum install wget unzip
> wget https://releases.hashicorp.com/terraform/0.11.13/terraform_0.11.13_linux_amd64.zip
> sudo unzip ./terraform_0.11.13_linux_amd64.zip -d /usr/local/bin/
> terraform -v


## コマンド

* 初期化

> terraform init

※ 初めて実行する場合のみ初期化のため。


* 引数指定(例)

```
terraform apply \
  -var 'access_key=AKIAXXXXXXXXXXXXXXXXXX' \
  -var 'secret_key=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX'

※ 環境変数でも可
export TF_VAR_access_key="AKIAXXXXXXXXXXXXXXXXXX"
export TF_VAR_secret_key="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
```


* 対象リソース指定

> terraform plan -target={resource}


* 実行内容検証

> terraform plan

※ var 変数等を引数で指定しない場合はここで設定できる。


* 実行

> terraform apply

※ var 変数等を引数で指定しない場合はここで設定できる。
※ 最終実行確認時に yes とすることで生成される。
※ 更新する場合も同じ。

* 結果確認

> terraform show

* 削除

> terraform destroy


## リソース設定チートシート

(参照) https://www.terraform.io/docs/providers/aws/r/dynamodb_table.html
