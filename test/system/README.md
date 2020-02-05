
# Docker Deploy 勉強
## はじめに
* ecs-study フォルダ内にある ALB 構築まで実施している状態

## dd-agent 用意

```
docker run -d --name dd-agent \
  -v /var/run/docker.sock:/var/run/docker.sock:ro \
  -v /proc/:/host/proc/:ro \
  -v /cgroup/:/host/sys/fs/cgroup:ro \
  -v /opt/datadog-agent-conf.d:/conf.d:ro \
  -e DD_API_KEY=0d215d8f44c642c18c4e0543d5aa2ff5 \
  -e DD_APM_ENABLED=true \
  -e DD_LOGS_ENABLED=true \
  -e DD_APM_NON_LOCAL_TRAFFIC=true \
  -e DD_DOGSTATSD_ORIGIN_DETECTION=true \
  -e DD_DOGSTATSD_NON_LOCAL_TRAFFIC=true \
  -e SD_JMX_ENABLE=true \
  -e SD_BACKEND=docker \
  -p 8125:8125/udp \
  -p 8126:8126/tcp \
  datadog/agent:latest-jmx
```
※ 8125 udp, 8126 tcp ポートを開放 (インバウンドルールに追加)

## Apache 用意
* ui フォルダ内の Dockerfile を build
* 作成した image を `docker run -itd --net=host [イメージId]` で起動
  ※ 80 ポートをそのまま繋げる必要があるため `--net=host` が必要

## Tomcat 用意
### data-dog 有り
* server フォルダ内の server.war / Dockerfile / run.sh ファイルを ec2 へコピーする
* Dockerfile がある階層で java APM ダウンロード
  > wget -O dd-java-agent.jar 'https://search.maven.org/classic/remote_content?g=com.datadoghq&a=dd-java-agent&v=LATEST'

* Dockerfile を build
* 作成した image を `docker run -itd -p 7199:7199 -p 8081:8080 --link dd-agent:dd-agent [イメージID]` で起動

### data-dog 無し
* server フォルダ内の server.war / Dockerfile ファイルを ec2 へコピーする
* 作成した image を `docker run -itd -p 8081:8080 [イメージID]` で起動

## 構成
* EC2
  * ec2-3-115-12-156.ap-northeast-1.compute.amazonaws.com
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
