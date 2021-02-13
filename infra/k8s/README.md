
kubernetes

* k8s マスター
  クラスターを管理する

* k8s クラスター
  ノード・マスタをまとめたクラスタ

* k8s ノード
  コンテナを配置する場所 (マシン)
  ※ マスターが公開しているKubernetes APIを使用してマスターと通信

* K8s Pod
  1つ以上のアプリケーションコンテナ(Dockerやrktなど)のグループとそれらのコンテナの共有リソースを表すKubernetesの抽象概念
  ※ ネットワーキング、ボリューム

* Deployments
  アプリケーションのインスタンスを作成し、更新する方法を指示
  ※ スケジューリング、スケーリング、セルフヒーリング

* Ingress
  クラスター内の Service に対する外部からのアクセス(主にHTTP)を管理するAPIオブジェクト
  ※ (L7)ロードバランサー

* Service
  Podの論理セットと、それらにアクセスするためのポリシーを定義する抽象概念
  ※ YAML(推奨)またはJSONを使って定義する
  ※ (L4)ロードバランサー、NAT、DNS


## kubectl

* Kubernetesのコマンドラインインターフェイス
* Kubernetes API を使用してクラスターと対話する

### インストール

curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl"
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl

### コマンド

* kubectl version --client

* kubectl config view
  ※ クラスタコンフィグ情報 (クラスタ・認証・コンテキストのコンフィグ情報があり、どのクラスタを利用するかの指定ができる)
  ※ ~/.kube/config などにマニフェストファイルを用意することで変更可能
  ※ コンテキスト別にコマンドを打つことも可能 (kubectl --context prd-admin get pod)

※ コンテキスト (およびそれに付随するクラスタ・認証情報) を決めた上で pod の作成等を行う必要があるため、
   自分で一から作らず、簡易ツール (minikube) を利用する。


## minikube

* Minikubeはローカル環境でKubernetesを簡単に実行するためのツール
* シングルノードのクラスタのみ実行可能

### インストール

curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 && chmod +x minikube && sudo mv minikube /usr/bin/
sudo yum install -y conntrack
sudo minikube start --vm-driver=none

### コマンド

* minikube start/status/stop
  ※ 開始/ステータス/停止

* sudo minikube kubectl -- apply -f xx --prune -l system=xxx
  ※ リソース作成
     (kind の値によって作成されるリソースを定める)
     (複数のリソースを一つのマニフェストファイルに含めることも可能)
  ※ create でも作成可能だが、前回との差分を確認可能なようにするため apply を利用する
     (apply の場合、利用したマニフェストファイルがログに残るため前のマニフェストファイルがなくなったとしても比較できる可能性がある都合より)
  ※ prune を付けることで、変更差分による削除も併せて実施される
  ※ label を付けることで、対象リソースの絞り込みが可能

* sudo minikube kubectl get pods
  ※ Pod 一覧取得
  ※ label を付けることで、対象リソースの絞り込みが可能

* sudo minikube kubectl get service
  ※ Service 一覧取得
  ※ label を付けることで、対象リソースの絞り込みが可能

* sudo minikube kubectl -- exec -it xx bash
  ※ docker exec と同じ
  ※ xx に pod 名を入れれば内部にアクセスできる

* sudo minikube kubectl port-forward xx (localhostのポート番号):(serviceのポート番号)
  ※ xx に deployment/service/pod いずれかを指定可能
  ※ service の場合は svc/xx
