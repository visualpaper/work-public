
# React Study

## JSX

* 複数 element の場合は "()" で囲む
* babel で createElement にしているだけ
* (jsx がかは微妙だが) injection 攻撃を防いでくれる

## React

```
1. UI をコンポーネントの階層構造に落とし込む
   ※ コンポーネントを導出

2. Reactで静的なバージョンを作成する
   ※ state を用いずに作る

3. UI 状態を表現する必要かつ十分な state を決定する
   ※ 動的な要素を考慮し state を決定する
   ※ state の最小構成が明確にする

4. state をどこに配置するべきなのかを明確にする
   ※ その state を使って表示を行う、すべてのコンポーネントを確認する
   ※ 共通の親コンポーネントを見つける（その階層構造の中で、ある state を必要としているすべてのコンポーネントの上位にある単一のコンポーネントのことです）
   ※ 共通の親コンポーネントか、その階層構造でさらに上位の別のコンポーネントが state を持っているべきである
   ※ もし state を持つにふさわしいコンポーネントを見つけられなかった場合は、state を保持するためだけの新しいコンポーネントを作り、階層構造の中ですでに見つけておいた共通の親コンポーネントの上に配置する

5. 逆方向のデータフローを追加する
   ※ state のリフトアップ観点で変更可用性があるか
```

### 仮想 DOM

* react 要素は immutable
* render を呼ぶことで全て更新する
* 変化がある部分のみ更新される

### props

* state はコンポーネント内部で管理されるもの、props はコンポーネントへ渡されるもの
* props は読み取り専用

### state

* state はユーザ操作や時間経過などで動的に変化するデータを扱うために確保されている機能
* データは下に一方方向に伝わる
  > データフローは一般的には “トップダウン” もしくは “単一方向” データフローと呼ばれます。
  > いかなる state も必ず特定のコンポーネントが所有し、state から生ずる全てのデータまたは UI は、
  > ツリーでそれらの “下” にいるコンポーネントにのみ影響します

* state は props に似ていますが、コンポーネントによって完全に管理されるプライベートなもの
* 各コンポーネント別に独立している
* this.state に直接代入してよい唯一の場所はコンストラクタ (setState) のみ
* setState は何回でも呼べるが、結果はマージ (shallow マージされる)



## React Component

* 基本的にコンポジで、継承が必要なケースはほぼというか全くないらしい
* component クラスから null を返却すれば何も描画されない
* ↑をしなくても、component 自体を呼び出さないように jsx 上で条件を入れるといった方法でも良い (そちらのほうが良い気がする)
* 連携させたいなら、より上位の component で state 管理してあげる必要がある
* そのために変更検知させるイベントハンドラを下位コンポーネントに移譲したりする (https://codepen.io/gaearon/pen/WZpxpz?editors=0010)

### key

* Key は、どの要素が変更、追加もしくは削除されたのかを React が識別するのに役立ちます。配列内の項目に安定した識別性を与えるため、それぞれの項目に key を与えるべきです

* 配列内で使われる key はその兄弟要素の中で一意である必要があります。しかしグローバルに一意である必要はありません。2 つの異なる配列を作る場合は、同一の key が使われても構いません


### from

* form の submit で event.preventDefault(); でデフォルト動作を抑止できる (https://qiita.com/tochiji/items/4e9e64cabc0a1cd7a1ae)
