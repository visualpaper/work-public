
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

```
# チートシート
## 確認系
* 動いているコンテナの確認
docker ps

* 停止しているコンテナの確認
docker ps -a

* イメージの確認
docker image ls

## 取得系
* イメージの取得
docker pull イメージ名[:タグ|@イメージのハッシュ値]
例: docker pull debian:jessie

## 起動系
* コンテナの起動
docker run [イメージID]
※ 存在しない場合、DockerHUB から PULL するっぽい？

## 削除系
* コンテナの削除
docker rm [コンテナID] [コンテナID] ...
※ 全コンテナ削除 (docker rm `docker ps -a -q`)

* イメージの削除
docker rmi [イメージID]

```
