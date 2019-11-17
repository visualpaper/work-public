import sys

# [代入]
# multiple assignment (複数同時の代入が可能)
a, b = 0, 1  # -> a=0, b=1

# [数値]
# int/float だけ覚えればまずは OK (小数点があれば自動で float と看做される)
intHoge = 1
intHoge = 1 + 1.2  # -> 2.2

# [文字列]
# Slice が便利。要素アクセスも直感的
strHoge = "abcd"
strHoge[0]  # -> a
strHoge[0:2]  # -> ab

# [リスト]
# 操作が良い感じにできる。多次元配列も直感的
listHoge = []
listHoge.append(1)
listHoge += [2, 3]
list2Hoge = [listHoge, [4, 5, 6]]  # -> [[1, 2, 3], [4, 5, 6]]

# [タプル]
# 任意の要素数の値をまとめてあたかもひとつの値のように扱う
tupleFuga = (12345, 54321, 'hello!')
print(tupleFuga[0])

# [辞書]
# キー(key): 値(value) のペアの集合であり、キーが (辞書の中で)一意でければならない
dictionalHoge = {'jack': 4098, 'sape': 4139}
dictionalHoge['guido'] = 4127
print(dictionalHoge['jack'])

# [If]
# if ~ elif ~ else 構文。":" で区切る
x = int(input("Please enter an integer: "))

if x < 0:
    print("0 以下")
elif x == 1:
    print("1")
else:
    print("other")

# [For]
# in 句だけしかない。数値で回す場合は range が便利。step も踏める
# シーケンスと一緒に回す enumerate は良く使いそう
words = ['cat', 'window', 'defenestrate']
for word in words:
    print(word)

for i in range(5):
    print(i)

for i in range(0, 10, 2):
    print(i)

for i, v in enumerate(['tic', 'tac', 'toe']):
    print(i, v)

  # break / continue もある。
for n in range(2, 10):
    for x in range(2, n):
        if n % x == 0:
            print(n, 'equals', x, '*', n//x)
            break

      # noneMatche と同じと思って良い。
    else:
        print(n, 'is a prime number')

# [While]
# 普通
num = 0
while num < 2:
    print("num = " + str(num))
    num += 1

print("End")

# [その他]
# pass (do-nothing コメントのようなもの)
if 0 == 0:
    pass
