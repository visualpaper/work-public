# Groovy

* Groovy SDK インストール
http://groovy-lang.org/download.html
⇒ 展開されたフォルダを C 配下などに用意し、InteliJ で参照する。

## 基本的な構文勉強
### 覚えておいた方が良いレベル
* 正規表現として /../ 以外に =~ と ==~ が存在する
  `=~`: 文字列から一致する部分を抜き出す
  `==~`: 文字列が指定した正規表現と一致しているかどうかチェックする

* クロージャには this/owner/delegate がある
  this/delegate はそれぞれ自身/委譲した何かを指している
  owner は自身を囲っているクロージャを指している (ひとつ↑階層を指している)
  参考: https://qiita.com/saba1024/items/b57c412961e1a2779881

* 関数合成というものがある
  twice(toInt(getDataFromDatabase()))
  -> Closure calc1 = getDataFromDatabase >> toInt >> twice

* Trait 構文で多重継承できる
  -> trait A {}
     trait B extends A {}
     trait C extends A {}
     class D implements B, C {}

* @TypeChecked/@CompileStatic
  `@TypeChecked`: コンパイル時に型推論してチェックするが、生成される class ファイルにはその情報は残っていない (Object型になる)
  `@CompileStatic`: コンパイル時に型推論してチェックし、さらに class ファイルのその情報が残る
  ※ metaClass で操作する際に影響が発生しそう。@DelegatesTo というものにも影響しそう


### メモレベル
* エルビス演算子とかいう参考演算子をスマートにしたものがある
  assert "aaa" == (value == "aaa"? "aaa" : "not equal")
  -> "aaa" == value ?: "not found"
