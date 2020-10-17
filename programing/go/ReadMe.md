## インストール

1. golang インストール
    https://golang.org/doc/install

2. vscode プラグインインストール
   https://marketplace.visualstudio.com/items?itemName=golang.Go

3. go version で確認

## 実行

go run
※ go run . で大体 OK

## フォーマット

go fmt

## ビルド

go build
※ 実行時のフォルダ名を元に実行ファイルができあがる。
※ "-o yyy xx.go" というように実行ファイル名や、ビルド対象ファイルを指定することもできる。

## テスト

go test -v
※ ファイル名に "_test.go" があり、かつ、メソッド名の先頭が "Test" であるものが対象となる。
※ go test -v xxx で対象ディレクトリを指定できる。

## 基本

const (
    Y = 1
    Z // 省略すると、前の値と同じ (1) になる
    A = int(1) // 定数は型ありと無しがある。
) // 定数

i:= 1 // 型指定 + 代入

itoa: 値代入時にインクリメントされていく
複数戻り値: func xxx(x, y int) (int, int) { return 1, 2 } とかでできる
戻り値破棄: _, a := xxx(1, 2) のように↑メソッドを呼ぶと第一戻り値は破棄される。全破棄するなら xxx(1, 2) と呼べば良い。

無名関数・関数引数・関数戻り値:

```
func callFunction(f func()) {
    return f()
}

func main() {
    callFunction(func(){fmt.Println("aa")})()
}
```
※ クロージャもできる

## 制御

if x < y {

}

※ 簡易文付き制御
if n := x * y; n > x {

}

## 繰り返し

for {}
※ continue / break で制御

for i, r := range list {

}
※ i に index, r に要素

## switch

switch s:= 0 s {
    case 0:
        fmt.Println("aaa")
        fallthrough // デフォルトではフォールするーしない。明示指定が必要
    case 1:
        fmt.Println("bbb")
    default:
        何もなければ。
}

switch {
    case n > 0
        fmt.Println("aaa")
    case n < 1
        fmt.Println("bbb")
}
このような書き方もある。条件を case に記載する場合は定数を case に記載できない。

## スコープ

* パッケージ -> ファイル -> 関数 -> ブロック -> 制御構文(if/for 等)
  ※ グローバルはない。

* 他パッケージから参照可能であるかどうかは、定数/変数/関数の 1 文字目が大文字かどうかで決まる

## 型アサーション

動的に変数の型をチェックできる機能

var x interface{} = 3
i := x.(int) // => 3 代入
f := x.(float64) // 実行時エラー

i, isInt := x.(int) // i == 3, isInt == true
f, isFloat := x.(float64) // f == 0, isFloat == false 実行時エラーは起こらない

switch x.(type) {
    case bool:
        xxx
    case int:
        xxx
}
という記載もできる

## defer

関数の終了時に実行される式

func sample() {
    defer fmt.Println("aaa")
    defer fmt.Println("bbb")
    defer fmt.Println("ccc")
    fmt.Println("ddd")
}
=> "ddd", "ccc", "bbb", "aaa" の順で実施
※ リソースの開放 (ファイルクローズとか) に使える。

## panic

ランタイムエラーを発生させる定義済み関数
func panic(v interface{})

panic 実施時点で即座に終わるので、
アプリけーションの一般例外というものでなく、OOM とかの感覚で良い。
特記事項としては、中断前までに defer に登録済みの処理は、
panic 後も実施される。

## recover

panic をリカバーする定義済み関数
panic で指定された引数が recover の戻り値となる。
defer と組み合わせて使うのが原則。

func main() {
    defer func() {
        // nil 以外なら、panic が呼ばれたことになる。
        if x := recover(); x != nil {
            fmt.Println("recover")
        }
    }()

    panic("aaa")
    fmt.Println("bbb")
}
=> recover と出力される。
関数をまたいで panic / recover はできない。goto みたいで強力すぎるため。

func sample(v string) {
    fmt.Println()
    defer func() {
        // nil 以外なら、panic が呼ばれたことになる。
        if x := recover(); x != nil {
            fmt.Println("recover")
        }
    }()

    panic("aaa")
}

func main() {
    sample("1")
    sample("2")
}
=> 1, recover, 2, recover と出力される。

強力すぎるという点は頭に入れておいたほうが良い。

## go ルーチン

* 並列処理をスレッドより小さい単位で実施
* go ルーチンで実施する関数が完了した際に go ルーチンは終了する

func sample() {
    xxx
}

func main() {
  go sample()  // go ルーチンが起動し、メインルーチンとは別で処理が実施される
  xxx
}

## init

パッケージの初期化を目的とした特殊な関数

func init() {
    fmt.Println("aaa")
}

func init() {
    fmt.Println("bbb")
}

func main() {
    fmt.Println("ccc")
}
=> "aaa", "bbb", "ccc" 

before みたいなもので、複数書くことで上から順に実施される。
色々気になるが、さわりだけ。

## 参照型

* nil を受け付けることが可能

### slice

* 配列は値だがスライスは参照。可変長か固定長かが大きな違い
* 型という言葉でいうと、型は要素数を混みでひとつの型

a := [5]int{1,2,3,4,5}
s := a[0:2]
len(s) // 2 (長さ)
cap(s) // 5 (容量)
a[1] = 0 // s == [1,0] ←ここが危険。参照を持っている。

### map

m := make(map[int]string)
※ make(map[int]string, 100) -> 100 要素を持つ map 生成とかもできる

m[1] = "US"
fmt.Println(m)
=> [1:US]

s, ok := m[1] // s == "US", ok = true
s, ok := m[10] // s == "", ok = false

### チャネル

* go ルーチン間でのデータの受け渡しを行うためのもの
* chan ${データ型} 形式での型指定となる
* close しない場合はチャネル受信ブロックで待ち続ける
* close した場合は、型の初期値を受け取りブロックしない
  そういった場合のため、第二引数で close 済みかを判断可能
* select を用いることで複数チャネルを別々 or ランダムに受信することもできる
  ちょっとややこしい気がする

var ch chan int   // ch 変数は int 型の送受信可能なチャネル
var ch <-chan int // 受信専用
var ch chan<- int // 送信専用

## 構造体

* type でエイリアス指定も可能
  type MyInt int
  type ${エイリアス} ${型}

* ポインタを用いることで持ちまわすことも可能
* new(${構造体}) でポインタを作れる

type Person struct {
    id int "Tag" // Tag というものを利用できる。
                 // metadata なのでプログラムには影響なし。
    name string
    area string
}

var pt Person
=> pt.id == 0, pt.name == ""

ptref := &Person
sample(ptref)

func sample(ptref *Person) {}



## 特記事項

* import (
    f "fmt"
)
  -> f.Println("aaa") のように使える。

* import (
    . "math"
)
  -> f.Println(Pi) のように math パッケージ名の指定を省略することもできる。

