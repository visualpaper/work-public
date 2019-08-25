
# Docker Deploy 勉強
## はじめに
* ecs-study フォルダ内にある ALB 構築まで実施している状態

## Apache 用意
* ui フォルダ内の Dockerfile を build
* 作成した image を `docker run -itd --net=host [イメージId]` で起動
  ※ 80 ポートをそのまま繋げる必要があるため `--net=host` が必要

## Tomcat 用意
* server フォルダ内の server.war / Dockerfile ファイルを ec2 へコピーする
* Dockerfile を build
* 作成した image を `docker run -itd -p 8081:8080 [イメージID]` で起動

## 構成
* EC2
  * ec2-18-182-61-74.ap-northeast-1.compute.amazonaws.com
    ※ 22 ポートを空けている (key ファイルを用いて teraterm や winSCP で接続可能)
<br>

* VPC
  * vpc-02ce58a18a991fee0
    ※ VPC ~ ELB までの構築手順は ecs-study フォルダ内にある ALB 構築参照

<br>

* ELB
  * http://umejima-vpc-elb-394473554.ap-northeast-1.elb.amazonaws.com
    ※ 80 ポートのみ空けている。
    ※ 今のところ常時 sample-ui / sample-server という image を元に container を起動しているため
       常に API にアクセス可能な状態となっている。
<br>

* API
  * healthCheck
    {ELB Path}/app/healthCheck
