
# Docker 関連勉強
## はじめに
### 環境用意
1. EC2 作成
2. [接続] 欄に表示される EC2 ホスト名/キーペアファイルを秘密鍵に指定し SSH 接続
※ デフォルトで 22 ポートは開いている

### Docker 初期設定
1. インストール
```
sudo yum update -y
sudo yum install -y docker
```

2. 起動
```
sudo service docker start
```

3. Docker グループに ec2-user を追加
```
sudo usermod -a -G docker ec2-user
```

4. 一旦ログアウト⇒ログイン

5. info 表示確認
```
docker info
```
※ ec2-user で sudo なしでいけることを確認

## STEP1: イメージ取得～起動～終了
1. イメージを Docker HUB より取得する
> docker pull hello-world

2. イメージ一覧確認
> docker image ls

3. イメージを元にコンテナを起動する
> docker run hello-world

4. 起動しているコネテナの確認
> docker ps
※ hello-world は起動しっぱなしでないらしい

5. 停止しているコンテナの確認
> docker ps -a

6. コンテナの削除
> docker rm [コンテナID]

7. 停止しているコンテナの確認
> docker ps -a

8. イメージの削除
> docker rmi [イメージID]

9. イメージの確認
> docker image ls

## Step2. DockerFile
* Dockerfile はコンテナー内の環境で何が起こるかを定義するもの

```
# チートシート
## 便利
apt-get update
apt-get install vim

## Docker
sudo service docker start

### 確認系
* 動いているコンテナの確認
docker ps

* 停止しているコンテナの確認
docker ps -a

* イメージの確認
docker image ls

### 作成系
* イメージの作成
docker build [ -t ｛イメージ名｝ [ :｛タグ名｝ ] ] ｛Dockerfileのあるディレクトリ｝

### 取得系
* イメージの取得
docker pull イメージ名[:タグ|@イメージのハッシュ値]
例: docker pull debian:jessie
例(ECR):
$(aws ecr get-login --region ap-northeast-1 --no-include-email)
docker pull 683640743654.dkr.ecr.ap-northeast-1.amazonaws.com/umejima-sample-ecr:latest

### 起動系
* コンテナの起動
docker run [イメージID]
※ 存在しない場合、DockerHUB から PULL するっぽい？

  例: マシンのポート4000をコンテナの公開ポート80にマッピングして、アプリを実行
  docker run -p 4000:80 <イメージID>

  例: ↑をバックグラウンドでデタッチモードで実行
  docker run -d -p 4000:80 <イメージID>

  例: 起動したままにする
  docker run -itd <イメージID>

  例: host の iptable を利用する
  docker run -itd --net=host <イメージID>

### 再起動
docker restart <コンテナID>

### 停止系
* コンテナの停止
docker container stop <コンテナID>

### 削除系
* コンテナの削除
docker rm [コンテナID] [コンテナID] ...
※ 全コンテナ削除 (docker rm `docker ps -a -q`)

* イメージの削除
docker rmi [イメージID]

### コンテナに入る
docker exec -it <イメージID> bach
※ bash とか sh とかで入れるっぽい。今のところ bash でやって問題ない。

## DockerFile
http://docs.docker.jp/engine/reference/builder.html#

* 以降の命令で使う ベース・イメージ を指定します。
FROM <イメージ> | <イメージ>:<タグ> | <イメージ>@<digest>
例: FROM python:2.7-slim
※ 最初に記載する必要がある。

# 命令実行時の作業ディレクトリを指定します。
WORKDIR <path>
例: WORKDIR /app
※ 指定がない場合でも、勝手に作られるらしい。

# ファイルを移動します。
COPY <ソース> <送信先>
例: COPY . /app
※ <送信先> は絶対パスです。あるいは、パスは WORKDIR からの相対パスです。

# シェル形式でコマンドを実行します。
RUN <コマンド>
例: RUN pip install --trusted-host pypi.python.org -r requirements.txt
※ シェル形式、コマンドを実行する。Linux 上のデフォルトは /bin/sh -c

# コンテナが実行時にリッスンするポートを指定します。
EXPOSE <ポート番号>
例: EXPOSE 80
※ -p フラグを指定しなければ、これだけではホストからコンテナにアクセスできるようにしませんという記述有り。

# 環境変数を設定します。
ENV <key> <value>
例: ENV NAME World

# Run app.py when the container launches
CMD ["実行バイナリ", "パラメータ１", "パラメータ２"] （ exec 形式、推奨する形式）
例: CMD ["python", "app.py"]
※ Dockerfile で CMD 命令を一度だけ指定できます。複数の CMD がある場合、最も後ろの CMD のみ有効。
```

# TODO
## DockerCompose
* コンテナ同士の連携関連
