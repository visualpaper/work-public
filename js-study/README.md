# JavaScript
## 代入
```
var name = 21;
```

## 変数定義
* var
※ const 的な挙動をするものはない (はず。CoffeeScript の時はクラス変数として定義する方法はあった)

## スコープ
* 以下の 2 種類しかサポートしていない
  - グローバルスコープ
  - 関数スコープ

## 関数定義
```
var plus = function(x, y) {
  return x + y;
};
```
※ this 参照は関数自身(なので、this を渡すとかいうことをしなければ、関数外での操作ができなかったらしい。

## クラス
```
function Human(name) {
  this.name = name;
}

Human.prototype.hello = function () {
  console.log('My name is ' + this.name);
};
```

## エクスポート
```
module.exports.aaa = function() {
 ~~~~
}
```

## インポート
```
var module = require(./module.js);
```

# EC2015
## 代入
```
let a = 21;

// name/age に対し `Koyabu`/20 を一度に設定できる。
let [name, age] = ['Koyabu', 20];
```

## 変数定義
* let (代入可能)
* const (代入不可能)
※ できるだけ var を使用しないほうが良いらしい

## スコープ
* ブロックスコープが新たに追加された
```
{
  let a = i
}
```
※ ブラケット内で let や const により定義された変数はそのブラケットのスコープ内のみで生きることができる。

## 関数定義
* アロー関数定義が可能
```
let plus = (x, y) => {
  return x + y;
};

// 単一式の場合はブラケットやreturnを省略できる
let plus = (x, y) => x + y;

// リテラルを返却する場合は()で囲む必要がある
var func = () => ({ foo: 1 });

// 可変長引数もできる。超便利
let f = (x, ...ys) => console.log(x, ys);
※ f(2, 3, 5) => 2 [ 3, 5 ]

// デフォルト引数もできちゃう。
function multiply(a, b = 1) {
  return a*b;
}
multiply(5); // 5
```
※ this 参照は関数が定義されたスコープにおける this を引き継ぐ。

## クラス定義
```
// 継承もできる(super.メソッド名() で親メソッドコールも可)
class Human extends Animal {
  constructor(name) {
    super();
    this.name = name;
  }

  // static メソッド (クラスメソッドと同義)
  static num_of_hands() {
    console.log(2)
  }

  // prototype メソッド
  hello() {
    console.log('My name is ' + this.name);
  }
}
```
※ アクセス修飾子はかけない。

## エクスポート
* Named エクスポート
```
export const sqrt = Math.sqrt;
export function square(x) {
    return x * x;
}
```
※ 複数定義をエクスポートできるが、インポート側で同一名称を使用する必要がある

* Default エクスポート
```
export default function() {...};
```
※ JS ファイルにつき一つだけエクスポートでき、インポート側で独自名称で使用できる。
※ シンプルなので、推奨されている。以下公式文章コピペ

> ECMAScript 6は単一/デフォルトのエクスポートスタイルを好み、デフォルトをインポートするための最も優れた構文を提供します。
> 名前付きエクスポートのインポートは、やや簡潔ではなくなる可能性があります。

## インポート
* Named エクスポートをインポートする場合
```
import { call, put, takeEvery, delay } from 'redux-saga/effects'
```

* Default エクスポートをインポートする場合
```
import MyClass from 'MyClass';
import { MyComponent2 as MyNewComponent } from "./MyComponent";
```


## その他
### 配列展開
```
var array = [1, 2, 3]
function f(x, y, z) { }

//f(1, 2, 3)という引数での関数fの呼び出しと同義
f(...array);

//[1, 2, 3, 4, 5, 6]となる
[...array, 4, 5, 6]

// a = 1, b = 2, x = 3, y = 4, z = 5とした場合と同じ
var x
var y
var z
var variables = [x, y, z]
[a, b, ...variables] = [1, 2, 3, 4, 5];
```

### 変数展開
```
var name = 'Koyabu'

var hello = `My name is
${name}`
console.log(hello)
//=>My name is 
//Koyabu
```

# TypeScript
* private/public アクセス修飾子をかけるらしい。
