

# [関数]
# 戻り値がない場合は None が返却される
# 変数参照は関数内、関数外、グローバルの順
# docString (javadoc のようなもの) は↓のように記載できる。
# 関数自体の参照を渡すこともできる。
# 関数を戻り値とすることもできる。
def doAA(param):
    """docstring"""
    print(param)


doAA(2)
print(doAA(3))
fib = doAA
fib(2)


# 匿名関数みたいに Lambda 式も記載できる
def doBB(param):
    return lambda x: param + x


print(doBB(5)(2))  # -> 7


# デフォルト引数も使えるが、状態を持ち続けるものもあるので注意
# キーワード引数指定もできる。


def f(a, L=[], b=123):
    L.append(a)
    print(b)
    return L


print(f(a=1, b=100))
print(f(2))
print(f(3))


# 可変引数、可変キーワード引数も使える。
# 可変引数にはキーワード引数指定以外
# 可変キーワード引数にはキーワード引数指定のみが入る。
# 関数宣言側で強制することもできる (def pos_only_arg(arg, /)、def kwd_only_arg(*, arg))
def cheeseshop(kind, *arguments, **keywords):
    print("-- Do you have any", kind, "?")
    print("-- I'm sorry, we're all out of", kind)
    for arg in arguments:
        print(arg)
    print("-" * 40)
    for kw in keywords:
        print(kw, ":", keywords[kw])


cheeseshop("Limburger", "It's very runny, sir.",
           "It's really very, VERY runny, sir.",
           shopkeeper="Michael Palin",
           client="John Cleese",
           sketch="Cheese Shop Sketch")
