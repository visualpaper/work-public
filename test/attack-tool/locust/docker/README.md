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
  -e DD_API_KEY=ed7960cae2f7c7e1134754f8e8b2e8de \
  -p 8125:8125/udp \
  -p 8126:8126/tcp \
  datadog/agent:latest-jmx
※ 8125 udp, 8126 tcp ポートを開放 (インバウンドルールに追加)

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

 # 検証
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
