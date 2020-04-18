# 構成

※ 詳細は ecs-study フォルダ内にある ALB 構築を参照

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


# 起動コマンド
### 設定確認
> ulimit -Sn
> ulimit -Hn
※ 65536 でなければ以下ファイルに設定追加

> sudo vi /etc/security/limits.conf
  `* soft nofile 65536`
  `* hard nofile 65536`

### 起動コンテナをクリアする
docker rm -f `docker ps -a -q`

### dd-agent 起動 (DataDog 有版)
docker run -d --name dd-agent \
  -v /var/run/docker.sock:/var/run/docker.sock:ro \
  -v /proc/:/host/proc/:ro \
  -v /cgroup/:/host/sys/fs/cgroup:ro \
  -v /opt/datadog-agent-conf.d:/conf.d:ro \
  -e DD_APM_ENABLED=true \
  -e DD_LOGS_ENABLED=true \
  -e DD_APM_NON_LOCAL_TRAFFIC=true \
  -e DD_DOGSTATSD_ORIGIN_DETECTION=true \
  -e DD_DOGSTATSD_NON_LOCAL_TRAFFIC=true \
  -e SD_JMX_ENABLE=true \
  -e SD_BACKEND=docker \
  -e DD_API_KEY=de50e2046291eccd9b2086158afb98c3 \
  -p 8125:8125/udp \
  -p 8126:8126/tcp \
  datadog/agent:latest-jmx
※ 8125 udp, 8126 tcp ポートを開放 (インバウンドルールに追加)
※ ポートが解放されていない場合、docker image 削除し以下コマンド実施
   > sudo lsof -i -P | grep "LISTEN"
   > sudo kill {dd-agent 関連 pid}

### Apache 起動
docker run -itd --net=host --ulimit="nofile=65536" {イメージID}

### Tomcat 起動
docker run -itd -p 8081:8080 --ulimit="nofile=65536" {イメージID}

### Java AMP 取得 (DataDog 有版)
wget -O dd-java-agent.jar 'https://search.maven.org/classic/remote_content?g=com.datadoghq&a=dd-java-agent&v=LATEST'

### Tomcat 起動 (DataDog 有版)
docker run -itd -p 7199:7199 -p 8081:8080 --link dd-agent:dd-agent --ulimit="nofile=65536" {イメージID}

## 攻撃サーバ
### 起動コンテナをクリアする
docker rm -f `docker ps -a -q`
docker images -aq | xargs docker rmi

### Docker Build
sudo docker build -t locust-docker-image .

### Master
sudo docker run --name master -itd -p 8089:8089 -p 5557:5557 -p 5558:5558 locust-docker-image --master

### Slave
sudo docker run --link master -itd locust-docker-image --slave --master-host=master --logfile ./locustfile.log

### Slave (別マシン)
sudo docker run -itd locust-docker-image --slave --logfile ./locustfile.log --master-host=${Master Host IP Address}

# メトリクス
## CPU コマンド
* top
> top -d1
※ ロードアベレージが低くて RPS が出ない場合は I/O、高くて RPS が出ない場合は CPU / メモリを疑う
※ 1 キーで CPU コア別の利用率がわかる
※ スワップの発生頻度も、メモリ使用率もわかる。
※ sleeping が多い場合は wchan (y キー押下) で内容がわかる

* netstat
> watch -d -n 1 "netstat -s"
※ segments retransmited (セグメント再送出) と segments send out (セグメントの送出) の値を比較し、セグメント再送出が高ければネットワークの信頼性が低い
※ connections established (ESTABLE コネクション数) で、リクエストで受け付けているコネクション数が見える

> watch -d -n 1 "netstat -i"
※ DRP と OVR (ドロップとオーバーラン) が多い場合は飽和の兆候があるが、ここも問題ない。

* 対象サーバへ届いているコネクション数確認
> watch -d -n 1 "netstat -alpn | grep -E ':(80|443) ' | awk {'print $5'} | sed -e 's/\:[^.]*$//' | sort | uniq -c"

* ネットワーク送信量
> watch -d -n 1 "cat /proc/net/dev"
※ 10 列目の値が転送バイト

## Apache

http://umejima-vpc-elb-394473554.ap-northeast-1.elb.amazonaws.com/server-status

## Tomcat

```
ps aux | grep java
cd /proc/xxx/task
watch -n 1 'ls -l|wc -l'
```

## DataDog

```
system.cpu.user: CPUがユーザー空間プロセスの実行に費やした時間の割合(パーセント)
system.cpu.system: CPUがカーネルの実行に費やした時間の割合(パーセント)
system.mem.used: 使用中のRAMの量(バイト)
system.mem.free: 空きRAMの量(バイト)
※ https://docs.datadoghq.com/ja/integrations/system/

system.disk.used: 使用中のディスク容量(バイト)
system.disk.free: 空きディスク容量(バイト)
※ https://docs.datadoghq.com/ja/integrations/disk/

system.net.bytes_rcvd: 1秒間にデバイスで受信されたバイト数
system.net.bytes_sent: 1秒あたりにデバイスから送信されたバイト数
※ https://docs.datadoghq.com/ja/integrations/disk/

jvm.heap_memory: 使用されたJavaヒープメモリの合計(バイト)
jvm.non_heap_memory: 使用されているJava非ヒープメモリの合計(バイト)
jvm.gc.major_collection_count: 発生した主要なガベージコレクションの数
jvm.gc.minor_collection_count: 発生したマイナーガベージコレクションの数
※ https://docs.datadoghq.com/ja/tracing/runtime_metrics/java/

tomcat.thread.busy: 使用中のスレッドの数
tomcat.thread.count: スレッドプールによって管理されるスレッドの数
※ https://docs.datadoghq.com/ja/integrations/tomcat/

apache.performance.busy_workers: リクエストを処理するスレッド数
apache.performance.idle_workers: アイドル状態のスレッド数
※ https://docs.datadoghq.com/ja/integrations/apache/
```
